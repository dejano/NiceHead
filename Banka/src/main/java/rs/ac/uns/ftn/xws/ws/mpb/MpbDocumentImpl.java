/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package rs.ac.uns.ftn.xws.ws.mpb;

import java.util.logging.Logger;

import javax.ejb.Stateless;

/**
 * This class was generated by Apache CXF 2.6.5 2015-05-20T21:23:20.673+02:00
 * Generated source version: 2.6.5
 * 
 */

@Stateless
@javax.jws.WebService(serviceName = "MpbDocumentService", portName = "MpbDocumentPort", targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/mpb", wsdlLocation = "file:/C:/Users/nikola42/Documents/Fakultet/XWS/projekat/NiceHead/Banka/WEB-INF/wsdl/mpb.wsdl", endpointInterface = "rs.ac.uns.ftn.xws.ws.mpb.MpbDocument")
public class MpbDocumentImpl implements MpbDocument {

	private static final Logger LOG = Logger.getLogger(MpbDocumentImpl.class
			.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * rs.ac.uns.ftn.xws.ws.mpb.MpbDocument#rtgsApproval(rs.ac.uns.ftn.xws.generated
	 * .RtgsApprovalMessage rtgsApprovalPart )*
	 */
	public void rtgsApproval(
			rs.ac.uns.ftn.xws.generated.RtgsApprovalMessage rtgsApprovalPart) {
		LOG.info("Executing operation rtgsApproval");
		System.out.println(rtgsApprovalPart);
		try {
		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * rs.ac.uns.ftn.xws.ws.mpb.MpbDocument#clearingApproval(rs.ac.uns.ftn.xws
	 * .generated.ClearingApprovalMessage clearingApprovalPart )*
	 */
	public void clearingApproval(
			rs.ac.uns.ftn.xws.generated.ClearingApprovalMessage clearingApprovalPart) {
		LOG.info("Executing operation clearingApproval");
		System.out.println(clearingApprovalPart);
		try {
		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

}
