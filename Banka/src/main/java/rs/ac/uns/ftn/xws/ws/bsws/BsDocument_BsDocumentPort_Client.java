
package rs.ac.uns.ftn.xws.ws.bsws;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.net.URL;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import rs.ac.uns.ftn.xws.generated.bs.Statement;
import rs.ac.uns.ftn.xws.generated.bs.StatementRequest;

/**
 * This class was generated by Apache CXF 2.6.5
 * 2015-05-24T20:21:22.704+02:00
 * Generated source version: 2.6.5
 * 
 */
public final class BsDocument_BsDocumentPort_Client {

    private static final QName SERVICE_NAME = new QName("http://www.ftn.uns.ac.rs/xws/ws/bsws", "BsDocumentService");

    private BsDocument_BsDocumentPort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        
    	URL wsdl = new URL("http://localhost:8080/bs/services/BsDocument?wsdl");
//		URL wsdl = new URL(null, "http://localhost:8080/cb/services/BsDocument?wsdl", new sun.net.www.protocol.http.Handler());
		QName serviceName = new QName(
				"http://www.ftn.uns.ac.rs/xws/ws/bsws",
				"BsDocumentService");
		QName portName = new QName(
				"http://www.ftn.uns.ac.rs/xws/ws/bsws", "BsDocumentPort");
		
//		URL url = new URL("http://localhost:8080/cb/services/BsDocument?wsdl");
//	    QName qname = new QName("http://localhost:8080/cb/services/BsDocument?wsdl", "BsDocumentService");
//	    Service service = Service.create(url, qname);

		Service service = Service.create(wsdl, serviceName);
		BsDocument bsd = service.getPort(portName, BsDocument.class);

		System.out.println("bs call");
		StatementRequest bsRequest = new StatementRequest();
		bsRequest.setAccountNumber("310-0000000000111-83");
		bsRequest.setStatementNumber(1);
		
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(new Date());
		XMLGregorianCalendar date = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		bsRequest.setDate(date);
		
		Statement statement = bsd.getStatement(bsRequest);
		
		System.out.println("statment account number : " + statement.getAccountNumber());
		System.out.println("KRAJ KLIENTA");
    	
    }

}
