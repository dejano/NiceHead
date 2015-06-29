/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package rs.ac.uns.ftn.xws.ws.bsws;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.jws.HandlerChain;

import rs.ac.uns.ftn.xws.dao.util.StatementUtil;
import rs.ac.uns.ftn.xws.generated.bs.Statement;
import rs.ac.uns.ftn.xws.generated.bs.StatementRequest;
import rs.ac.uns.ftn.xws.misc.CertMap;

@Stateless
@javax.jws.WebService(
		serviceName = "BsDocumentService",
		portName = "BsDocumentPort",
		targetNamespace = "http://www.ftn.uns.ac.rs/xws/ws/bsws",
		wsdlLocation = "file:/C:/Users/Nikola/Documents/Fakultet/XWS/projekat/NiceHead/Banka/WEB-INF/wsdl/bs.wsdl",
		endpointInterface = "rs.ac.uns.ftn.xws.ws.bsws.BsDocument")
@HandlerChain(file = "../handler-chain-document.xml")
public class BsDocumentImpl implements BsDocument {

	private static final Logger LOG = Logger.getLogger(BsDocumentImpl.class.getName());

	public Statement getStatement(StatementRequest bsRequestPart) {
		String cert = CertMap.getCert(bsRequestPart);
		
		LOG.info("Executing operation getStatement");

		Statement retVal = null;

		Statement statement = StatementUtil.buildStatement(bsRequestPart);
		retVal = statement;

		CertMap.add(retVal, cert);
		
		return retVal;
	}
}
