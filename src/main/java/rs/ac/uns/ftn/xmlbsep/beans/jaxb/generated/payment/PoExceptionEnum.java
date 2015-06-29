
package rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated.payment;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PoExceptionEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PoExceptionEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="invalidXml"/>
 *     &lt;enumeration value="debtorAccountDoesNotExist"/>
 *     &lt;enumeration value="creditorAccountDoesNotExist"/>
 *     &lt;enumeration value="debtorInsufficientFunds"/>
 *     &lt;enumeration value="invalidBankCode"/>
 *     &lt;enumeration value="invalidSwiftCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PoExceptionEnum", namespace = "http://www.ftn.uns.ac.rs/xws/xsd/paymentOrder")
@XmlEnum
public enum PoExceptionEnum {

    @XmlEnumValue("invalidXml")
    INVALID_XML("invalidXml"),
    @XmlEnumValue("debtorAccountDoesNotExist")
    DEBTOR_ACCOUNT_DOES_NOT_EXIST("debtorAccountDoesNotExist"),
    @XmlEnumValue("creditorAccountDoesNotExist")
    CREDITOR_ACCOUNT_DOES_NOT_EXIST("creditorAccountDoesNotExist"),
    @XmlEnumValue("debtorInsufficientFunds")
    DEBTOR_INSUFFICIENT_FUNDS("debtorInsufficientFunds"),
    @XmlEnumValue("invalidBankCode")
    INVALID_BANK_CODE("invalidBankCode"),
    @XmlEnumValue("invalidSwiftCode")
    INVALID_SWIFT_CODE("invalidSwiftCode");
    private final String value;

    PoExceptionEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PoExceptionEnum fromValue(String v) {
        for (PoExceptionEnum c: PoExceptionEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
