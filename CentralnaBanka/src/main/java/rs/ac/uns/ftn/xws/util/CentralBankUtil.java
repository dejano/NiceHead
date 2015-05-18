package rs.ac.uns.ftn.xws.util;

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

	public static void main(String[] args) {
		System.out.println(getAccountNumberAltForm("111-1234567891234-11"));
		System.out.println(getAccountNumberAltForm("111123456789123411"));
	}

	private CentralBankUtil() {
	}
}
