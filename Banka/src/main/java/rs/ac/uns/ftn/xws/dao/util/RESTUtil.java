package rs.ac.uns.ftn.xws.dao.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.io.IOUtils;

public class RESTUtil {

	public static final String REST_URL = ResourceBundle.getBundle("basex").getString("rest.url");
	
//	public static final String REST_URL = ResourceBundle.getBundle(
//			"src/main/resources").getString("rest.url");

	public static int createSchema(String schemaName) throws Exception {
		System.out.println("=== PUT: create a new database: " + schemaName
				+ " ===");
		URL url = new URL(REST_URL + schemaName);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod(RequestMethod.PUT);
		
		conn.disconnect();
		
		return conn.getResponseCode();
	}

	public static int createResource(String schemaName, String resourceId,
			InputStream resource) throws Exception {
		System.out.println("=== PUT: create a new resource: " + resourceId
				+ " in database: " + schemaName + " ===");
		URL url = new URL(REST_URL + schemaName + "/" + resourceId);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod(RequestMethod.PUT);

		OutputStream out = new BufferedOutputStream(conn.getOutputStream());

		System.out.println("\n* Send document...");
		IOUtils.copy(resource, out);
		IOUtils.closeQuietly(resource);
		IOUtils.closeQuietly(out);

		conn.disconnect();
		
		return conn.getResponseCode();
	}

	public static InputStream retrieveResource(String query, String schemaName,
			String method) throws Exception {
		if (method == RequestMethod.GET)
			return retrieveResource(query, schemaName, "UTF-8", true);
		else if (method == RequestMethod.POST)
			return retrieveResourcePost(query, schemaName);
		return null;
	}

	public static InputStream retrieveResourcePost(String xquery,
			String schemaName) throws Exception {
		URL url = new URL(REST_URL + schemaName);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod(RequestMethod.POST);
		conn.setRequestProperty("Content-Type", "application/query+xml");
		OutputStream out = conn.getOutputStream();

		String xml = "<query xmlns='http://basex.org/rest'>" + "<text>"
				+ "<![CDATA[%s]]>" + "</text>" + "</query>";

		out.write(String.format(xml, xquery).getBytes("UTF-8"));
		out.close();

		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK)
			return conn.getInputStream();

		conn.disconnect();
		return null;
	}

	public static InputStream retrieveResource(String query, String schemaName,
			String encoding, boolean wrap) throws Exception {
		StringBuilder builder = new StringBuilder(REST_URL);
		builder.append(schemaName);
		builder.append("?query=");
		builder.append(query.replace(" ", "+"));
		builder.append("&encoding=");
		builder.append(encoding);
		if (wrap)
			builder.append("&wrap=yes");

		URL url = new URL(builder.substring(0));
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		/* Response kod vracen od strane servera */
		int responseCode = conn.getResponseCode();

		/* Ako je sve proslo kako treba... */
		if (responseCode == HttpURLConnection.HTTP_OK)
			return conn.getInputStream();

		conn.disconnect();
		return null;
	}

	public static int deleteResource(String schemaName, String resourceId)
			throws Exception {
		URL url = new URL(REST_URL + schemaName + "/" + resourceId);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(RequestMethod.DELETE);
		conn.disconnect();

		return conn.getResponseCode();
	}

	public static int dropSchema(String schemaName) throws Exception {
		return deleteResource(schemaName, "");
	}

	public static String readString(InputStream input) throws Exception {
		StringBuilder sb = new StringBuilder();

		BufferedReader br = new BufferedReader(new InputStreamReader(input));
		for (String line; (line = br.readLine()) != null;) {
			sb.append(line);
		}

		return sb.toString();
	}

	public static boolean isRunning() throws Exception {
		try {
			((HttpURLConnection) new URL(REST_URL).openConnection())
					.getResponseCode();
		} catch (ConnectException e) {
			return false;
		}
		return true;
	}

}
