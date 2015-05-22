package rs.ac.uns.ftn.xws.ws.mpcb;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

@WebService(targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/mpcb", name = "MpcbDocument")
@XmlSeeAlso({ rs.ac.uns.ftn.xws.generated.ObjectFactory.class })
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface MpcbDocument {


	@WebMethod
	@WebResult(name = "mt900", targetNamespace = "http://www.ftn.uns.ac.rs/xws/xsd/mt900", partName = "mpcbResponsePart")
	public rs.ac.uns.ftn.xws.generated.Mt900 rtgsRequest(
			@WebParam(partName = "rtgsRequestPart", name = "mt103", targetNamespace = "http://www.ftn.uns.ac.rs/xws/xsd/mt103") rs.ac.uns.ftn.xws.generated.Mt103 rtgsRequestPart)
			throws MpException;

	@WebMethod
	public void clearingRequest(
			@WebParam(partName = "clearingRequestPart", name = "mt102", targetNamespace = "http://www.ftn.uns.ac.rs/xws/xsd/mt102") rs.ac.uns.ftn.xws.generated.Mt102 clearingRequestPart)
			throws MpException;
}
