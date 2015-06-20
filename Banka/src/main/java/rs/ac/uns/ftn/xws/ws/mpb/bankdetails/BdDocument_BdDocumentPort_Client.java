package rs.ac.uns.ftn.xws.ws.mpb.bankdetails;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import rs.ac.uns.ftn.xws.generated.cmn.BankDetails;

public final class BdDocument_BdDocumentPort_Client {

	public static BankDetails getBankDetails(String bankCode) throws Exception {
		BankDetails bd = null;
		BdDocument bdService = null;
		
		URL wsdl = new URL(
				"http://localhost:8081/cb/services/BdDocument?wsdl");

		QName serviceName = new QName(
				"http://www.ftn.uns.ac.rs/xws/ws/bankDetails",
				"BdDocumentService");
		QName portName = new QName(
				"http://www.ftn.uns.ac.rs/xws/ws/bankDetails",
				"BdDocumentPort");

		Service service = Service.create(wsdl, serviceName);

		bdService = service.getPort(portName, BdDocument.class);
		bd = bdService.getBankDetails(bankCode);

		System.out.println(bd.getSwiftCode() + "   "
				+ bd.getBankClearingAccountNumber());
		
		return bd;
	}
}
