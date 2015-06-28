package rs.ac.uns.ftn.xws.misc;

import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import rs.ac.uns.ftn.xws.generated.mp.Mt102;

public class SecWrapper {

	public static Document wrap(Document doc) {
		Document ret = null;

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			ret = builder.newDocument();

			Element root = ret.createElement("secMessage");
			ret.appendChild(root);

			Node importedNode = ret.importNode(doc.getFirstChild(), true);

			Element content = ret.createElement("content");
			content.appendChild(importedNode);
			root.appendChild(content);

			String timestampValue = String.valueOf(Calendar.getInstance().getTime().getTime());

			Element token = ret.createElement("token");
			token.appendChild(ret.createTextNode(Integer.toString(DocumentUtil.getHashCode(doc))
					+ timestampValue));
			root.appendChild(token);

			Element timestamp = ret.createElement("timestamp");
			timestamp.appendChild(ret.createTextNode(timestampValue));
			root.appendChild(timestamp);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static Document unwrap(Document secDocument) {
		Document ret = null;

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			ret = builder.newDocument();

			Node contentNode = ret.importNode(secDocument.getElementsByTagName("content").item(0)
					.getFirstChild(), true);

			ret.appendChild(contentNode);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static String getToken(Document secDocument) {
		return secDocument.getElementsByTagName("token").item(0).getTextContent();
	}

	public static long getTimestamp(Document secDocument) {
		return Long.parseLong(secDocument.getElementsByTagName("timestamp").item(0)
				.getTextContent());
	}

	public static void main(String[] args) {
		Mt102 m = new Mt102();
		m.setMessageId("1");

		Document doc = DocumentUtil.toDocument(m);
		Document secDoc = wrap(doc);

		if (DocumentUtil.getHashCode(doc) == DocumentUtil.getHashCode(unwrap(secDoc)))
			System.out.println("!!!");

		System.out.println(((Element) unwrap(secDoc).getFirstChild()).getAttribute("messageId"));
		System.out.println(getToken(secDoc));
		System.out.println(getTimestamp(secDoc));

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(unwrap(secDoc).getFirstChild());
			StreamResult result = new StreamResult(System.out);
			transformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}
