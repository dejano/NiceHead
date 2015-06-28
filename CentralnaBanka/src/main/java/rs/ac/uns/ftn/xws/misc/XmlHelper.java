package rs.ac.uns.ftn.xws.misc;

import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.helpers.DefaultHandler;

public class XmlHelper {

	public static <T> T unmarshall(InputStream stream, Class<T> T) throws Exception {
		Source source = new StreamSource(stream);

		JAXBContext ctx = JAXBContext.newInstance(T.getPackage().getName());
		Unmarshaller unmarshaller = ctx.createUnmarshaller();

		JAXBElement<T> root = unmarshaller.unmarshal(source, T);

		return root.getValue();
	}

	public static <T> String marshall(T obj) {
		StringWriter sw = new StringWriter();

		Class<?> objClazz = obj.getClass();
		try {
			JAXBElement<T> el = getJaxbElement(obj, objClazz);

			JAXBContext ctx = JAXBContext.newInstance(objClazz.getPackage().getName());

			Marshaller marshaller = ctx.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

			marshaller.marshal(el, sw);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sw.toString();
	}

	public static <T> String marshall(T obj, Class<?> objFactoryClazz, String className) {
		StringWriter sw = new StringWriter();

		try {
			JAXBElement<T> el = getJaxbElement(obj, objFactoryClazz, className);

			JAXBContext ctx = JAXBContext.newInstance(objFactoryClazz.getPackage().getName());

			Marshaller marshaller = ctx.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

			marshaller.marshal(el, sw);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sw.toString();
	}

	public static <T> boolean validate(T obj, String schemaLocation) {
		boolean ret = true;

		Class<?> objClazz = obj.getClass();

		try {
			JAXBElement<T> el = getJaxbElement(obj, objClazz);

			JAXBContext jaxbContext = JAXBContext.newInstance(objClazz.getPackage().getName());

			SchemaFactory schemaFactory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new File(schemaLocation));

			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			marshaller.setSchema(schema);

			marshaller.marshal(el, new DefaultHandler());
		} catch (JAXBException e) {
			e.printStackTrace();
			ret = false;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	@SuppressWarnings("unchecked")
	public static <T> JAXBElement<T> getJaxbElement(T obj, Class<?> objClazz)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IllegalArgumentException,
			InvocationTargetException {
		Class<?> objFactoryClazz = Class
				.forName(objClazz.getPackage().getName() + ".ObjectFactory");

		Object objectFactory = objFactoryClazz.newInstance();

		Method createMethod = objFactoryClazz.getMethod("create" + objClazz.getSimpleName(),
				objClazz);

		return (JAXBElement<T>) createMethod.invoke(objectFactory, obj);
	}

	@SuppressWarnings("unchecked")
	public static <T> JAXBElement<T> getJaxbElement(T obj, Class<?> objFactoryClazz,
			String objectName) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, NoSuchMethodException, SecurityException,
			IllegalArgumentException, InvocationTargetException {
		Object objectFactory = objFactoryClazz.newInstance();

		Method createMethod = objFactoryClazz.getMethod("create" + objectName, obj.getClass());

		return (JAXBElement<T>) createMethod.invoke(objectFactory, obj);
	}

	private XmlHelper() {
	}
}
