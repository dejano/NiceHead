package rs.ac.uns.ftn.xmlbsep.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by dejan on 26.5.2015..
 */
public class XMLSchemaConstraintValidator implements ConstraintValidator<ValidXMLSchemaConstraint, Object> {

    private String xmlSchemaLocation;

    public void initialize(ValidXMLSchemaConstraint constraintAnnotation) {
        System.out.println("XMLSchemaValidator.initialize");
        xmlSchemaLocation = constraintAnnotation.value();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        System.out.println("XMLSchemaValidator.isValid");
        return false;
    }
}
