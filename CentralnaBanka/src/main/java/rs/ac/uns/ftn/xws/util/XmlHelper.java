package rs.ac.uns.ftn.xws.util;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

public class XmlHelper {

	public static <T> T unmarshall(InputStream stream, Class<T> T) throws Exception {
		Source source = new StreamSource(stream);

		JAXBContext ctx = JAXBContext.newInstance(T.getPackage().getName());
		Unmarshaller unmarshaller = ctx.createUnmarshaller();

		JAXBElement<T> root = unmarshaller.unmarshal(source, T);

		return root.getValue();
	}

	private XmlHelper() {
	}
}
