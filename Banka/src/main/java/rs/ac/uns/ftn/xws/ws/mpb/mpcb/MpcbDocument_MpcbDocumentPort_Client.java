package rs.ac.uns.ftn.xws.ws.mpb.mpcb;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.Handler;

import rs.ac.uns.ftn.xws.dao.PaymentOrderDataDao;
import rs.ac.uns.ftn.xws.generated.cmn.AccountDetails;
import rs.ac.uns.ftn.xws.generated.cmn.BankDetails;
import rs.ac.uns.ftn.xws.generated.cmn.Message;
import rs.ac.uns.ftn.xws.generated.mp.Mt102;
import rs.ac.uns.ftn.xws.generated.mp.Mt102.Payments;
import rs.ac.uns.ftn.xws.generated.mp.Mt102Payment;
import rs.ac.uns.ftn.xws.generated.mp.Mt103;
import rs.ac.uns.ftn.xws.generated.po.PaymentOrder;
import rs.ac.uns.ftn.xws.handler.client.mpcb.ClientCryptoHandler;
import rs.ac.uns.ftn.xws.handler.client.mpcb.ClientSignatureHandler;
import rs.ac.uns.ftn.xws.misc.BankConstants;
import rs.ac.uns.ftn.xws.misc.Mt102Util;
import rs.ac.uns.ftn.xws.ws.mpb.bankdetails.BdDocument_BdDocumentPort_Client;
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
			
			//ClientReplayAttackHandler attack = new ClientReplayAttackHandler();
			ClientCryptoHandler crypto = new ClientCryptoHandler();
			ClientSignatureHandler sing = new ClientSignatureHandler();
			
			@SuppressWarnings("rawtypes")
			List<Handler> handlerChain = new ArrayList<Handler>();
			//handlerChain.add(attack);
			handlerChain.add(crypto);
			handlerChain.add(sing);
			
			((BindingProvider) mpcbService).getBinding().setHandlerChain(handlerChain);

			Message message = null;
			
			Map<String, List<PaymentOrder>> mt102Map = Mt102Util.getPaymentOrders();
			
			String myBankAccountNumber = BankConstants.BANK_ACCOUNT_NUMBER;
			
			BankDetails debtorBankDetails = new BankDetails();
			debtorBankDetails = BdDocument_BdDocumentPort_Client.getBankDetails(myBankAccountNumber.substring(0,3));
//			//quick test
//			debtorBankDetails.setBankClearingAccountNumber(myBankAccountNumber);
//			debtorBankDetails.setSwiftCode("ABC");
			
			int i=0; // quick test
			for (String creditorBankCode : mt102Map.keySet()) {
				BankDetails creditorBankDetails = new BankDetails();
				creditorBankDetails = BdDocument_BdDocumentPort_Client.getBankDetails(creditorBankCode);
				//quick test
//				creditorBankDetails.setBankClearingAccountNumber(creditorBankCode+i);
//				creditorBankDetails.setSwiftCode(creditorBankCode+i);
				
				message = MessageIdDocument_MessageIdDocumentPort_Client.getMessageId();
				Mt102 newMt102 = Mt102Util.createMt102(creditorBankDetails, debtorBankDetails, message);
				
				//newMt102.setCertificateRef(message.getCertificateRef()); setuje se u prethodnoj metodi
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
