
package rs.ac.uns.ftn.xws.ws.mpb;

import javax.xml.ws.Endpoint;
 
public class MpbDocument_MpbDocumentPort_Server{

    protected MpbDocument_MpbDocumentPort_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new MpbDocumentImpl();
        String address = "http://localhost:8080/mpb";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws java.lang.Exception { 
        new MpbDocument_MpbDocumentPort_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}
