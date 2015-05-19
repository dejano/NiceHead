package rs.ac.uns.ftn.xws.ws.mpcb;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.6.5
 * 2015-05-19T11:46:58.932+02:00
 * Generated source version: 2.6.5
 * 
 */
@WebService(targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/mpcb", name = "MpcbDocument")
@XmlSeeAlso({rs.ac.uns.ftn.xws.generated.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface MpcbDocument {

    @WebMethod
    @WebResult(name = "mpcbResponseMessage", targetNamespace = "http://www.ftn.uns.ac.rs/xws/xsd/mp", partName = "mpcbResponsePart")
    public rs.ac.uns.ftn.xws.generated.MpcbResponseMessage rtgsRequest(
        @WebParam(partName = "rtgsRequestPart", name = "rtgsRequestMessage", targetNamespace = "http://www.ftn.uns.ac.rs/xws/xsd/mp")
        rs.ac.uns.ftn.xws.generated.RtgsRequestMessage rtgsRequestPart
    ) throws MpException;

    @WebMethod
    @WebResult(name = "mpcbResponseMessage", targetNamespace = "http://www.ftn.uns.ac.rs/xws/xsd/mp", partName = "mpcbResponsePart")
    public rs.ac.uns.ftn.xws.generated.MpcbResponseMessage clearingRequest(
        @WebParam(partName = "clearingRequestPart", name = "clearingRequestMessage", targetNamespace = "http://www.ftn.uns.ac.rs/xws/xsd/mp")
        rs.ac.uns.ftn.xws.generated.ClearingRequestMessage clearingRequestPart
    ) throws MpException;
}
