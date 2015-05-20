package rs.ac.uns.ftn.xws.dao;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import rs.ac.uns.ftn.xws.dao.util.RESTUtil;
import rs.ac.uns.ftn.xws.dao.util.RequestMethod;
import rs.ac.uns.ftn.xws.domain.ClearingData;
import rs.ac.uns.ftn.xws.util.XmlHelper;

public class ClearingDataDao {

	private static final String SCHEMA_NAME = "centralBank";

	private static final String[] insertMt102Query = { "insert node ",
			" into //clearingData" };

	private static final String[] deleteMt102Query = {
			"delete node //mt102[@messageId='", "']" };

	private static final String getClearingDataQuery = "//clearingData&wrap=no";

	public static void main(String[] args) throws Exception {
		File file = new File("src/main/resources/");
		RESTUtil.dropSchema(SCHEMA_NAME);
		RESTUtil.createSchema(SCHEMA_NAME);
		RESTUtil.createResource(SCHEMA_NAME, "clearingData.xml",
				new FileInputStream(new File(file, "clearingData.xml")));

		String mt102Xml = "<mt102 messageId=\"2\"> <debtorBankDetails> <swiftCode>AAAAAA00</swiftCode> <bankClearingAccountNumber>000-0000000000000-00</bankClearingAccountNumber> </debtorBankDetails> <creditorBankDetails> <swiftCode>AAAAAA00</swiftCode> <bankClearingAccountNumber>000-0000000000000-00</bankClearingAccountNumber> </creditorBankDetails> <totalAmount> 0.00 </totalAmount> <currencyDate>2006-05-04</currencyDate> <currencyCode>A</currencyCode> <date>2006-05-04</date> <payments> <payments> <paymentOrderId>paymentOrderId0</paymentOrderId> <debtor>debtor0</debtor> <paymentPurpose>paymentPurpose0</paymentPurpose> <creditor>creditor0</creditor> <orderDate>2006-05-04</orderDate> <debtorAccountDetails> <accountNumber>000-0000000000000-00</accountNumber> <model>50</model> <referenceNumber>referenceNumber0</referenceNumber> </debtorAccountDetails> <creditorAccountDetails> <accountNumber>000-0000000000000-00</accountNumber> <model>50</model> <referenceNumber>referenceNumber1</referenceNumber> </creditorAccountDetails> <amount>0.00</amount> <currencyCode>cur</currencyCode> </payments> <payments> <paymentOrderId>paymentOrderId1</paymentOrderId> <debtor>debtor1</debtor> <paymentPurpose>paymentPurpose1</paymentPurpose> <creditor>creditor1</creditor> <orderDate>2006-05-04</orderDate> <debtorAccountDetails> <accountNumber>000-0000000000000-00</accountNumber> <model>50</model> <referenceNumber>referenceNumber2</referenceNumber> </debtorAccountDetails> <creditorAccountDetails> <accountNumber>000-0000000000000-00</accountNumber> <model>50</model> <referenceNumber>referenceNumber3</referenceNumber> </creditorAccountDetails> <amount>0.00</amount> <currencyCode>cur</currencyCode> </payments> </payments> </mt102>";

		insertMt102(mt102Xml);

		System.out.println(getClearingDataa());

		// deleteMt102("2");

		System.out.println(getClearingData());
	}

	public static String getClearingDataa() {
		String ret = null;

		try {
			ret = RESTUtil.readString(RESTUtil.retrieveResource(
					getClearingDataQuery, SCHEMA_NAME, RequestMethod.GET));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static ClearingData getClearingData() {
		ClearingData ret = null;

		try {
			ret = XmlHelper.unmarshall(RESTUtil.retrieveResource(
					getClearingDataQuery, SCHEMA_NAME, RequestMethod.GET),
					ClearingData.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static void insertMt102(String mt102Xml) {
		String q = insertMt102Query[0] + mt102Xml + insertMt102Query[1];

		try {
			RESTUtil.retrieveResourcePost(q, SCHEMA_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deleteMt102(String mt102MessageId) {
		String q = deleteMt102Query[0] + mt102MessageId + deleteMt102Query[1];
		try {
			RESTUtil.retrieveResource(q, SCHEMA_NAME, RequestMethod.POST);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ClearingDataDao() {
	}
}
