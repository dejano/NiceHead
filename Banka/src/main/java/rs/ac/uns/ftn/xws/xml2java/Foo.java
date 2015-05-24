package rs.ac.uns.ftn.xws.xml2java;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

public class Foo {

	@SuppressWarnings("unchecked")
	public static <T> T unmarshall(Class<T> clazz, InputStream xmlStream)
			throws Exception {
		T ret = null;

		Source source = new StreamSource(xmlStream);

		JAXBContext context = JAXBContext.newInstance(clazz.getPackage()
				.getName());

		Unmarshaller unmarshaller = context.createUnmarshaller();

		JAXBElement<T> root = unmarshaller.unmarshal(source, clazz);

		return root.getValue();
	}

	private Foo() {
	}
}
