package rs.ac.uns.ftn.xws.ws.bsws;

import java.net.URL;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import rs.ac.uns.ftn.xws.dao.util.DateUtil;
import rs.ac.uns.ftn.xws.generated.bs.Statement;
import rs.ac.uns.ftn.xws.generated.bs.StatementRequest;

public final class BsDocument_BsDocumentPort_Client {

	private BsDocument_BsDocumentPort_Client() {
	}

	public static void main(String args[]) throws java.lang.Exception {
		URL wsdl = new URL("http://localhost:8081/banka1/services/BsDocument?wsdl");
		QName serviceName = new QName("http://www.ftn.uns.ac.rs/xws/ws/bsws", "BsDocumentService");
		QName portName = new QName("http://www.ftn.uns.ac.rs/xws/ws/bsws", "BsDocumentPort");

		Service service = Service.create(wsdl, serviceName);
		BsDocument bsd = service.getPort(portName, BsDocument.class);

		System.out.println("bs call");
		StatementRequest bsRequest = new StatementRequest();
		bsRequest.setAccountNumber("111-0000000000000-00");
		bsRequest.setStatementNumber(1);

		GregorianCalendar c = DateUtil.convertFromDMY("04-05-2006");
		XMLGregorianCalendar date = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		System.out.println(date.toString());
		bsRequest.setDate(date);

		Statement statement = bsd.getStatement(bsRequest);

		System.out.println("statment account number : " + statement.getAccountNumber());
		System.out.println("KRAJ KLIENTA");
	}
}
