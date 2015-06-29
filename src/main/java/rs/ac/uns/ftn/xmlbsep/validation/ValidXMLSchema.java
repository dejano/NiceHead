package rs.ac.uns.ftn.xmlbsep.validation;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@InterceptorBinding
@Target({METHOD, TYPE})
@Retention(RUNTIME)
public @interface ValidXMLSchema {
    @Nonbinding String value() default "";
    @Nonbinding Class<?> clazz() default Class.class;
}
