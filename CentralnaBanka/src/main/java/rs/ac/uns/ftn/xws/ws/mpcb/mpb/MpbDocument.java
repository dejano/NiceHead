package rs.ac.uns.ftn.xws.ws.mpcb.mpb;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

@WebService(targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/mpb", name = "MpbDocument")
@XmlSeeAlso({rs.ac.uns.ftn.xws.generated.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface MpbDocument {

    @WebMethod
    @Oneway
    public void clearingDebit(
        @WebParam(partName = "clearingDebitPart", name = "mt900", targetNamespace = "http://www.ftn.uns.ac.rs/xws/xsd/mt900")
        rs.ac.uns.ftn.xws.generated.Mt900 clearingDebitPart
    );

    @WebMethod
    @Oneway
    public void rtgsApproval(
        @WebParam(partName = "rtgsApprovalPart", name = "rtgsApprovalMessage", targetNamespace = "http://www.ftn.uns.ac.rs/xws/xsd/mp")
        rs.ac.uns.ftn.xws.generated.RtgsApprovalMessage rtgsApprovalPart
    );

    @WebMethod
    @Oneway
    public void clearingApproval(
        @WebParam(partName = "clearingApprovalPart", name = "clearingApprovalMessage", targetNamespace = "http://www.ftn.uns.ac.rs/xws/xsd/mp")
        rs.ac.uns.ftn.xws.generated.ClearingApprovalMessage clearingApprovalPart
    );
}
