package rs.ac.uns.ftn.xws.ws.mpcb.mpb;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import rs.ac.uns.ftn.xws.dao.BanksDataDao;
import rs.ac.uns.ftn.xws.generated.Mt102;
import rs.ac.uns.ftn.xws.generated.Mt103;
import rs.ac.uns.ftn.xws.util.ObjectFactory;

public class MpbDocumentClient {

	public static final String URL = "/services/MpbDocument?wsdl";

	private static MpbDocument getService(String swiftCode) {
		MpbDocument ret = null;

		try {
			String bankWsUrl = BanksDataDao.getBankWsUrl(swiftCode);
			URL wsdl = new URL(bankWsUrl + URL);

			QName serviceName = new QName(
					"http://www.ftn.uns.ac.rs/xws/ws/mpb", "MpbDocumentService");
			QName portName = new QName("http://www.ftn.uns.ac.rs/xws/ws/mpb",
					"MpbDocumentPort");

			Service service = Service.create(wsdl, serviceName);

			ret = service.getPort(portName, MpbDocument.class);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static void invokeClearingDebit(Mt102 mt102) {
		MpbDocument mpbService = getService(mt102.getCreditorBankDetails()
				.getSwiftCode());

		mpbService.clearingDebit(ObjectFactory.getMt900(mt102));
	}

	public static void invokeRtgsApproval(Mt103 mt103) {
		MpbDocument mpbService = getService(mt103.getCreditorBankDetails()
				.getSwiftCode());

		mpbService.rtgsApproval(ObjectFactory
				.getRtgsApprovalMessageMessage(mt103));
	}

	public static void invokeClearingApproval(Mt102 mt102) {
		MpbDocument mpbService = getService(mt102.getCreditorBankDetails()
				.getSwiftCode());

		mpbService.clearingApproval(ObjectFactory
				.getClearingApprovalMessage(mt102));
	}
}
