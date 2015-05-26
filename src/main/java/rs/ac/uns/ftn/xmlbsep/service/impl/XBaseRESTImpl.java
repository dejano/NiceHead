package rs.ac.uns.ftn.xmlbsep.service.impl;

import org.apache.commons.io.IOUtils;
import org.basex.http.HTTPMethod;
import org.basex.util.Base64;
import rs.ac.uns.ftn.xmlbsep.service.RequestMethod;
import rs.ac.uns.ftn.xmlbsep.service.XBaseREST;
import rs.ac.uns.ftn.xmlbsep.util.HTTPConnectionInterceptor;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Stateless
@Local(XBaseREST.class)
@Interceptors(HTTPConnectionInterceptor.class)
public class XBaseRESTImpl implements XBaseREST  {

    public static final String DB_USER_NAME = "admin";
    public static final String DB_PASSWORD = "admin";
    public static final String REST_SERVER_URL = "http://localhost:8984/rest/";

    public HttpURLConnection conn;

    public int createSchema(String schemaName) throws IOException {
        this.connect(new URL(REST_SERVER_URL + schemaName));
        conn.setDoOutput(true);
        System.out.println(String.valueOf(HTTPMethod.PUT));
        conn.setRequestMethod(String.valueOf(HTTPMethod.PUT));

        int responseCode = conn.getResponseCode();
        String message = conn.getResponseMessage();

        this.disconnect();
        return responseCode;
    }

    public int dropSchema(String schemaName) throws IOException  {
        return 0;
    }

    public int insert(String schemaName, String resourceId, InputStream resource) throws IOException {
        this.connect(new URL(REST_SERVER_URL + schemaName + "/" + resourceId));
        conn.setDoOutput(true);
        System.out.println(String.valueOf(HTTPMethod.PUT));
        conn.setRequestMethod(String.valueOf(HTTPMethod.PUT));
        OutputStream out = new BufferedOutputStream(conn.getOutputStream());
		/* Slanje podataka kroz stream */
        System.out.println("\n* Send document...");
        IOUtils.copy(resource, out);
        IOUtils.closeQuietly(resource);
        IOUtils.closeQuietly(out);

        int responseCode = conn.getResponseCode();
        String message = conn.getResponseMessage();

        this.disconnect();
        return responseCode;
    }

    public int update(String schemaName, String resourceId, InputStream resource) {
        return 0;
    }

    public int get(String xQuery, String schemaName) {
        return 0;
    }

    public int delete(String schemaName, String resourceId) {
        return 0;
    }

    protected void connect(URL url) throws IOException {
        conn = (HttpURLConnection) url.openConnection();
        String encoded = Base64.encode(DB_USER_NAME + ":" + DB_PASSWORD);
        conn.setRequestProperty("Authorization", "Basic " + encoded);
    }

    protected void disconnect() {
        conn.disconnect();
    }
}
