
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package rs.ac.uns.ftn.xws.ws.bsws;

import java.util.logging.Logger;

import javax.ejb.Stateless;

import rs.ac.uns.ftn.xws.dao.util.StatementUtil;
import rs.ac.uns.ftn.xws.generated.bs.Statement;

/**
 * This class was generated by Apache CXF 2.6.5
 * 2015-06-02T02:38:31.919+02:00
 * Generated source version: 2.6.5
 * 
 */
@Stateless
@javax.jws.WebService(
                      serviceName = "BsDocumentService",
                      portName = "BsDocumentPort",
                      targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/bsws",
                      wsdlLocation = "file:/C:/Users/Bandjur/Desktop/Workspace/XWS-BSEP-PI/XWS/NiceHead/Banka/WEB-INF/wsdl/bs.wsdl",
                      endpointInterface = "rs.ac.uns.ftn.xws.ws.bsws.BsDocument")
                      
public class BsDocumentImpl implements BsDocument {

    private static final Logger LOG = Logger.getLogger(BsDocumentImpl.class.getName());

    /* (non-Javadoc)
     * @see rs.ac.uns.ftn.xws.ws.bsws.BsDocument#getStatement(rs.ac.uns.ftn.xws.generated.bs.StatementRequest  bsRequestPart )*
     */
    public rs.ac.uns.ftn.xws.generated.bs.Statement getStatement(
			rs.ac.uns.ftn.xws.generated.bs.StatementRequest bsRequestPart) {
		LOG.info("Executing operation getStatement");
		
		LOG.info("VUKSA TEST!!!");
		
		
		Statement retVal = null;
		
		Statement statement = StatementUtil.buildStatement(bsRequestPart);
		retVal = statement;
		

		LOG.info("SHOOOOOW");
		return retVal;
	}

}
