package rs.ac.uns.ftn.xws.dao.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;





//import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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

import org.w3c.dom.Document;
//import org.w3c.dom.Node;
////import org.w3c.dom.Document;
import org.xml.sax.InputSource;
//import org.xml.sax.SAXException;
import org.xml.sax.SAXException;

import rs.ac.uns.ftn.xws.dao.PaymentDataDao;
import rs.ac.uns.ftn.xws.dao.PaymentOrderDataDao;
import rs.ac.uns.ftn.xws.domain.bsb.PaymentData;
import rs.ac.uns.ftn.xws.generated.po.PaymentOrder;
import rs.ac.uns.ftn.xws.misc.XmlHelper;

public class ParserUtil {

	

//	public static List<String> getPayments(String input) {
//		List<String> retList = new ArrayList<String>();
//
//		String[] parts = input.split("</bsb:paymentData>");
//		for (String string : parts) {
//			retList.add(string + "</bsb:paymentData>");
//		}
//
//		// retList = new ArrayList<String>(Arrays.asList(parts));
//		return retList;
//	}

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
		InputStream is = new ByteArrayInputStream(outputStream.toByteArray());

	}

	// TODO @bandjur izbaci PaymentDataDao.getPayments() odnosno napravi generic parsiranje 
	public static List<PaymentData> transformXmlListIntoPaymentDataList(String input) {
		List<PaymentData> retList = new ArrayList<PaymentData>();
		List<String> list = PaymentDataDao.getPayments(input);

		for (String xmlRecord : list) {
			Document newDocument = StringToXML(xmlRecord);

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			Source xmlSource = new DOMSource(newDocument);
			Result outputTarget = new StreamResult(outputStream);
			try {
				TransformerFactory.newInstance().newTransformer()
						.transform(xmlSource, outputTarget);

				InputStream is = new ByteArrayInputStream(
						outputStream.toByteArray());
				PaymentData payment = XmlHelper.unmarshall(is,
						PaymentData.class);

				retList.add(payment);
			} catch (TransformerConfigurationException e) {
				e.printStackTrace();
			} catch (TransformerException e) {
				e.printStackTrace();
			} catch (TransformerFactoryConfigurationError e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return retList;
	}
	
	// TODO @bandjur izbaci PaymentDataDao.getPayments() odnosno napravi generic parsiranje 
		public static List<PaymentOrder> transformXmlListIntoPaymentOrderList(String input) {
			List<PaymentOrder> retList = new ArrayList<PaymentOrder>();
			List<String> list = PaymentOrderDataDao.getPaymentOrdersStrings(input);

			for (String xmlRecord : list) {
				Document newDocument = StringToXML(xmlRecord);

				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				Source xmlSource = new DOMSource(newDocument);
				Result outputTarget = new StreamResult(outputStream);
				try {
					TransformerFactory.newInstance().newTransformer()
							.transform(xmlSource, outputTarget);

					InputStream is = new ByteArrayInputStream(
							outputStream.toByteArray());
					PaymentOrder paymentOrder = XmlHelper.unmarshall(is,
							PaymentOrder.class);

					retList.add(paymentOrder);
				} catch (TransformerConfigurationException e) {
					e.printStackTrace();
				} catch (TransformerException e) {
					e.printStackTrace();
				} catch (TransformerFactoryConfigurationError e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			return retList;
		}

	// <? extends T>
	public static <T> T transformStringIntoJAXBeans(String xmlRecord,
			Class<T> clazz) {
		// <T> retVal = null;
		T retVal = null;
		Document newDocument = StringToXML(xmlRecord);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Source xmlSource = new DOMSource(newDocument);
		Result outputTarget = new StreamResult(outputStream);
		try {
			TransformerFactory.newInstance().newTransformer()
					.transform(xmlSource, outputTarget);

			InputStream is = new ByteArrayInputStream(
					outputStream.toByteArray());
			T retObject = XmlHelper.unmarshall(is, clazz);
			retVal = (retObject != null) ? retObject : retVal;

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retVal;
	}

}
