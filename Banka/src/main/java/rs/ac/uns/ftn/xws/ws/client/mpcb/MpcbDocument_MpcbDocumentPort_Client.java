package rs.ac.uns.ftn.xws.ws.client.mpcb;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import rs.ac.uns.ftn.xws.dao.CompanyDataDao;
import rs.ac.uns.ftn.xws.generated.cmn.BankDetails;
import rs.ac.uns.ftn.xws.generated.mp.Mt102;
import rs.ac.uns.ftn.xws.generated.po.PaymentOrder;
import rs.ac.uns.ftn.xws.misc.BankConstants;
import rs.ac.uns.ftn.xws.misc.CertMap;
import rs.ac.uns.ftn.xws.misc.Mt102Util;
import rs.ac.uns.ftn.xws.ws.client.bankDetails.BdDocument_BdDocumentPort_Client;
import rs.ac.uns.ftn.xws.ws.client.messageid.MessageIdDocument_MessageIdDocumentPort_Client;

public final class MpcbDocument_MpcbDocumentPort_Client {

	private MpcbDocument_MpcbDocumentPort_Client() {
	}

	public static void main(String args[]) throws java.lang.Exception {
		try {
			URL wsdl = new URL("http://localhost:8080/cb/services/MpcbDocument?wsdl");

			QName serviceName = new QName("http://www.ftn.uns.ac.rs/xws/ws/mpcb",
					"MpcbDocumentService");
			QName portName = new QName("http://www.ftn.uns.ac.rs/xws/ws/mpcb", "MpcbDocumentPort");

			Service service = Service.create(wsdl, serviceName);

			MpcbDocument mpcbService = service.getPort(portName, MpcbDocument.class);

			String messageId = "";

			Map<String, List<PaymentOrder>> mt102Map = Mt102Util.getPaymentOrders();

			String myBankAccountNumber = BankConstants.BANK_CODE;

			BankDetails debtorBankDetails = new BankDetails();
			debtorBankDetails = BdDocument_BdDocumentPort_Client.getBankDetails(myBankAccountNumber
					.substring(0, 3));

			for (String creditorBankCode : mt102Map.keySet()) {
				BankDetails creditorBankDetails = new BankDetails();
				creditorBankDetails = BdDocument_BdDocumentPort_Client
						.getBankDetails(creditorBankCode);

				messageId = MessageIdDocument_MessageIdDocumentPort_Client.getMessageId();
				Mt102 newMt102 = Mt102Util.createMt102(creditorBankCode, creditorBankDetails,
						debtorBankDetails, messageId);

				// slanje mt102 poruke centralnoj banci
				CertMap.add(newMt102, "cb");
				mpcbService.clearingRequest(newMt102);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
