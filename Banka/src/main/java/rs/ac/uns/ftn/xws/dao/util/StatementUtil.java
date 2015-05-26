package rs.ac.uns.ftn.xws.dao.util;

import java.util.List;

import rs.ac.uns.ftn.xws.dao.PaymentDataDao;
import rs.ac.uns.ftn.xws.generated.bs.Statement;
import rs.ac.uns.ftn.xws.generated.cmn.Payment;

public class StatementUtil {

	public static void main(String[] args) {

		List<Payment> payments = ParserUtil
				.transformStringsIntoJAXB(PaymentDataDao.getPayments(
						"2006-05-04", "111-0000000000000-00", 1));
		
		Statement statment = new Statement();

	}

}
