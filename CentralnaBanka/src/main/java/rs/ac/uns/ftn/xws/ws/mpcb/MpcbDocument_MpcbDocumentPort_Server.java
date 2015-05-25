
package rs.ac.uns.ftn.xws.ws.mpcb;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 2.6.5
 * 2015-05-25T18:46:52.426+02:00
 * Generated source version: 2.6.5
 * 
 */
 
public class MpcbDocument_MpcbDocumentPort_Server{

    protected MpcbDocument_MpcbDocumentPort_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new MpcbDocumentImpl();
        String address = "http://localhost:8080/mpcb";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws java.lang.Exception { 
        new MpcbDocument_MpcbDocumentPort_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}
