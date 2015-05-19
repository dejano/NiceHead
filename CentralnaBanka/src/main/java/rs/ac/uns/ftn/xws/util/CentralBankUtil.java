package rs.ac.uns.ftn.xws.util;

import java.math.BigDecimal;
import java.util.List;

import rs.ac.uns.ftn.xws.generated.AccountDetails;
import rs.ac.uns.ftn.xws.generated.BankDetails;
import rs.ac.uns.ftn.xws.generated.Mt102;
import rs.ac.uns.ftn.xws.generated.Mt102.Payments;
import rs.ac.uns.ftn.xws.generated.Payment;

public class CentralBankUtil {

	public static String getAccountNumberAltForm(String accountNumber) {
		String ret;

		if (accountNumber.contains("-"))
			ret = accountNumber.replace("-", "");
		else {
			ret = accountNumber.substring(0, 3) + "-"
					+ accountNumber.substring(3, 16) + "-"
					+ accountNumber.substring(16);
		}

		return ret;
	}

	public static boolean isAmountValid(Mt102 mt102) {
		BigDecimal diff;
		BigDecimal totalAmount = BigDecimal.ZERO;

		for (Payment payment : mt102.getPayments().getPayments()) {
			totalAmount = totalAmount.add(payment.getAmount());
		}

		diff = (mt102.getTotalAmount().subtract(totalAmount)).abs();

		return diff.compareTo(new BigDecimal(0.01)) < 0;
	}

	public static boolean isSwiftCodeValid(String swiftCode) {
		// TODO impl validaciju
		return true;
	}

	public static boolean areEqualCreditorBanks(Mt102 mt102) {
		boolean ret = true;

		String creditorBankCode;
		List<Payment> payments = mt102.getPayments().getPayments();

		creditorBankCode = getBankCode(payments.get(0)
				.getCreditorAccountDetails());

		for (int i = 0; i < payments.size(); i++) {
			String currentBankCode = getBankCode(payments.get(i)
					.getCreditorAccountDetails());
			if(!creditorBankCode.equals(currentBankCode)){
				ret = false;
				break;
			}
		}
		
		return ret;
	}

	public static String getBankCode(AccountDetails accountDetails) {
		return accountDetails.getAccountNumber().substring(0, 3);
	}

	public static void main(String[] args) {
		Mt102 m = new Mt102();
		m.setTotalAmount(new BigDecimal(9.2));
		m.setPayments(new Payments());
		
		AccountDetails ad1 = new AccountDetails();
		ad1.setAccountNumber("123111111111111111");

		AccountDetails ad2 = new AccountDetails();
		ad2.setAccountNumber("122111111111111111");

		Payment p1 = new Payment();
		p1.setCreditorAccountDetails(ad1);
		p1.setAmount(new BigDecimal(5.1));
		m.getPayments().getPayments().add(p1);

		Payment p2 = new Payment();
		p2.setCreditorAccountDetails(ad2);
		p2.setAmount(new BigDecimal(4.1));
		m.getPayments().getPayments().add(p2);

		System.out.println(isAmountValid(m));
		
		System.out.println(areEqualCreditorBanks(m));

		System.out.println(getAccountNumberAltForm("111-1234567891234-11"));
		System.out.println(getAccountNumberAltForm("111123456789123411"));
	}

	private CentralBankUtil() {
	}
}
