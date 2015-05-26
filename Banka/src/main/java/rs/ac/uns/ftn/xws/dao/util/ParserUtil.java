package rs.ac.uns.ftn.xws.dao.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;
//import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerConfigurationException;
//import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
//import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
//import org.w3c.dom.Node;
////import org.w3c.dom.Document;
import org.xml.sax.InputSource;
//import org.xml.sax.SAXException;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import rs.ac.uns.ftn.xws.generated.bs.Statement;
import rs.ac.uns.ftn.xws.generated.cmn.Payment;
import rs.ac.uns.ftn.xws.xml2java.Foo;

public class ParserUtil {

	public static void main(String[] args) throws JAXBException,
			FileNotFoundException {

		InputStream is;
//		is = new FileInputStream(
//				"C:/Users/Bandjur/Desktop/Workspace/XWS-BSEP-PI/XWS/NiceHead/Banka/src/main/resources/bs.xml");

		is = new FileInputStream(
				"C:/Users/Bandjur/Desktop/Workspace/XWS-BSEP-PI/XWS/NiceHead/Banka/src/main/resources/paymentTest.xml");
		
		try {
//			Statement newOrder = Foo.unmarshall(Statement.class, is);
			Payment newOrder = Foo.unmarshall(Payment.class, is);
			System.out.println("...");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("FUCK YEAH");
	}

	public static List<String> getPayments(String input) {
		List<String> retList = new ArrayList<String>();

		String[] parts = input.split("</bsb:payment>");
		for (String string : parts) {
			retList.add(string + "</bsb:payment>");
		}

		// retList = new ArrayList<String>(Arrays.asList(parts));
		return retList;
	}

	// public static Document StringToXML(String xmlRecord)
	// throws ParserConfigurationException, SAXException, IOException {
	// DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	// DocumentBuilder builder = factory.newDocumentBuilder();
	// Document d1 = (Document) builder
	// .parse(new InputSource(new StringReader(xmlRecord)));
	//
	// return d1;
	// }

	public static Document StringToXML(String xmlRecord) {
		Document doc = null;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();

			try {
				doc = builder
						.parse(new InputSource(new StringReader(xmlRecord)));
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		Transformer transformer;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
			Result output = new StreamResult(new File(
					"C:/Users/Bandjur/Desktop/test.xml"));
			Source input = new DOMSource(doc);
			transformer.transform(input, output);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		return doc;
	}

	public static void test(Document doc) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Source xmlSource = new DOMSource(doc);
		Result outputTarget = new StreamResult(outputStream);

		try {
			TransformerFactory.newInstance().newTransformer()
					.transform(xmlSource, outputTarget);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
		InputStream is = new ByteArrayInputStream(outputStream.toByteArray());
		//
//		Order newOrder = Foo.unmarshall(Order.class, is);

	}

	public static List<Payment> transformStringsIntoJAXB(String input) {
//	public static List<rs.ac.uns.ftn.xws.domain.Payment> transformStringsIntoJAXB(String input) {
//		List<rs.ac.uns.ftn.xws.domain.Payment> retList = new ArrayList<rs.ac.uns.ftn.xws.domain.Payment>();
		List<Payment> retList = new ArrayList<Payment>();
		List<String> list = getPayments(input);
		
		for (String xmlRecord : list) {
			Document newDocument = StringToXML(xmlRecord);

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			Source xmlSource = new DOMSource(newDocument);
			Result outputTarget = new StreamResult(outputStream);
			try {
				TransformerFactory.newInstance().newTransformer()
						.transform(xmlSource, outputTarget);
			} catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerFactoryConfigurationError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			InputStream is = new ByteArrayInputStream(
					outputStream.toByteArray());

			try {
				Payment payment = XmlHelper.unmarshall(is,Payment.class);
				retList.add(payment);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			Order newOrder = Foo.unmarshall(Order.class, is);

//			retList.add(newOrder);
		}

		return retList;
	}

}
