package rs.ac.uns.ftn.xws.dao.util;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class DateUtil {

	static SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");

	public static GregorianCalendar convertFromDMY(String dd_mm_yy)
			throws ParseException {

		// this actually works, got rid of the original code idea
		String[] splitDate = dd_mm_yy.split("-");
		int days = Integer.parseInt(splitDate[0]);
		int month = (Integer.parseInt(splitDate[1]) - 1);
		int year = Integer.parseInt(splitDate[2]);

		// dates go in properly
		GregorianCalendar dateConverted = new GregorianCalendar(year, month,
				days);
//		String finalDate = format(dateConverted);
		return dateConverted;
	}

	public static String format(GregorianCalendar date) throws ParseException {

		fmt.setCalendar(date);
		String dateFormatted = fmt.format(date.getTime());
		System.out.println(dateFormatted);
		return dateFormatted;
	}

}