package rs.ac.uns.ftn.xmlbsep.validation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by dejan on 26.5.2015..
 */
@Aspect
public class Logging {

//    @Before("execution(* rs.ac.uns.ftn.xmlbsep..*(..))")
//    public void logAround(JoinPoint joinPoint) throws Throwable {
//
//        System.out.println("logAround() is running!");
//        System.out.println("hijacked method : " + joinPoint.getSignature().getName());
//        System.out.println("hijacked arguments : " + Arrays.toString(joinPoint.getArgs()));
//
//        System.out.println("Around before is running!");
//        System.out.println("Around after is running!");
//
//        System.out.println("******");
//    }

//    @Around("execution(* rs.ac.uns.ftn.xmlbsep.service.impl.InvoiceServiceImpl.save(..))")
//    public Object getnamee(ProceedingJoinPoint point) throws Throwable {
//        System.out.println("Logging.getnamee");
//
//        return point.proceed();
//    }

}

