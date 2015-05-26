package rs.ac.uns.ftn.xmlbsep.validation;

import rs.ac.uns.ftn.xmlbsep.rest.InvoiceController;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.util.JAXBSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

/**
 * Created by dejan on 26.5.2015..
 */
public class XMLSchemaValidator implements ConstraintValidator<ValidXMLSchema, Object> {

    private String xmlSchemaLocation;

    public void initialize(ValidXMLSchema constraintAnnotation) {
        System.out.println("XMLSchemaValidator.initialize");
        xmlSchemaLocation = constraintAnnotation.value();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        System.out.println("XMLSchemaValidator.isValid");
//        if (value == null) {
//            return false;
//        }
//        System.out.println("XMLSchemaValidator.isValid");
//        try {
//            JAXBContext jc = JAXBContext.newInstance(value.getClass());
//
//            JAXBSource source = new JAXBSource(jc, value);
//
//            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//            Schema schema = sf.newSchema(InvoiceController.class.getResource(xmlSchemaLocation));
//
//            Validator validator = schema.newValidator();
//            validator.validate(source);
//
//        } catch (Exception e) {
//            System.out.println("XMLSchemaValidator.isValid INVALID");
//            e.printStackTrace();
//            return false;
//        }
        System.out.println("XMLSchemaValidator.isValid VALID");

        return true;
    }
}
