package rs.ac.uns.ftn.xws.ws.messageid;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.6.5
 * 2015-05-20T12:30:07.409+02:00
 * Generated source version: 2.6.5
 * 
 */
@WebService(targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/messageId", name = "MessageIdDocument")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface MessageIdDocument {

    @WebMethod
    @WebResult(name = "messageId", targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/messageId", partName = "messageId")
    public java.lang.String getMessageId();
}
