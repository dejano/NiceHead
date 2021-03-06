package rs.ac.uns.ftn.xws.ws.bankdetails;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.6.5 2015-05-29T17:48:50.938+02:00
 * Generated source version: 2.6.5
 * 
 */
@WebService(targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/bankDetails", name = "BdDocument")
@XmlSeeAlso({ ObjectFactory.class,
		rs.ac.uns.ftn.xws.generated.mp.ObjectFactory.class,
		rs.ac.uns.ftn.xws.generated.cmn.ObjectFactory.class })
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface BdDocument {

	@WebMethod
	@WebResult(name = "bankDetails", targetNamespace = "http://www.ftn.uns.ac.rs/xws/xsd/common", partName = "bankDetailsPart")
	public rs.ac.uns.ftn.xws.generated.cmn.BankDetails getBankDetails(
			@WebParam(partName = "bankCodePart", name = "bankCode", targetNamespace = "http://www.ftn.uns.ac.rs/xws/xsd/mp") java.lang.String bankCodePart)
			throws NoBankCodeException;
}
