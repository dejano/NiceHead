package rs.ac.uns.ftn.xws.ws.po;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.6.5
 * 2015-05-29T14:27:42.433+02:00
 * Generated source version: 2.6.5
 * 
 */
@WebService(targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/po", name = "PoDocument")
@XmlSeeAlso({rs.ac.uns.ftn.xws.generated.po.ObjectFactory.class, rs.ac.uns.ftn.xws.generated.cmn.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface PoDocument {

    @WebMethod
    public void paymentOrderHandle(
        @WebParam(partName = "paymentOrderPart", name = "paymentOrder", targetNamespace = "http://www.ftn.uns.ac.rs/xws/xsd/po")
        rs.ac.uns.ftn.xws.generated.po.PaymentOrder paymentOrderPart
    ) throws PoException;
}
