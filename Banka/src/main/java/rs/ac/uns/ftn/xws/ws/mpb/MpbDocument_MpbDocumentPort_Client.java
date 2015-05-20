package rs.ac.uns.ftn.xws.ws.mpb;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import rs.ac.uns.ftn.xws.generated.RtgsConfirmMessage;

public final class MpbDocument_MpbDocumentPort_Client {

	private MpbDocument_MpbDocumentPort_Client() {
	}

	public static void main(String args[]) throws java.lang.Exception {
		try {
			URL wsdl = new URL("http://localhost:8080/banka/services/MpbDocument?wsdl");

			QName serviceName = new QName(
					"http://www.ftn.uns.ac.rs/xws/ws/mpb", "MpbDocumentService");
			QName portName = new QName("http://www.ftn.uns.ac.rs/xws/ws/mpb",
					"MpbDocumentPort");

			Service service = Service.create(wsdl, serviceName);

			MpbDocument mpbService = service.getPort(portName,
					MpbDocument.class);

			mpbService.rtgsConfirm(new RtgsConfirmMessage());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
