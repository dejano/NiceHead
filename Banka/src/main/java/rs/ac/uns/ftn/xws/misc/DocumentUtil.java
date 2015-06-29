package rs.ac.uns.ftn.xws.misc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class DocumentUtil {

	public static void printDocument(Document document) throws Exception {
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		StreamResult result = new StreamResult(System.out);
		transformer.transform(new DOMSource(document), result);
	}

	public static void printSource(Source source) throws Exception {
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		StreamResult result = new StreamResult(System.out);
		transformer.transform(source, result);
	}

	public static Document convertToDocument(Source request) {
		Document r = null;
		try {
			DocumentBuilder db = getDocumentBuilder();
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "no");
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			StreamResult result = new StreamResult(baos);
			transformer.transform(request, result);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			r = db.parse(bais);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	private static DocumentBuilder getDocumentBuilder() {
		try {
			// Setup document builder
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			docBuilderFactory.setNamespaceAware(true);
			DocumentBuilder builder = docBuilderFactory.newDocumentBuilder();
			return builder;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static int getHashCode(Document node) {
		int ret = -1;

		try {
			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer transformer = transFactory.newTransformer();

			StringWriter buffer = new StringWriter();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.transform(new DOMSource(node), new StreamResult(buffer));

			String str = buffer.toString();

			ret = str.hashCode();
		} catch (TransformerConfigurationException e1) {
			e1.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static <T> Document toDocument(T obj) {
		Document ret = null;

		try {
			Class<?> objClazz = obj.getClass();

			JAXBContext ctx = JAXBContext.newInstance(objClazz.getPackage().getName());

			Marshaller marshaller = ctx.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

			DOMResult res = new DOMResult();
			marshaller.marshal(XmlHelper.getJaxbElement(obj, objClazz), res);
			ret = (Document) res.getNode();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static Document convertStringToDocument(String xmlStr) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xmlStr)));
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
