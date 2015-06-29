package rs.ac.uns.ftn.xmlbsep.validation;

import rs.ac.uns.ftn.xmlbsep.exception.InvalidXMLException;
import rs.ac.uns.ftn.xmlbsep.security.HasPermission;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.util.JAXBSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

@Interceptor
@ValidXMLSchema
public class XMLSchemaValidatorInterceptor {

    public XMLSchemaValidatorInterceptor() {
        super();
    }

    @AroundInvoke
//    @Authenticate
    public Object intercept(InvocationContext context) throws Exception {

        ValidXMLSchema annotation = context.getMethod().getAnnotation(ValidXMLSchema.class);
        if (annotation == null) {
            System.out.println("XMLSchemaValidatorInterceptor.intercept");
            return context.proceed();
        }

        String XSDPath = annotation.value();
        Class clazz = annotation.clazz();
        Object obj = null;

        for (Object object : context.getParameters()) {
            if (object.getClass().equals(clazz)) {
                System.out.println("FOUND OBJECT");
                obj = object;
                break;
            }
        }

        if (obj == null) {
            System.out.println("XMLSchemaValidatorInterceptor.intercept");
            return context.proceed();
        }

        try {
            JAXBContext jc = JAXBContext.newInstance(clazz);

            JAXBSource source = new JAXBSource(jc, obj);

            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(XMLSchemaValidatorInterceptor.class.getResource(XSDPath));

            Validator validator = schema.newValidator();
            validator.validate(source);

        } catch (Exception e) {
            System.out.println("XMLSchemaValidator.validate: Invalid");
            e.printStackTrace();
            throw new InvalidXMLException(e.getMessage());
        }
        System.out.println("XMLSchemaValidator.validate Valid");

        return context.proceed();
    }

}
