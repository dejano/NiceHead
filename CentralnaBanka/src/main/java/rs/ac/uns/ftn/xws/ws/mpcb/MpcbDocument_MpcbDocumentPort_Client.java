package rs.ac.uns.ftn.xws.ws.mpcb;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import rs.ac.uns.ftn.xws.generated.AccountDetails;
import rs.ac.uns.ftn.xws.generated.BankDetails;
import rs.ac.uns.ftn.xws.generated.Mt102;
import rs.ac.uns.ftn.xws.generated.Mt103;
import rs.ac.uns.ftn.xws.generated.Payment;
import rs.ac.uns.ftn.xws.generated.Mt102.Payments;

public final class MpcbDocument_MpcbDocumentPort_Client {

	private MpcbDocument_MpcbDocumentPort_Client() {
	}

	public static void main(String args[]) {
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

			// test clearing
			Mt103 mt103 = new Mt103();
			mt103.setAmount(new BigDecimal(22));

			BankDetails bdd = new BankDetails();
			bdd.setSwiftCode("CONARS23");
			mt103.setDebtorBankDetails(bdd);

			BankDetails bdc = new BankDetails();
			bdc.setSwiftCode("CONARS22");
			mt103.setCreditorBankDetails(bdc);

			mpcbService.rtgsRequest(mt103);
			
			// test rtgs
			Mt102 mt102 = new Mt102();
			mt102.setTotalAmount(new BigDecimal(9.2));
			mt102.setPayments(new Payments());
			
			BankDetails bd1 = new BankDetails();
			bd1.setSwiftCode("CONARS23");
			mt102.setDebtorBankDetails(bd1);
			
			BankDetails bd2 = new BankDetails();
			bd2.setSwiftCode("CONARS22");
			mt102.setCreditorBankDetails(bd2);
			
			AccountDetails ad1 = new AccountDetails();
			ad1.setAccountNumber("123111111111111111");

			AccountDetails ad2 = new AccountDetails();
			ad2.setAccountNumber("123111111111111111");

			Payment p1 = new Payment();
			p1.setCreditorAccountDetails(ad1);
			p1.setAmount(new BigDecimal(5.1));
			mt102.getPayments().getPayments().add(p1);

			Payment p2 = new Payment();
			p2.setCreditorAccountDetails(ad2);
			p2.setAmount(new BigDecimal(4.1));
			mt102.getPayments().getPayments().add(p2);
			
			mpcbService.clearingRequest(mt102);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (MpException e) {
			System.out.println("exception " + e.getFaultInfo().name());
		}
	}

}
