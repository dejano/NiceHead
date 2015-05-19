
package rs.ac.uns.ftn.xws.ws.swiftcode;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 2.6.5
 * 2015-05-19T10:12:54.948+02:00
 * Generated source version: 2.6.5
 * 
 */
 
public class SwiftCodeDocument_SwiftCodeDocumentPort_Server{

    protected SwiftCodeDocument_SwiftCodeDocumentPort_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new SwiftCodeDocumentImpl();
        String address = "http://localhost:8080/swiftCode";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws java.lang.Exception { 
        new SwiftCodeDocument_SwiftCodeDocumentPort_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}
