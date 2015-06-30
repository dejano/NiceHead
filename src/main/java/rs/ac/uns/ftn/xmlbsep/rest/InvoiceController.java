package rs.ac.uns.ftn.xmlbsep.rest;

import rs.ac.uns.ftn.xmlbsep.beans.jaxb.ResultWrapper;
import rs.ac.uns.ftn.xmlbsep.beans.jaxb.User;
import rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated.invoice.Invoice;
import rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated.payment.AccountDetails;
import rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated.payment.PaymentOrder;
import rs.ac.uns.ftn.xmlbsep.dao.ConfigDao;
import rs.ac.uns.ftn.xmlbsep.dao.InvoiceDaoLocal;
import rs.ac.uns.ftn.xmlbsep.dao.PartnerDaoLocal;
import rs.ac.uns.ftn.xmlbsep.security.handler.SecMessageHandler;
import rs.ac.uns.ftn.xmlbsep.security.handler.ClientCryptoHandler;
import rs.ac.uns.ftn.xmlbsep.security.handler.ClientSignatureHandler;
import rs.ac.uns.ftn.xmlbsep.security.HasPermission;
import rs.ac.uns.ftn.xmlbsep.security.InvoiceFactory;
import rs.ac.uns.ftn.xmlbsep.security.InvoiceState;
import rs.ac.uns.ftn.xmlbsep.util.CertMap;
import rs.ac.uns.ftn.xmlbsep.ws.PoDocument;
import rs.ac.uns.ftn.xmlbsep.ws.PoException;
import rs.ac.uns.ftn.xmlbsep.ws.messageid.MessageIdDocument_MessageIdDocumentPort_Client;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.Handler;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Consumes({"application/xml", "application/json"})
@Produces({"application/xml"})
@Path("/partneri/{partnerId}/fakture")
public class InvoiceController {

    @EJB
    private InvoiceDaoLocal invoiceDao;
    @EJB
    private PartnerDaoLocal partnerDao;
    @EJB
    private InvoiceFactory invoiceFactory;
    @EJB
    private ConfigDao configDao;

    @Context
    private HttpServletRequest request;

    @POST
//    @ValidXMLSchema(value = "/xsd/invoice.xsd", clazz = Invoice.class)
    public Response create(Invoice invoice, @PathParam("partnerId") String partnerId, @Context UriInfo uriInfo) throws Throwable {
        if (!partnerDao.isPartner(partnerId)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        Invoice retVal = null;

        try {
            invoice.setState("partner");
            retVal = invoiceDao.persist(invoice);
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        }
        createPayment(invoice);
        return Response.status(Response.Status.CREATED).contentLocation(uriInfo.getAbsolutePathBuilder().path(String.valueOf(retVal.getId())).build()).build();
    }

    @POST
//    @ValidXMLSchema(value = "/xsd/invoice.xsd", clazz = Invoice.class)
    @HasPermission("create.invoice")
    @Path("/create")
    public Response store(Invoice invoice, @PathParam("partnerId") String partnerId, @Context UriInfo uriInfo) throws Throwable {
        if (!partnerDao.isPartner(partnerId)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        Invoice retVal = null;

        User user = (User) request.getSession().getAttribute("user");
        if (user.hasRole("employee") && invoice.getInvoiceHeader().getPrice().getTotal().longValueExact() <= 1000) {
            invoice.setState(InvoiceState.BOSS.toString());
        } else if (invoice.getInvoiceHeader().getPrice().getTotal().longValueExact() > 1000 && !user.hasRole("director")) {
            invoice.setState(InvoiceState.DIRECTOR.toString());
        }

        try {
            retVal = invoiceDao.persist(invoice);
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        }

        return Response.status(Response.Status.CREATED).contentLocation(uriInfo.getAbsolutePathBuilder().path(String.valueOf(retVal.getId())).build()).build();
    }

    @PUT
//    @ValidXMLSchema(value = "/xsd/invoice.xsd", clazz = Invoice.class)
    @HasPermission("approve.invoice")
    @Path("/approve/{invoiceId}")
    public Response approve(@PathParam("invoiceId") String invoiceId, @PathParam("partnerId") String partnerId, @Context UriInfo uriInfo) throws Throwable {
        if (!partnerDao.isPartner(partnerId)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        Invoice invoice = invoiceDao.findInvoiceBy(partnerId, invoiceId);

        if (invoice == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Invoice retVal = null;

        User user = (User) request.getSession().getAttribute("user");
        if (invoice.getInvoiceHeader().getPrice().getTotal().longValueExact() > 1000 && !user.hasRole("director")) {
            invoice.setState(InvoiceState.DIRECTOR.toString());
        } else {
            invoice.setState(InvoiceState.PARTNER.toString());
            // send invoice to partner's rest (create endpoint for this)
        }

        try {
            retVal = invoiceDao.merge(invoice, Long.valueOf(invoice.getId()));
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        }

        return Response.status(Response.Status.OK).contentLocation(uriInfo.getAbsolutePathBuilder().path(String.valueOf(retVal.getId())).build()).build();
    }

    @DELETE
    @HasPermission("reject.invoice")
    @Path("/{invoiceId}")
    public Response reject(@PathParam("partnerId") String partnerId, @PathParam("invoiceId") int invoiceId) throws Exception {
        if (!partnerDao.isPartner(partnerId)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        try {
            invoiceDao.remove((long) invoiceId);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
//    @HasPermission("read.invoice")
    @Path("/pending")
    public Response getPendingInvoices(@PathParam("partnerId") String partnerId) throws Exception {
        if (!partnerDao.isPartner(partnerId)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        List<Invoice> invoices = null;
        boolean notFound = false;
        ResultWrapper wrapper = new ResultWrapper();

        try {
            invoices = invoiceDao.findAllWherePartnersId(partnerId);
            if (invoices.size() == 0) {
                notFound = true;
            }
            wrapper.setResult(invoices);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return notFound ? Response.status(Response.Status.NOT_FOUND).build() : Response.status(Response.Status.OK).entity(wrapper).build();
    }

    @GET
    @HasPermission("read.invoice")
    @Path("/sent")
    public Response getSentInvoices(@PathParam("partnerId") String partnerId) throws Exception {
        if (!partnerDao.isPartner(partnerId)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        List<Invoice> invoices = null;
        boolean notFound = false;
        ResultWrapper wrapper = new ResultWrapper();

        try {
            invoices = invoiceDao.findSentWherePartnersId(partnerId);
            if (invoices.size() == 0) {
                notFound = true;
            }
            wrapper.setResult(invoices);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return notFound ? Response.status(Response.Status.NOT_FOUND).build() : Response.status(Response.Status.OK).entity(wrapper).build();
    }

    @GET
    @HasPermission("read.invoice")
    public Response getReceivedInvoices(@PathParam("partnerId") String partnerId) throws Exception {
        if (!partnerDao.isPartner(partnerId)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        List<Invoice> invoices = null;
        boolean notFound = false;
        ResultWrapper wrapper = new ResultWrapper();

        try {
            invoices = invoiceDao.findReceivedWherePartnersId();
            if (invoices.size() == 0) {
                notFound = true;
            }
            wrapper.setResult(invoices);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return notFound ? Response.status(Response.Status.NOT_FOUND).build() : Response.status(Response.Status.OK).entity(wrapper).build();
    }

    @GET
    @HasPermission("read.invoice")
    @Path("/pending/{state}")
    public Response getInvoicesByState(@PathParam("partnerId") String partnerId, @PathParam("state") String state) throws Exception {
        if (!partnerDao.isPartner(partnerId)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        List<Invoice> invoices = null;
        boolean notFound = false;
        ResultWrapper wrapper = new ResultWrapper();
        InvoiceState invoiceState;

        try {
            invoiceState = InvoiceState.valueOf(state.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        try {
            invoices = invoiceDao.findAllWherePartnersId(partnerId, invoiceState);
            if (invoices.size() == 0) {
                notFound = true;
            }
            wrapper.setResult(invoices);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return notFound ? Response.status(Response.Status.NOT_FOUND).build() : Response.status(Response.Status.OK).entity(wrapper).build();
    }

    @GET
    @Path("/{invoiceId}")
    @HasPermission("read.invoice")
    public Response getInvoice(@PathParam("partnerId") String partnerId, @PathParam("invoiceId") int invoiceId) throws Exception {
        if (!partnerDao.isPartner(partnerId)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Invoice retVal = null;
        try {
            retVal = invoiceDao.findInvoiceBy(partnerId, String.valueOf(invoiceId));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return retVal == null ? Response.status(Response.Status.NOT_FOUND).build() : Response.status(Response.Status.OK).entity(retVal).build();
    }

    @PUT
    @Path("/{invoiceId}")
    @HasPermission("update.invoice")
    public Response update(Invoice invoice, @PathParam("partnerId") String partnerId, @PathParam("invoiceId") int invoiceId) throws Throwable {
        if (!partnerDao.isPartner(partnerId)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        Invoice oldInvoice = invoiceDao.findInvoiceBy(partnerId, String.valueOf(invoiceId));

        if (oldInvoice == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {
            invoiceFactory.merge(invoice, oldInvoice);
            invoiceDao.merge(oldInvoice, Long.valueOf(invoice.getId()));
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        }
        return Response.status(Response.Status.OK).build();
    }

    private void createPayment(Invoice invoice) {
        PaymentOrder paymentOrder = new PaymentOrder();
        try {
            paymentOrder.setMessageId(MessageIdDocument_MessageIdDocumentPort_Client.getMessageId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        paymentOrder.setCurrencyCode(invoice.getInvoiceHeader().getPayment().getCurrency().getValue());
        paymentOrder.setCurrencyDate(invoice.getInvoiceHeader().getPayment().getCurrency().getDate());
        paymentOrder.setOrderDate(invoice.getInvoiceHeader().getBill().getDate());
        paymentOrder.setPaymentPurpose("Placanje usluga");
        paymentOrder.setDebtor(invoice.getInvoiceHeader().getBuyer().getName());
        paymentOrder.setCreditor(invoice.getInvoiceHeader().getSupplier().getName());
        paymentOrder.setAmount(invoice.getInvoiceHeader().getPrice().getTotal());

        AccountDetails creditorAccountDetails = new AccountDetails();
        creditorAccountDetails.setModel(97);
        creditorAccountDetails.setAccountNumber(invoice.getInvoiceHeader().getPayment().getAccountNumber());
        creditorAccountDetails.setReferenceNumber("referenceNumber0");

        AccountDetails debtorAccountDetails = new AccountDetails();
        debtorAccountDetails.setModel(97);
//        debtorAccountDetails.setAccountNumber("223-2222222222222-22");
        debtorAccountDetails.setAccountNumber(configDao.get().getAccounts().get(0));
        debtorAccountDetails.setReferenceNumber("referenceNumber0");

        paymentOrder.setCreditorAccountDetails(creditorAccountDetails);
        paymentOrder.setDebtorAccountDetails(debtorAccountDetails);

        paymentOrder.setUrgent(false);
        System.out.println("InvoiceController.createPayment");
        sendPaymentOrder(paymentOrder);
    }

    private void sendPaymentOrder(PaymentOrder paymentOrder) {
        try {
            URL wsdl = new URL("http://localhost:8080/banka2/services/PoDocument?wsdl");

            QName serviceName = new QName("http://www.ftn.uns.ac.rs/xws/ws/po", "PoDocumentService");
            QName portName = new QName("http://www.ftn.uns.ac.rs/xws/ws/po", "PoDocumentPort");

            Service service = Service.create(wsdl, serviceName);

            PoDocument poService = service.getPort(portName, PoDocument.class);

            SecMessageHandler msg = new SecMessageHandler();
            ClientCryptoHandler crypto = new ClientCryptoHandler();
            ClientSignatureHandler sign = new ClientSignatureHandler();

            @SuppressWarnings("rawtypes")
            List<Handler> handlerChain = new ArrayList<Handler>();
            handlerChain.add(msg);
            handlerChain.add(sign);
            handlerChain.add(crypto);

            ((BindingProvider) poService).getBinding().setHandlerChain(handlerChain);

            CertMap.add(paymentOrder, "banka2");

            try {
                poService.paymentOrderHandle(paymentOrder);
            } catch (PoException e) {
                System.out.println(e.getFaultInfo());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
