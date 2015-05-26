package rs.ac.uns.ftn.xmlbsep.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = {XMLSchemaValidator.class})
public @interface ValidXMLSchema {
    String message() default "Provided XML file isn't valid.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String value();
}
