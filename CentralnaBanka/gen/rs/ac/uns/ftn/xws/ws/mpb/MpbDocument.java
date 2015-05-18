package rs.ac.uns.ftn.xws.ws.mpb;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;

/**
 * This class was generated by Apache CXF 2.6.5
 * 2015-05-13T18:48:05.737+02:00
 * Generated source version: 2.6.5
 * 
 */
@WebService(targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/mpb", name = "MpbDocument")
@XmlSeeAlso({rs.ac.uns.ftn.xws.bo.mt103.ObjectFactory.class, rs.ac.uns.ftn.xws.bo.mt102.ObjectFactory.class, rs.ac.uns.ftn.xws.bo.mp.ObjectFactory.class, ObjectFactory.class, rs.ac.uns.ftn.xws.bo.mt910.ObjectFactory.class})
public interface MpbDocument {

    @WebMethod
    @Oneway
    @RequestWrapper(localName = "rtgsConfirm", targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/mpb", className = "rs.ac.uns.ftn.xws.ws.mpb.RtgsConfirmType")
    public void rtgsConfirm(
        @WebParam(name = "mt910", targetNamespace = "")
        rs.ac.uns.ftn.xws.bo.mt910.Mt910 mt910,
        @WebParam(name = "mt103", targetNamespace = "")
        rs.ac.uns.ftn.xws.bo.mt103.Mt103 mt103
    );

    @WebMethod
    @Oneway
    @RequestWrapper(localName = "clearingConfirm", targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/mpb", className = "rs.ac.uns.ftn.xws.ws.mpb.ClearingConfirmType")
    public void clearingConfirm(
        @WebParam(name = "mt910", targetNamespace = "")
        rs.ac.uns.ftn.xws.bo.mt910.Mt910 mt910,
        @WebParam(name = "mt102", targetNamespace = "")
        rs.ac.uns.ftn.xws.bo.mt102.Mt102 mt102
    );
}