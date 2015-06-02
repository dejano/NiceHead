package rs.ac.uns.ftn.xws.ws.mpb.mpcb;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import rs.ac.uns.ftn.xws.dao.PaymentOrderDataDao;
import rs.ac.uns.ftn.xws.generated.cmn.AccountDetails;
import rs.ac.uns.ftn.xws.generated.cmn.BankDetails;
import rs.ac.uns.ftn.xws.generated.mp.Mt102;
import rs.ac.uns.ftn.xws.generated.mp.Mt102.Payments;
import rs.ac.uns.ftn.xws.generated.mp.Mt102Payment;
import rs.ac.uns.ftn.xws.generated.mp.Mt103;
import rs.ac.uns.ftn.xws.generated.po.PaymentOrder;
import rs.ac.uns.ftn.xws.misc.BankConstants;
import rs.ac.uns.ftn.xws.misc.Mt102Util;
import rs.ac.uns.ftn.xws.ws.mpb.messageid.MessageIdDocument_MessageIdDocumentPort_Client;
import rs.ac.uns.ftn.xws.ws.mpb.swiftcode.SwiftCodeDocument_SwiftCodeDocumentPort_Client;

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
			
			
			String messageId = "";
			
			Map<String, List<PaymentOrder>> mt102Map = Mt102Util.getPaymentOrders();
			
			String myBankAccountNumber = BankConstants.BANK_ACCOUNT_NUMBER;
			
			BankDetails debtorBankDetails = new BankDetails();
			debtorBankDetails.setBankClearingAccountNumber(myBankAccountNumber);
			debtorBankDetails.setSwiftCode(SwiftCodeDocument_SwiftCodeDocumentPort_Client.getSwiftCode(myBankAccountNumber));
			
			
			for (String creditorBankCode : mt102Map.keySet()) {
				BankDetails creditorBankDetails = new BankDetails();
				// TODO @Nikola42 getSwiftCode treba da se radi na osnovu bankCode a ne na osnovu clearingAccountNumbera?
				//acceptorBankDetails.setSwiftCode(SwiftCodeDocument_SwiftCodeDocumentPort_Client.getSwiftCode(acceptorBankCode));
				//acceptorBankDetails.setBankClearingAccountNumber(value);
				messageId = MessageIdDocument_MessageIdDocumentPort_Client.getMessageId();
				
				Mt102 newMt102 = Mt102Util.createMt102(creditorBankDetails, debtorBankDetails, messageId);
				
				//slanje mt102 poruke centralnoj banci
				mpcbService.clearingRequest(newMt102);
			}
			// izbrisi paymentOrdere nakon slanja Mt102
			// trebalo bi da postoji funkcija koja brise odredjene paymente i 
			// da se izvrsavanje ove foreach petlje radi kao transkacija zbog mogucnosti rollbacka
			PaymentOrderDataDao.ClearPaymentOrders();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
