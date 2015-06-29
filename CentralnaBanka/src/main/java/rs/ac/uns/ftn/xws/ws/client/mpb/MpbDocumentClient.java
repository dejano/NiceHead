package rs.ac.uns.ftn.xws.ws.client.mpb;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.Handler;

import rs.ac.uns.ftn.xws.dao.BanksDataDao;
import rs.ac.uns.ftn.xws.generated.mp.ClearingApprovalMessage;
import rs.ac.uns.ftn.xws.generated.mp.Mt102;
import rs.ac.uns.ftn.xws.generated.mp.Mt103;
import rs.ac.uns.ftn.xws.generated.mp.Mt900;
import rs.ac.uns.ftn.xws.generated.mp.RtgsApprovalMessage;
import rs.ac.uns.ftn.xws.handler.ClientSecMessageHandler;
import rs.ac.uns.ftn.xws.misc.CertMap;
import rs.ac.uns.ftn.xws.misc.ObjectMapper;
import rs.ac.uns.ftn.xws.handler.client.ClientCryptoHandler;
import rs.ac.uns.ftn.xws.handler.client.ClientSignatureHandler;

public class MpbDocumentClient {

	public static final String URL = "/services/MpbDocument?wsdl";

	private static MpbDocument getService(String swiftCode) {
		MpbDocument ret = null;

		try {
			String bankWsUrl = BanksDataDao.getBankWsUrl(swiftCode);
			URL wsdl = new URL(bankWsUrl + URL);

			QName serviceName = new QName("http://www.ftn.uns.ac.rs/xws/ws/mpb",
					"MpbDocumentService");
			QName portName = new QName("http://www.ftn.uns.ac.rs/xws/ws/mpb", "MpbDocumentPort");

			Service service = Service.create(wsdl, serviceName);

			ret = service.getPort(portName, MpbDocument.class);
			
			ClientSecMessageHandler secMessage = new ClientSecMessageHandler();
			ClientCryptoHandler crypto = new ClientCryptoHandler();
			ClientSignatureHandler sign = new ClientSignatureHandler();

			@SuppressWarnings("rawtypes")
			List<Handler> handlerChain = new ArrayList<Handler>();
			handlerChain.add(secMessage);
			handlerChain.add(sign);
			handlerChain.add(crypto);

			((BindingProvider) ret).getBinding().setHandlerChain(handlerChain);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static void invokeClearingDebit(Mt102 mt102) {
		MpbDocument mpbService = getService(mt102.getDebtorBankDetails().getSwiftCode());

		Mt900 mt900 = ObjectMapper.getMt900(mt102);
		
		CertMap.add(mt900, BanksDataDao.getBankCertificateBySwiftCode(mt102
				.getDebtorBankDetails().getSwiftCode()));
		
		mpbService.clearingDebit(mt900);
	}

	public static void invokeRtgsApproval(Mt103 mt103) {
		MpbDocument mpbService = getService(mt103.getCreditorBankDetails().getSwiftCode());
		RtgsApprovalMessage ram = ObjectMapper.getRtgsApprovalMessageMessage(mt103);

		CertMap.add(ram, BanksDataDao.getBankCertificateBySwiftCode(
				mt103.getCreditorBankDetails().getSwiftCode()));
		
		mpbService.rtgsApproval(ram);
	}

	public static void invokeClearingApproval(Mt102 mt102) {
		MpbDocument mpbService = getService(mt102.getCreditorBankDetails().getSwiftCode());

		ClearingApprovalMessage cam = ObjectMapper.getClearingApprovalMessage(mt102);
		
		CertMap.add(cam, BanksDataDao.getBankCertificateBySwiftCode(mt102
				.getCreditorBankDetails().getSwiftCode()));
		
		mpbService.clearingApproval(cam);
	}
}
