package rs.ac.uns.ftn.xmlbsep.validation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import rs.ac.uns.ftn.xmlbsep.exception.InvalidXMLException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.util.JAXBSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

@Aspect
public class XMLSchemaValidator {

    @Before("execution(* rs.ac.uns.ftn.xmlbsep.rest.InvoiceController.*(..))")
    public void validate(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        ValidXMLSchema annotation = signature.getMethod().getAnnotation(ValidXMLSchema.class);
        if (annotation == null) {
            return;
        }

        String XSDPath = annotation.value();
        Class clazz = annotation.clazz();
        Object obj = null;

        Object[] signatureArgs = joinPoint.getArgs();
        for (Object signatureArg : signatureArgs) {
            if (signatureArg.getClass().equals(clazz)) {
                obj = signatureArg;
                break;
            }
        }

        if (obj == null) {
            return;
        }

        try {
            JAXBContext jc = JAXBContext.newInstance(clazz);

            JAXBSource source = new JAXBSource(jc, obj);

            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(XMLSchemaValidator.class.getResource(XSDPath));

            Validator validator = schema.newValidator();
            validator.validate(source);

        } catch (Exception e) {
            System.out.println("XMLSchemaValidator.validate: Invalid");
            throw new InvalidXMLException(e.getMessage());
        }
        System.out.println("XMLSchemaValidator.validate Valid");
    }
}
