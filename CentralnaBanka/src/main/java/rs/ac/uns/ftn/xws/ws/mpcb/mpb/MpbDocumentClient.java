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

	public static void invokeRtgsConfirm(Mt103 mt103) {
		try {
			String bankWsUrl = BanksDataDao.getBankWsUrl(mt103
					.getCreditorBankDetails().getSwiftCode());
			URL wsdl = new URL(bankWsUrl + URL);

			QName serviceName = new QName(
					"http://www.ftn.uns.ac.rs/xws/ws/mpb", "MpbDocumentService");
			QName portName = new QName("http://www.ftn.uns.ac.rs/xws/ws/mpb",
					"MpbDocumentPort");

			Service service = Service.create(wsdl, serviceName);

			MpbDocument mpbService = service.getPort(portName,
					MpbDocument.class);

			mpbService.rtgsApproval(ObjectFactory
					.getRtgsApprovalMessageMessage(mt103));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public static void invokeClearingConfirm(Mt102 mt102) {
		try {
			String bankWsUrl = BanksDataDao.getBankWsUrl(mt102
					.getCreditorBankDetails().getSwiftCode());
			URL wsdl = new URL(bankWsUrl + URL);

			QName serviceName = new QName(
					"http://www.ftn.uns.ac.rs/xws/ws/mpb", "MpbDocumentService");
			QName portName = new QName("http://www.ftn.uns.ac.rs/xws/ws/mpb",
					"MpbDocumentPort");

			Service service = Service.create(wsdl, serviceName);

			MpbDocument mpbService = service.getPort(portName,
					MpbDocument.class);

			mpbService.clearingApproval(ObjectFactory
					.getClearingApprovalMessage(mt102));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
