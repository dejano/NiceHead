package rs.ac.uns.ftn.xws.ws.mpcb;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import rs.ac.uns.ftn.xws.generated.cmn.AccountDetails;
import rs.ac.uns.ftn.xws.generated.cmn.BankDetails;
import rs.ac.uns.ftn.xws.generated.mp.Mt102;
import rs.ac.uns.ftn.xws.generated.mp.Mt102.Payments;
import rs.ac.uns.ftn.xws.generated.mp.Mt102Payment;
import rs.ac.uns.ftn.xws.generated.mp.Mt103;

public final class MpcbDocument_MpcbDocumentPort_Client {

	private MpcbDocument_MpcbDocumentPort_Client() {
	}

	public static void main(String args[]) throws java.lang.Exception {
		try {
			URL wsdl = new URL(
					"http://localhost:8080/cb/services/MpcbDocument?wsdl");

			QName serviceName = new QName(
					"http://www.ftn.uns.ac.rs/xws/ws/mpcb",
					"MpcbDocumentService");
			QName portName = new QName("http://www.ftn.uns.ac.rs/xws/ws/mpcb",
					"MpcbDocumentPort");

			Service service = Service.create(wsdl, serviceName);

			MpcbDocument mpcbService = service.getPort(portName,
					MpcbDocument.class);

//			try {
//				// test rtgs
//				Mt103 mt103 = new Mt103();
//				mt103.setAmount(new BigDecimal(22));
//
//				AccountDetails creditorAccountDetails = new AccountDetails();
//				creditorAccountDetails.setAccountNumber("222-2222222222222-22");
//				mt103.setCreditorAccountDetails(creditorAccountDetails);
//
//				BankDetails bdd = new BankDetails();
//				bdd.setSwiftCode("CONARS23");
//				mt103.setDebtorBankDetails(bdd);
//
//				BankDetails bdc = new BankDetails();
//				bdc.setSwiftCode("CONARS22");
//				mt103.setCreditorBankDetails(bdc);
//
//				mpcbService.rtgsRequest(mt103);
//			} catch (MpException e) {
//				System.out.println("exception " + e.getFaultInfo().name());
//			}
			try {
				// test clearing
				Mt102 mt102 = new Mt102();
				mt102.setMessageId("555");
				mt102.setTotalAmount(new BigDecimal(9.2));
				mt102.setPayments(new Payments());

				BankDetails bd1 = new BankDetails();
				bd1.setSwiftCode("CONARS23");
				mt102.setDebtorBankDetails(bd1);

				BankDetails bd2 = new BankDetails();
				bd2.setSwiftCode("CONARS22");
				mt102.setCreditorBankDetails(bd2);

				AccountDetails ad1 = new AccountDetails();
				ad1.setAccountNumber("222-2222222222222-22");

				AccountDetails ad2 = new AccountDetails();
				ad2.setAccountNumber("223-2222222222222-22");

				Mt102Payment p1 = new Mt102Payment();
				p1.setDebtorAccountDetails(ad1);
				p1.setCreditorAccountDetails(ad2);
				p1.setAmount(new BigDecimal(5.1));
				mt102.getPayments().getPayment().add(p1);

				Mt102Payment p2 = new Mt102Payment();
				p2.setCreditorAccountDetails(ad2);
				p2.setAmount(new BigDecimal(4.1));
				mt102.getPayments().getPayment().add(p2);

				mpcbService.clearingRequest(mt102);
			} catch (MpException e) {
				System.out.println("exception " + e.getFaultInfo().name());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
