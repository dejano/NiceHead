package rs.ac.uns.ftn.xmlbsep.xmldb;

import org.basex.rest.Result;
import org.basex.rest.Results;
import org.w3c.dom.Node;
import rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated.partner.Partner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dejan on 27.5.2015..
 */
public class EntityManagerBaseX<T, ID extends Serializable> {

    /*
     * Izbaciti u XML/properties konfiguraciju
     */
    public static final String REST_URL = "http://localhost:8984/rest/";

    public static final String BASEX_CONTEXT_PATH = "org.basex.rest";

    private String schemaName;

    private String contextPath;

    private Marshaller marshaller;

    private Unmarshaller unmarshaller, basex_unmarshaller;

    private JAXBContext context, basex_context;

    private URL url;

    private HttpURLConnection conn;

    public EntityManagerBaseX(String schemaName, String contextPath) throws JAXBException {
        setSchemaName(schemaName);
        setContextPath(contextPath);

        context = JAXBContext.newInstance(contextPath);
        marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        unmarshaller = context.createUnmarshaller();

        basex_context = JAXBContext.newInstance(BASEX_CONTEXT_PATH);
        basex_unmarshaller = basex_context.createUnmarshaller();
    }

    @SuppressWarnings("unchecked")
    public T find(ID resourceId) throws IOException, JAXBException {
        T entity = null;

        StringBuilder builder = new StringBuilder(REST_URL);
        builder.append(schemaName);
        builder.append("/").append(resourceId);
        builder.append("&wrap=no");

        url = new URL(builder.toString());
        conn = (HttpURLConnection) url.openConnection();

        int responseCode = conn.getResponseCode();
        String message = conn.getResponseMessage();

        System.out.println("\n* HTTP response: " + responseCode + " (" + message + ')');

        if (responseCode == HttpURLConnection.HTTP_OK)
            return (T) unmarshaller.unmarshal(conn.getInputStream());

        conn.disconnect();
        return entity;
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll(String collection) throws IOException, JAXBException {
        Results wrappedResults = null;
        List<T> results = new ArrayList<T>();

        StringBuilder builder = new StringBuilder(REST_URL);
        builder.append(schemaName);
        builder.append("?query=collection('" + collection + "')");
        builder.append("&wrap=yes");

        url = new URL(builder.substring(0));
        conn = (HttpURLConnection) url.openConnection();

        int responseCode = conn.getResponseCode();
        String message = conn.getResponseMessage();

        System.out.println(builder.toString());
        System.out.println(url);

        System.out.println("\n* HTTP response: " + responseCode + " (" + message + ')');

        if (responseCode == HttpURLConnection.HTTP_OK) {
            // return (Results) basex_unmarshaller.unmarshal(conn.getInputStream());
            parseMultipleResults(results, conn.getInputStream());
        }

        conn.disconnect();
        return results;
    }

    @SuppressWarnings("unchecked")
    public void parseMultipleResults(List<T> results, InputStream input) throws JAXBException, IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(input));
//        try {
//            String line;
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Results wrappedResults;
        wrappedResults = (Results) basex_unmarshaller.unmarshal(input);
        for (Result result : wrappedResults.getResult()) {
            results.add((T) unmarshaller.unmarshal((Node) result.getAny()));
        }


    }

    /*
     * Takes both, XQuery and XUpdate statements.
     */
    public InputStream executeQuery(String xQuery, boolean wrap) throws IOException {
        InputStream result = null;
        String wrapString = wrap ? "yes" : "no";
        String wrappedQuery = "<query xmlns='http://basex.org/rest'>" +
                "<text><![CDATA[%s]]></text>" +
                "<parameter name='wrap' value='" + wrapString + "'/>" +
                "</query>";
        wrappedQuery = String.format(wrappedQuery, xQuery);

        url = new URL(REST_URL + schemaName);
//        System.out.println(url);
//        System.out.println(wrappedQuery);
        conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod(RequestMethod.POST);
        conn.setRequestProperty("Content-Type", "application/query+xml");

		/*
         * Generate HTTP POST body.
		 */
        OutputStream out = conn.getOutputStream();
        out.write(wrappedQuery.getBytes("UTF-8"));
        out.close();

        int responseCode = conn.getResponseCode();
        String message = conn.getResponseMessage();

        System.out.println("\nEntityManagerBaseX.executeQuery* HTTP response: " + responseCode + " (" + message + ')');

        if (responseCode == HttpURLConnection.HTTP_OK)
            result = conn.getInputStream();

        return result;
    }

    /*
     * Takes both, XQuery and XUpdate statements.
     */
    public <G> List<G> executeQuery(String xQuery, CustomResultHandler<G> rowMapper, boolean wrap) throws IOException, JAXBException {
        String wrapString = wrap ? "yes" : "no";
        String wrappedQuery = "<query xmlns='http://basex.org/rest'>" +
                "<text><![CDATA[%s]]></text>" +
                "<parameter name='wrap' value='" + wrapString + "'/>" +
                "</query>";
        wrappedQuery = String.format(wrappedQuery, xQuery);

        url = new URL(REST_URL + schemaName);
//        System.out.println(url);
//        System.out.println(wrappedQuery);
        conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod(RequestMethod.POST);
        conn.setRequestProperty("Content-Type", "application/query+xml");

		/*
         * Generate HTTP POST body.
		 */
        OutputStream out = conn.getOutputStream();
        out.write(wrappedQuery.getBytes("UTF-8"));
        out.close();

        int responseCode = conn.getResponseCode();

//        System.out.println("\n* HTTP response: " + responseCode + " (" + message + ')');
        List<G> list = new ArrayList<G>();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            ResultHandler<G> resultHandler = new ResultHandler<G>();
            list = resultHandler.handleResult(basex_unmarshaller, unmarshaller, conn.getInputStream(), rowMapper);
        }

        return list;
    }

    public void persist(T entity, Long id) throws JAXBException, IOException {

        String resourceId = String.valueOf(id);

        url = new URL(REST_URL + schemaName + "/" + resourceId);
        conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod(RequestMethod.PUT);

        marshaller.marshal(entity, conn.getOutputStream());

        int responseCode = conn.getResponseCode();
        String message = conn.getResponseMessage();

        System.out.println("\nEntityManagerBaseX.persist * HTTP response: " + responseCode + " (" + message + ')');

        conn.disconnect();
    }

    public void delete(ID resourceId) throws IOException {
        url = new URL(REST_URL + schemaName + "/" + resourceId);
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(RequestMethod.DELETE);

        int responseCode = conn.getResponseCode();
        String message = conn.getResponseMessage();

        System.out.println("\n* HTTP response: " + responseCode + " (" + message + ')');

        conn.disconnect();
    }

    public void update(T entity, ID resourceId) throws IOException, JAXBException {
        url = new URL(REST_URL + schemaName + "/" + resourceId);
        conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod(RequestMethod.PUT);

        marshaller.marshal(entity, conn.getOutputStream());

        int responseCode = conn.getResponseCode();
        String message = conn.getResponseMessage();

        System.out.println("\n* HTTP response: " + responseCode + " (" + message + ')');

        conn.disconnect();
    }

    /**
     * Implements some sort of identity strategy, since it isn't natively supported by XMLDB.
     *
     * @return the next id value.
     * @throws IOException
     */
    public <G> Long getIdentity(G entity) throws IOException, JAXBException {
        IdentityXQuery annotation = entity.getClass().getAnnotation(IdentityXQuery.class);
        String xQuery = annotation.value();

        List<Long> id = executeQuery(xQuery, new IdentityRowMapper(), false);

        return id.get(0);
    }

	/*
     * Get/set methods
	 */

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public Marshaller getMarshaller() {
        return marshaller;
    }

    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public Unmarshaller getUnmarshaller() {
        return unmarshaller;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    public JAXBContext getContext() {
        return context;
    }

    public void setContext(JAXBContext context) {
        this.context = context;
    }

}

