package rs.ac.uns.ftn.xmlbsep.security;


import rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated.invoice.Invoice;

import javax.xml.datatype.XMLGregorianCalendar;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;

public class ItemFactory {
    public static void merge(Invoice invoice, Invoice oldInvoice) {
        methods(invoice, oldInvoice);
    }

    private static void methods(Object object, Object object2) {
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
                                Method setter = object2.getClass().getDeclaredMethod(setterName, returnType);
                                setter.invoke(object2, getterMethod.invoke(object));
                            }
                            System.out.println(getterName + " equals: " + equals);

                        } else {
//                            System.out.println("Non-Primitive: " + object.getClass().getName() + "->" + getterMethod.getName());
                            methods(getterMethod.invoke(object), getterMethod.invoke(object2));
                        }
                    }
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

    private static boolean compare(Object object1, Object object2) {
        System.out.println(object1.toString() + " == " + object2.toString());
        return object1.equals(object2);
    }

}
