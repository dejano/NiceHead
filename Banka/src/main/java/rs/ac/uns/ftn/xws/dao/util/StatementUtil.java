package rs.ac.uns.ftn.xws.dao.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import rs.ac.uns.ftn.xws.dao.PaymentDataDao;
import rs.ac.uns.ftn.xws.generated.bs.Statement;
import rs.ac.uns.ftn.xws.generated.bs.Statement.Items;
import rs.ac.uns.ftn.xws.generated.bs.StatementItem;
import rs.ac.uns.ftn.xws.generated.bs.StatementRequest;
import rs.ac.uns.ftn.xws.generated.cmn.Payment;
import rs.ac.uns.ftn.xws.generated.cmn.PaymentData;

public class StatementUtil {

	public static void main(String[] args) throws DatatypeConfigurationException, ParseException {

		
		StatementRequest statementRequest = new StatementRequest();
		
		statementRequest.setAccountNumber("111-0000000000000-00");
		statementRequest.setStatementNumber(1);
		
		GregorianCalendar c = DateUtil.convertFromDMY("04-05-2006");
		XMLGregorianCalendar date = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		System.out.println(date.toString());
		statementRequest.setDate(date);
		
		Statement statement = buildStatement(statementRequest);
		// statement.set
		System.out.println("...");
	}

	public static Statement buildStatement(StatementRequest request) {
		Statement retVal = new Statement();
		List<StatementItem> items = new ArrayList<StatementItem>();

		String accountNumber = request.getAccountNumber();
		String orderDate = request.getDate().toString();
		int statementNumber = request.getStatementNumber();

		// iz headera
		retVal.setAccountNumber(accountNumber);
		retVal.setOrderDate(request.getDate());
		retVal.setStatementNumber(statementNumber);

		// iz paymenta
//		List<PaymentData> payments = PaymentDataDao.getPayments(orderDate,
//				accountNumber, statementNumber);
		List<PaymentData> payments = PaymentDataDao.getPayments("2006-05-04",
				accountNumber, statementNumber);
		int paymentsSize = payments.size();
		
		if (paymentsSize > 0) {
			int payoutCount = 0;
			BigDecimal payoutAmount = BigDecimal.ZERO;
			int paymentCount = 0;
			BigDecimal paymentAmount = BigDecimal.ZERO;
			
			if(paymentsSize>1) {
				retVal.setPreviousBalance(payments.get(0).getPreviousBalance());
				retVal.setNewBalance(payments.get(paymentsSize-1).getNewBalance());
			}
			else {
				retVal.setPreviousBalance(payments.get(0).getPreviousBalance());
				retVal.setNewBalance(payments.get(0).getNewBalance());
			}
			
			for (PaymentData payment : payments) {
				StatementItem item = buildStatementItem(payment, accountNumber);
				items.add(item);
				
				if(item.getDirection().equals("A")) {
					payoutCount++;
					payoutAmount = payoutAmount.add(item.getValue());
				}
				else {
					paymentCount++;
					paymentAmount = paymentAmount.add(item.getValue());
				}
			}
			
			// TODO @Nikola42 kako da setujem ovo prokletno govno
			Items itemsObject = new Statement.Items();
			//retVal.setItems((Items) items);
			
			retVal.setPayoutCount(payoutCount);
			retVal.setPaymentCount(paymentCount);
			retVal.setPayoutAmount(payoutAmount);
			retVal.setPaymentAmount(paymentAmount);
		}
		// TODO else ( 2 slucaja : 
		// A : prazan presek ali postoji racun u banci pa treba prikazati trenutno stanje
		// B : firma uopste nije registrovana u banci ( ne postoji takav racun u evidenciji )
		//

		return retVal;
	}

	public static StatementItem buildStatementItem(PaymentData payment,
			String requestedAccountNumber) {
		StatementItem item = new StatementItem();

		item.setCreditor(payment.getCreditor());
		item.setCreditorAccountDetails(payment.getCreditorAccountDetails());
		item.setDebtor(payment.getDebtor());
		item.setDebtorAccountDetails(payment.getDebtorAccountDetails());
		item.setPaymentPurpose(payment.getPaymentPurpose());

		if (payment.getDebtorAccountDetails().getAccountNumber()
				.equals(requestedAccountNumber))
			item.setDirection("A");
		else
			// trebalo bi else if , pa else da baci exception ili neki drugi
			// feedback
			item.setDirection("B");

		item.setOrderDate(payment.getOrderDate());
		item.setValueDate(payment.getValueDate());
		item.setValue(payment.getValue());

		return item;
	}

}
