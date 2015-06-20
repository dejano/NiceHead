package rs.ac.uns.ftn.xws.dao.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import rs.ac.uns.ftn.xws.generated.bs.Statement;
import rs.ac.uns.ftn.xws.generated.bs.StatementRequest;
import rs.ac.uns.ftn.xws.misc.DocumentUtil;
import rs.ac.uns.ftn.xws.misc.XmlHelper;
import rs.ac.uns.ftn.xws.security.DecryptKEK;
import rs.ac.uns.ftn.xws.security.EncryptKEK;
import rs.ac.uns.ftn.xws.security.SignEnveloped;
import rs.ac.uns.ftn.xws.security.VerifyClientSignatureEnveloped;

public class TestUtil {
	
	public static void main(String[] args) throws Exception {
//		InitXBase();
		securityTest();
	}
	
	public static void securityTest() throws Exception {
		
		StatementRequest bsRequest = new StatementRequest();
		bsRequest.setAccountNumber("111-0000000000000-00");
		bsRequest.setStatementNumber(1);
		
		GregorianCalendar c = DateUtil.convertFromDMY("04-05-2006");
		XMLGregorianCalendar date = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		System.out.println(date.toString());
		bsRequest.setDate(date);
		
		String marshalledObject = XmlHelper.marshall(bsRequest);
		
		Document document = ParserUtil.StringToXML(marshalledObject);
		System.out.println("********* ISPISUJEM ORGINALNI DOKUMENT *********");
		DocumentUtil.printDocument(document);
		
		System.out.println("********* POTPISUJEM BANK STATAMENT *********");
		Document signedDocument = SignEnveloped.signDocument(document);
		DocumentUtil.printDocument(signedDocument);
		
		System.out.println("****** ENKRIPTUJEM BANK STATAMENT********");
		Document encryptedDoc = EncryptKEK.encryptDocument(signedDocument);
		
		System.out.println("****** ISPISUJEM ENCRIPTOVANI DOCUMENT **** ");
		DocumentUtil.printDocument(encryptedDoc);
		
		System.out.println("*****DEKTRIPUTJEM BANK STATEMENT REQUEST******");
		Document decryptedDocument = DecryptKEK.decryptDocument(encryptedDoc);
		
		System.out.println("*****ISPISUJEM BANK STATEMENT REQUEST NAKON DECRYPTA******");
		DocumentUtil.printDocument(decryptedDocument);
		
		System.out.println("***** PROVERAM POTPIS I KIDAM signature xml element ako ga ima******");
		
		boolean signatureValid = VerifyClientSignatureEnveloped.verifySignature(decryptedDocument);
		
		if(!signatureValid) {
			System.out.println("POTPIS NIJE VALIDAN ...");
			return ;
		}
		System.out.println("POTPIS JE VALIDAN ... SKIDAM ODGOVARAJUCI TAG");
		// uklanjanje potpisa
		Element element =  (Element) decryptedDocument.getElementsByTagNameNS("http://www.w3.org/2000/09/xmldsig#", "Signature").item(0);  
		element.getParentNode().removeChild(element);
		
		System.out.println("ISPISUJEM DEKRIPOTVANI ELEMENT SA SKINUTIM POTPISOM");
		DocumentUtil.printDocument(decryptedDocument);
		
//		Statement statement= new Statement();
//		statement.set
		
	}
	
	public static void InitXBase() {
		/* Test CRUD operacija */
		try {
			File file = new File("C:\\Users\\Bandjur\\Desktop\\orders");
			
			RESTUtil.createSchema("orders");
			RESTUtil.createResource("orders", "orderMika2.xml", new FileInputStream(new File(file, "orderMika2.xml")));
			RESTUtil.createResource("orders", "orderMika1.xml", new FileInputStream(new File(file, "orderMika1.xml")));
			System.out.println(RESTUtil.readString(RESTUtil.retrieveResource("//value/text()", "orders", RequestMethod.GET)));
			
			System.out.println("droping table 'orders'");
			RESTUtil.dropSchema("orders");
			System.out.println("table dropped");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//		createSchema("factbook");
//		createResource("factbook", "factbook.xml", new FileInputStream(new File(file, "factbook.xml")));
		
//		printStream(RESTUtil.retrieveResource("(//city/name)[position()= 10 to 15]", "factbook", RequestMethod.GET));
//		//printStream(RESTUtil.retrieveResource("(//city/name)[position()= 10 to 15]", "factbook", RequestMethod.GET));
//		
//		createSchema("firma");
//		createResource("firma", "uplatnica.xml", new FileInputStream(new File(file, "uplatnica_prazna.xml")));
//		
//		printStream(retrieveResource("//ulica/text()", "firma", RequestMethod.GET));
//		
//		updateResource("firma", "uplatnica.xml", new FileInputStream(new File(file, "uplatnica.xml")));
//		
//		printStream(retrieveResource("//ulica/text()", "firma", RequestMethod.GET));
//
//		/* XSD sema za REST query je data u src/main/resources folderu (mora se postovati ova gramatika za slanje zahteva. Nigde se ovaj fajl ne koristi, 
//		 * tu je za prikaz izgleda gramatike zahteva)*/
//		// CDATA zato sto hocemo da se obezbedimo da nas FLWOR izraz ostane u izvornom obliku i tako dodje do servera. Inace bi se mogli parsirati entiteti
//		// npr. u xQUERY se ne koristi < vec ^lt
//		String xml = "<query xmlns='http://basex.org/rest'>"
//				+ 		"<text>"
//				+ 			"<![CDATA[%s]]>"
//				+ 		"</text>"
//				+ 		"<parameter name='wrap' value='yes'/>"
//				+ 		"</query>";
//		String xquery = "for $i in //ulica/text() return string-length($i)";
//		// na mesto %s u CDATA se ugradjuje query string
//		printStream(retrieveResource(String.format(xml, xquery), "firma", RequestMethod.POST));
//		
//		dropSchema("factbook");
//		
//		deleteResource("firma", "uplatnica.xml");
//		dropSchema("firma");
		
		
		
	}

}
