package rs.ac.uns.ftn.xws.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rs.ac.uns.ftn.xws.dao.BanksDataDao;
import rs.ac.uns.ftn.xws.dao.ClearingDataDao;
import rs.ac.uns.ftn.xws.domain.ClearingData;
import rs.ac.uns.ftn.xws.generated.Mt102;

public class ClearingHelper {

	public static void executeClearing() {
		ClearingData clearingData = ClearingDataDao.getClearingData();
		Map<String, BankClearingDetails> bankClearingDetails = new HashMap<String, BankClearingDetails>();

		// create clearing details for each bank
		for (int i = 0; i < clearingData.getMt102().size(); i++) {
			Mt102 mt102 = clearingData.getMt102().get(i);

			String debtorBankSwiftCode = mt102.getDebtorBankDetails()
					.getSwiftCode();
			String creditorBankSwiftCode = mt102.getCreditorBankDetails()
					.getSwiftCode();
			
			if (!bankClearingDetails.containsKey(debtorBankSwiftCode)) {
				BigDecimal balance = BanksDataDao
						.getBankBalance(debtorBankSwiftCode);

				bankClearingDetails.put(debtorBankSwiftCode, new BankClearingDetails(balance));
			}

			if (!bankClearingDetails.containsKey(creditorBankSwiftCode)) {
				BigDecimal balance = BanksDataDao
						.getBankBalance(creditorBankSwiftCode);

				bankClearingDetails.put(creditorBankSwiftCode, new BankClearingDetails(balance));
			}

			bankClearingDetails.get(debtorBankSwiftCode).addPayment(mt102);
			bankClearingDetails.get(creditorBankSwiftCode).addPayout(mt102);
		}

		// remove all mt102s that cannot be payed
		boolean allPayable = false;
		while (!allPayable) {
			boolean removedMt102 = false;

			for (BankClearingDetails cd : bankClearingDetails.values()) {
				while (!cd.isPayable()) {
					Mt102 mt102 = cd.removeLastPayment();
					bankClearingDetails.get(mt102.getCreditorBankDetails().getSwiftCode())
							.removePayout(mt102);
					
					removedMt102 = true;
				}
			}

			if (!removedMt102)
				allPayable = true;
		}

		// pay mt102s
		for (String swiftCode : bankClearingDetails.keySet()) {
			BankClearingDetails cd = bankClearingDetails.get(swiftCode);

			BanksDataDao.updateBankBalance(swiftCode,
					cd.getBalance().subtract(cd.getAmount()));

			for (Mt102 mt102 : cd.getPayments()) {
				ClearingDataDao.deleteMt102(mt102.getMessageId());
			}
		}
	}
	
	public static void main(String[] args) {
		executeClearing();
	}

	private ClearingHelper() {
	}
}

class BankClearingDetails {

	private List<Mt102> payments;
	private List<Mt102> payouts;
	private BigDecimal amount;
	private BigDecimal balance;

	public BankClearingDetails(BigDecimal balance) {
		this.balance = balance;

		this.amount = BigDecimal.ZERO;
		this.payments = new ArrayList<Mt102>();
		this.payouts = new ArrayList<Mt102>();
	}

	public boolean isPayable() {
		return balance.compareTo(amount) >= 0;
	}

	public void addPayment(Mt102 mt102) {
		payments.add(mt102);
		amount = amount.add(mt102.getTotalAmount());
	}

	public Mt102 removeLastPayment() {
		Mt102 mt102 = payments.get(payments.size() - 1);

		payments.remove(mt102);
		amount = amount.subtract(mt102.getTotalAmount());

		return mt102;
	}

	public void addPayout(Mt102 mt102) {
		payouts.add(mt102);
		amount = amount.subtract(mt102.getTotalAmount());
	}

	public void removePayout(Mt102 mt102) {
		payouts.remove(mt102);
		amount = amount.add(mt102.getTotalAmount());
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public List<Mt102> getPayments() {
		return payments;
	}
}
