package rs.ac.uns.ftn.xmlbsep.security;


import rs.ac.uns.ftn.xmlbsep.beans.jaxb.User;
import rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated.invoice.Invoice;
import rs.ac.uns.ftn.xmlbsep.exception.UnauthorizedAccessException;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.xml.datatype.XMLGregorianCalendar;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;

@Stateless
@Local(InvoiceFactory.class)
@Remote(InvoiceFactory.class)
public class InvoiceFactoryImpl implements InvoiceFactory {

    @Context
    private HttpServletRequest request;

    @Override
    public void merge(Invoice invoice, Invoice oldInvoice) throws UnauthorizedAccessException, IllegalStateException {
        methods(invoice, oldInvoice);
    }

    private void methods(Object object, Object object2) throws UnauthorizedAccessException, IllegalStateException {
        for (Method getterMethod : object.getClass().getDeclaredMethods()) {
            Class returnType = getterMethod.getReturnType();
            String getterName = getterMethod.getName();

            if (getterName.startsWith("get")) {
                String setterName = getterName.replaceFirst("get", "set");

                try {
                    // check if setter exist
                    if (object.getClass().getDeclaredMethod(setterName, returnType) != null) {

                        if (returnType.isAssignableFrom(String.class) || returnType.isAssignableFrom(Integer.class) ||
                                returnType.isAssignableFrom(Double.class) || returnType.isAssignableFrom
                                (BigInteger.class) || returnType.isAssignableFrom(BigDecimal.class) || returnType.isAssignableFrom(XMLGregorianCalendar.class)
                                || returnType.isInstance(Integer.class)) {
//                            System.out.println("Primitive: " + object.getClass().getName() + "->" + getterName);
                            boolean equals = compare(getterMethod.invoke(object), getterMethod.invoke(object2));
                            if (!equals) {
                                invokeSetter(object, object2, getterMethod, returnType, setterName);
                            }
                            System.out.println(getterName + " equals: " + equals);

                        } else {
//                            System.out.println("Non-Primitive: " + object.getClass().getName() + "->" + getterMethod.getName());
                            methods(getterMethod.invoke(object), getterMethod.invoke(object2));
                        }
                    }
                } catch (UnauthorizedAccessException e) {
                    throw e;
                } catch (IllegalStateException e) {
                    throw e;
                } catch (NoSuchMethodException e) {
                    System.out.println("No such getterMethod " + e.getMessage());
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    private void invokeSetter(Object object, Object object2, Method getterMethod, Class returnType, String setterName) throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, UnauthorizedAccessException, IllegalStateException {

        Method setter = object2.getClass().getDeclaredMethod(setterName, returnType);
        HasPermission hasPermission = setter.getAnnotation(HasPermission.class);
        if (hasPermission != null) {
            String neededPermission = hasPermission.value();
            verifyPermission(neededPermission);
        }

        setter.invoke(object2, getterMethod.invoke(object));
    }

    private boolean compare(Object object1, Object object2) {
        System.out.println(object1.toString() + " == " + object2.toString());
        return object1.equals(object2);
    }

    private void verifyPermission(String neededPermission) throws UnauthorizedAccessException, IllegalStateException {

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            throw new UnauthorizedAccessException("Not logged in");
        }

        if (!user.hasPermission(neededPermission)) {
            throw new IllegalStateException("Do not have permision " + neededPermission);
        }
    }

}
