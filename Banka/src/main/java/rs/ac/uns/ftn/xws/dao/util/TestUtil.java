package rs.ac.uns.ftn.xws.dao.util;

import java.io.File;
import java.io.FileInputStream;

public class TestUtil {
	
	public static void main(String[] args) {
		InitXBase();
	}
	
	public static void InitXBase() {
		/* Test CRUD operacija */
		try {
			File file = new File("C:\\Users\\Bandjur\\Desktop\\orders");
			
			RESTUtil.createSchema("orders");
			RESTUtil.createResource("orders", "orderMika2.xml", new FileInputStream(new File(file, "orderMika2.xml")));
			RESTUtil.createResource("orders", "orderMika1.xml", new FileInputStream(new File(file, "orderMika1.xml")));
			System.out.println(RESTUtil.readString(RESTUtil.retrieveResource("//value/text()", "orders", RequestMethod.GET)));
			
			System.out.println("droping table 'orders'");
			RESTUtil.dropSchema("orders");
			System.out.println("table dropped");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//		createSchema("factbook");
//		createResource("factbook", "factbook.xml", new FileInputStream(new File(file, "factbook.xml")));
		
//		printStream(RESTUtil.retrieveResource("(//city/name)[position()= 10 to 15]", "factbook", RequestMethod.GET));
//		//printStream(RESTUtil.retrieveResource("(//city/name)[position()= 10 to 15]", "factbook", RequestMethod.GET));
//		
//		createSchema("firma");
//		createResource("firma", "uplatnica.xml", new FileInputStream(new File(file, "uplatnica_prazna.xml")));
//		
//		printStream(retrieveResource("//ulica/text()", "firma", RequestMethod.GET));
//		
//		updateResource("firma", "uplatnica.xml", new FileInputStream(new File(file, "uplatnica.xml")));
//		
//		printStream(retrieveResource("//ulica/text()", "firma", RequestMethod.GET));
//
//		/* XSD sema za REST query je data u src/main/resources folderu (mora se postovati ova gramatika za slanje zahteva. Nigde se ovaj fajl ne koristi, 
//		 * tu je za prikaz izgleda gramatike zahteva)*/
//		// CDATA zato sto hocemo da se obezbedimo da nas FLWOR izraz ostane u izvornom obliku i tako dodje do servera. Inace bi se mogli parsirati entiteti
//		// npr. u xQUERY se ne koristi < vec ^lt
//		String xml = "<query xmlns='http://basex.org/rest'>"
//				+ 		"<text>"
//				+ 			"<![CDATA[%s]]>"
//				+ 		"</text>"
//				+ 		"<parameter name='wrap' value='yes'/>"
//				+ 		"</query>";
//		String xquery = "for $i in //ulica/text() return string-length($i)";
//		// na mesto %s u CDATA se ugradjuje query string
//		printStream(retrieveResource(String.format(xml, xquery), "firma", RequestMethod.POST));
//		
//		dropSchema("factbook");
//		
//		deleteResource("firma", "uplatnica.xml");
//		dropSchema("firma");
		
		
		
	}

}
