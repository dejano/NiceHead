package rs.ac.uns.ftn.xmlbsep.util;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.w3c.dom.Document;

public class CertMap {
	private static final Logger LOG = Logger.getLogger(CertMap.class.getName());

	private static Map<Integer, String> certMap = new HashMap<Integer, String>();

	public static <T> void add(Document doc, String cert) {
		int hash = DocumentUtil.getHashCode(doc);
		LOG.info("certmap put : " + hash + " - " + cert);
		certMap.put(hash, cert);
	}
	
	public static <T> void add(T obj, String cert) {
		int hash = DocumentUtil.getHashCode(DocumentUtil.toDocument(obj));
		LOG.info("certmap put : " + hash + " - " + cert);
		certMap.put(hash, cert);
	}

	public static <T> void add(T obj, Class<?> objFactoryClazz, String elementName, String cert) {
		int hash = DocumentUtil.getHashCode(DocumentUtil.toDocument(obj, objFactoryClazz,
				elementName));
		System.out.println(hash);
		LOG.info("certmap put : " + hash + " - " + cert);
		certMap.put(hash, cert);
	}

	public static String getCert(Document doc) {
		int hash = DocumentUtil.getHashCode(doc);
		System.out.println(hash);
		LOG.info("certmap removed : " + hash);
		return certMap.remove(hash);
	}

	public static <T> String getCert(T obj) {
		int hash = DocumentUtil.getHashCode(DocumentUtil.toDocument(obj));
		LOG.info("certmap removed : " + hash);
		return certMap.remove(hash);
	}

	public static <T> String getCert(T obj, Class<?> objFactoryClazz, String elementName) {
		return certMap.remove(DocumentUtil.getHashCode(DocumentUtil.toDocument(obj,
				objFactoryClazz, elementName)));
	}
	
	private CertMap() {
	}

}
