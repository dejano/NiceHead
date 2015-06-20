package rs.ac.uns.ftn.xws.dao;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.xws.dao.util.ParserUtil;
import rs.ac.uns.ftn.xws.dao.util.RESTUtil;
import rs.ac.uns.ftn.xws.dao.util.RequestMethod;
import rs.ac.uns.ftn.xws.domain.mpb.Mt102Data;
import rs.ac.uns.ftn.xws.domain.mpb.Mt102Ref;
import rs.ac.uns.ftn.xws.generated.cmn.BankDetails;
import rs.ac.uns.ftn.xws.generated.mp.Mt102;
import rs.ac.uns.ftn.xws.generated.mp.Mt102.Payments;
import rs.ac.uns.ftn.xws.generated.mp.Mt102Payment;
import rs.ac.uns.ftn.xws.generated.po.PaymentOrder;
import rs.ac.uns.ftn.xws.misc.BankConstants;
import rs.ac.uns.ftn.xws.misc.ObjectMapper;
import rs.ac.uns.ftn.xws.misc.XmlHelper;

public class Mt102DataDao {

	private static final String[] insertMt102Query = { "insert node ",
			" into //*[local-name()='mt102Data']" };

	private static final String[] deleteMt102Query = {
			"delete node //*[local-name()='mt102Data']/*[local-name()='mt102Ref'][@messageId='", "']" };

	private static final String[] getMt102Query = {
			"//*[local-name()='mt102Data']/*[local-name()='mt102Ref'][@messageId='", "']&wrap=no" };

	public static void main(String[] args) throws Exception {
		File file = new File("src/main/resources/");
		RESTUtil.deleteResource(BankConstants.BANK_NAME, "mt102Data.xml");
		RESTUtil.createResource(BankConstants.BANK_NAME, "mt102Data.xml",
				new FileInputStream(new File(file, "mt102Data.xml")));
//
//		Mt102Ref mt102Ref = new Mt102Ref();
//		mt102Ref.setMessageId("999");
//		insertMt102(XmlHelper.marshall(mt102Ref));

//		System.out.println(getMt102DataAsString("999"));
//
//		System.out.println(getMt102Ref("555").getMessageId());
	}

	public static String getMt102DataAsString(String messageId) {
		String ret = null;

		String query = getMt102Query[0] + messageId + getMt102Query[1];

		try {
			ret = RESTUtil.readString(RESTUtil.retrieveResource(query,
					BankConstants.BANK_NAME, RequestMethod.GET));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static Mt102Ref getMt102Ref(String messageId) {
		Mt102Ref ret = null;

		String query = getMt102Query[0] + messageId + getMt102Query[1];

		try {
			ret = XmlHelper
					.unmarshall(RESTUtil.retrieveResource(query,
							BankConstants.BANK_NAME, RequestMethod.GET),
							Mt102Ref.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static void insertMt102(String mt102Xml) {
		String q = insertMt102Query[0] + mt102Xml + insertMt102Query[1];

		try {
			RESTUtil.retrieveResourcePost(q, BankConstants.BANK_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deleteMt102(String mt102MessageId) {
		String q = deleteMt102Query[0] + mt102MessageId + deleteMt102Query[1];

		try {
			RESTUtil.retrieveResource(q, BankConstants.BANK_NAME,
					RequestMethod.POST);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Mt102Data createMt102Data(List<Mt102Ref> refList) {
		Mt102Data mt102data = new Mt102Data();
		
		// TODO prethodno konstruisi listu(klasu) preko Object Factory-a
		mt102data.getMt102Ref().addAll(refList);
		
		return mt102data;
	}

	private Mt102DataDao() {
	}
}
