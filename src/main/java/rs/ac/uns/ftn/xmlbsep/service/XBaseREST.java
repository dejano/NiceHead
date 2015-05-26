package rs.ac.uns.ftn.xmlbsep.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

/**
 * Created by dejan on 25.5.2015..
 */
public interface XBaseREST {

    int createSchema(String schemaName) throws IOException;

    int dropSchema(String schemaName) throws IOException;

    int insert(String schemaName, String resourceId, InputStream resource) throws IOException;

    int update(String schemaName, String resourceId, InputStream resource);

    int get(String xQuery, String schemaName);

    int delete(String schemaName, String resourceId);
}
