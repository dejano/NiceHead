package rs.ac.uns.ftn.xws.ws.bankdetails;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import rs.ac.uns.ftn.xws.generated.cmn.BankDetails;

public final class BdDocument_BdDocumentPort_Client {

	public static void main(String args[]) {
		BdDocument bdService = null;

		try {
			URL wsdl = new URL(
					"http://localhost:8080/cb/services/BdDocument?wsdl");

			QName serviceName = new QName(
					"http://www.ftn.uns.ac.rs/xws/ws/bankDetails",
					"BdDocumentService");
			QName portName = new QName(
					"http://www.ftn.uns.ac.rs/xws/ws/bankDetails",
					"BdDocumentPort");

			Service service = Service.create(wsdl, serviceName);

			bdService = service.getPort(portName, BdDocument.class);
			try {
				BankDetails bd = bdService.getBankDetails("88");

				System.out.println(bd.getSwiftCode() + "   "
						+ bd.getBankClearingAccountNumber());
			} catch (NoBankCodeException e) {
				System.out.println(e.getFaultInfo());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
