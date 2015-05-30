package rs.ac.uns.ftn.xmlbsep.util;

import org.xml.sax.SAXException;
import rs.ac.uns.ftn.xmlbsep.rest.InvoiceController;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;

/**
 * Created by dejan on 25.5.2015..
 */
public class XMLUtil {

    public static void validate(Object object, String path) throws JAXBException, SAXException, IOException {
        JAXBContext jc = JAXBContext.newInstance(object.getClass());
        JAXBSource source = new JAXBSource(jc, object);

        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(InvoiceController.class.getResource(path));

        Validator validator = schema.newValidator();
        validator.validate(source);
    }

    public static InputStream createInputStreamFromJaxObject(Object o) {
        InputStream in = null;
        try {
            in = new ByteArrayInputStream(createStringFromJaxObject(o).getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return in;
    }

    public static String createStringFromJaxObject(Object o) {
        StringWriter sw = new StringWriter();
        JAXB.marshal(o, sw);
        return sw.toString();
    }
//        Invoice person = JAXB.unmarshal(new StringReader("<?xml ..."), Person.class);
}
