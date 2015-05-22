package rs.ac.uns.ftn.xws.xml2java;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Foo {

	@SuppressWarnings("unchecked")
	public static <T> T unmarshall(Class<T> clazz, InputStream xmlStream) {
		T ret = null;

		try {
			JAXBContext context = JAXBContext
					.newInstance(clazz.getPackage().getName());

			Unmarshaller unmarshaller = context.createUnmarshaller();

			ret = (T) unmarshaller.unmarshal(xmlStream);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return ret;
	}

	private Foo() {
	}
}
