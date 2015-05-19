package rs.ac.uns.ftn.xws.ws.swiftcode;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.5 2015-05-19T10:12:54.714+02:00
 * Generated source version: 2.6.5
 * 
 */
public final class SwiftCodeDocument_SwiftCodeDocumentPort_Client {

	private SwiftCodeDocument_SwiftCodeDocumentPort_Client() {
	}

	public static void main(String args[]) {
		try {
			URL wsdl = new URL(
					"http://localhost:8080/cb/services/SwiftCodeDocument?wsdl");

			QName serviceName = new QName(
					"http://www.ftn.uns.ac.rs/xws/ws/swiftCode",
					"SwiftCodeDocumentService");
			QName portName = new QName(
					"http://www.ftn.uns.ac.rs/xws/ws/swiftCode",
					"SwiftCodeDocumentPort");

			Service service = Service.create(wsdl, serviceName);

			SwiftCodeDocument swiftCodeService = service.getPort(portName,
					SwiftCodeDocument.class);

			System.out.println("swift code call");
			String swiftCode = swiftCodeService
					.getSwiftCode("111111111111111111");

			System.out.println(swiftCode);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NoBankAccountException e) {
			System.out.println("exception");
		}
	}

}
