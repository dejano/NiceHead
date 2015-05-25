package rs.ac.uns.ftn.xws.util;

import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import rs.ac.uns.ftn.xws.generated.mp.Mt102;

public class XmlHelper {

	public static <T> T unmarshall(InputStream stream, Class<T> clazz)
			throws Exception {
		Source source = new StreamSource(stream);

		JAXBContext ctx = JAXBContext.newInstance(clazz.getPackage().getName());
		Unmarshaller unmarshaller = ctx.createUnmarshaller();

		JAXBElement<T> root = unmarshaller.unmarshal(source, clazz);

		return root.getValue();
	}

	public static <T> String marshall(T obj) {
		StringWriter sw = new StringWriter();

		Class<?> objClazz = obj.getClass();
		try {
			Class<?> objFactoryClazz = Class.forName(objClazz.getPackage()
					.getName() + ".ObjectFactory");

			JAXBContext ctx = JAXBContext.newInstance(objClazz.getPackage()
					.getName());

			Marshaller marshaller = ctx.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

			Object objectFactory = objFactoryClazz.newInstance();

			Method createMethod = objFactoryClazz.getMethod(
					"create" + objClazz.getSimpleName(), objClazz);

			@SuppressWarnings("unchecked")
			JAXBElement<T> el = (JAXBElement<T>) createMethod.invoke(
					objectFactory, obj);

			marshaller.marshal(el, sw);
		} catch (JAXBException e) {
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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return sw.toString();
	}

	public static void main(String[] args) {
		Mt102 mt102 = new Mt102();

		System.out.println(marshall(mt102));
	}

	private XmlHelper() {
	}
}


