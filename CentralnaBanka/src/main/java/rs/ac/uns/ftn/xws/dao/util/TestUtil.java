package rs.ac.uns.ftn.xws.dao.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import rs.ac.uns.ftn.xws.generated.cmn.AccountDetails;
import rs.ac.uns.ftn.xws.generated.cmn.ObjectFactory;
import rs.ac.uns.ftn.xws.misc.DocumentUtil;
import rs.ac.uns.ftn.xws.misc.XmlHelper;
import rs.ac.uns.ftn.xws.security.DecryptKEK;
import rs.ac.uns.ftn.xws.security.EncryptKEK;
import rs.ac.uns.ftn.xws.security.EncryptTest;
import rs.ac.uns.ftn.xws.security.SignEnveloped;
import rs.ac.uns.ftn.xws.security.VerifyClientSignatureEnveloped;

public class TestUtil {

	public static void main(String[] args) throws Exception {
		// InitXBase();
		securityTest();
	}

	public static void securityTest() throws Exception {
		File fXmlFile = new File("C:\\Users\\Bandjur\\Desktop\\payments.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);

		System.out.println("********* ISPISUJEM ORGINALNI DOKUMENT *********");
		DocumentUtil.printDocument(doc);

		System.out.println("****** ENKRIPTUJEM BANK STATAMENT********");
		Document encryptedDoc = EncryptKEK.encryptDocument(doc);

		System.out.println("****** ISPISUJEM ENCRIPTOVANI DOCUMENT **** ");
		DocumentUtil.printDocument(encryptedDoc);

		System.out.println("*****DEKTRIPUTJEM BANK STATEMENT REQUEST******");
		Document decryptedDocument = DecryptKEK.decryptDocument(encryptedDoc);

		System.out
				.println("*****ISPISUJEM BANK STATEMENT REQUEST NAKON DECRYPTA******");
		DocumentUtil.printDocument(decryptedDocument);
	}
}
