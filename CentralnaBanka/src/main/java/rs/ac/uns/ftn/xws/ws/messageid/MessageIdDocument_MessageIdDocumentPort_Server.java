
package rs.ac.uns.ftn.xws.ws.messageid;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 2.6.5
<<<<<<< HEAD:Banka/gen/rs/ac/uns/ftn/xws/ws/mpb/MpbDocument_MpbDocumentPort_Server.java
 * 2015-05-24T20:21:16.869+02:00
=======
 * 2015-05-20T12:30:07.438+02:00
>>>>>>> 6f1009ea04cc725a27921b9af916ab32785a6187:CentralnaBanka/src/main/java/rs/ac/uns/ftn/xws/ws/messageid/MessageIdDocument_MessageIdDocumentPort_Server.java
 * Generated source version: 2.6.5
 * 
 */
 
public class MessageIdDocument_MessageIdDocumentPort_Server{

    protected MessageIdDocument_MessageIdDocumentPort_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new MessageIdDocumentImpl();
        String address = "http://localhost:8080/messageId";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws java.lang.Exception { 
        new MessageIdDocument_MessageIdDocumentPort_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}
