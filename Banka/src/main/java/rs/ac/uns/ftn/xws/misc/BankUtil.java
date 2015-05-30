package rs.ac.uns.ftn.xws.misc;

public class BankUtil {

	public static boolean areInTheSameBank(String accountNumber1, String accountNumber2){
		return getBankCode(accountNumber1).equals(getBankCode(accountNumber2));
	}
	
	public static String getBankCode(String accountNumber){
		return accountNumber.trim().substring(0, 3);
	}
	
	private BankUtil() {}
}
