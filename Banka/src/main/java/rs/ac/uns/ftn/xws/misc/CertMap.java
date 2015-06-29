package rs.ac.uns.ftn.xws.misc;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;

public class CertMap {

	private static Map<Integer, String> certMap = new HashMap<Integer, String>();

	public static <T> void add(T obj, String cert) {
		certMap.put(DocumentUtil.getHashCode(DocumentUtil.toDocument(obj)), cert);
	}

	public static String getCert(Document doc) {
		return certMap.remove(DocumentUtil.getHashCode(doc));
	}

	private CertMap() {
	}

}
