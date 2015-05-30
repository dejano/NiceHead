package rs.ac.uns.ftn.xmlbsep.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = {XMLSchemaConstraintValidator.class})
public @interface ValidXMLSchemaConstraint {
    String message() default "Provided XML file isn't valid.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String value();
}
