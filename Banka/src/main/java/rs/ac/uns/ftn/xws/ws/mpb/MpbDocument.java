package rs.ac.uns.ftn.xws.ws.mpb;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.6.5
 * 2015-05-25T18:49:15.114+02:00
 * Generated source version: 2.6.5
 * 
 */
@WebService(targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/mpb", name = "MpbDocument")
@XmlSeeAlso({rs.ac.uns.ftn.xws.generated.mp.ObjectFactory.class, rs.ac.uns.ftn.xws.generated.cmn.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface MpbDocument {

    @WebMethod
    @Oneway
    public void clearingDebit(
        @WebParam(partName = "clearingDebitPart", name = "mt900", targetNamespace = "http://www.ftn.uns.ac.rs/xws/xsd/mp")
        rs.ac.uns.ftn.xws.generated.mp.Mt900 clearingDebitPart
    );

    @WebMethod
    @Oneway
    public void rtgsApproval(
        @WebParam(partName = "rtgsApprovalPart", name = "rtgsApprovalMessage", targetNamespace = "http://www.ftn.uns.ac.rs/xws/xsd/mp")
        rs.ac.uns.ftn.xws.generated.mp.RtgsApprovalMessage rtgsApprovalPart
    );

    @WebMethod
    @Oneway
    public void clearingApproval(
        @WebParam(partName = "clearingApprovalPart", name = "clearingApprovalMessage", targetNamespace = "http://www.ftn.uns.ac.rs/xws/xsd/mp")
        rs.ac.uns.ftn.xws.generated.mp.ClearingApprovalMessage clearingApprovalPart
    );
}