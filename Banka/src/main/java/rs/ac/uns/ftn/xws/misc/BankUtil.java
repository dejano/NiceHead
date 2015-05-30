package rs.ac.uns.ftn.xws.misc;

public class BankUtil {

	public static boolean areInTheSameBank(String accountNumber1, String accountNumber2){
		return accountNumber1.trim().substring(0, 3).equals(accountNumber2.trim().substring(0, 3));
	}
	
	private BankUtil() {}
}
