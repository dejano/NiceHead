
package rs.ac.uns.ftn.xws.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MpExceptionEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MpExceptionEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="debtorBankHasInsufficientFunds"/>
 *     &lt;enumeration value="invalidSwiftCode"/>
 *     &lt;enumeration value="invalidAmount"/>
 *     &lt;enumeration value="multipleBanks"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MpExceptionEnum", namespace = "http://www.ftn.uns.ac.rs/xws/xsd/mp")
@XmlEnum
public enum MpExceptionEnum {

    @XmlEnumValue("debtorBankHasInsufficientFunds")
    DEBTOR_BANK_HAS_INSUFFICIENT_FUNDS("debtorBankHasInsufficientFunds"),
    @XmlEnumValue("invalidSwiftCode")
    INVALID_SWIFT_CODE("invalidSwiftCode"),
    @XmlEnumValue("invalidAmount")
    INVALID_AMOUNT("invalidAmount"),
    @XmlEnumValue("multipleBanks")
    MULTIPLE_BANKS("multipleBanks");
    private final String value;

    MpExceptionEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MpExceptionEnum fromValue(String v) {
        for (MpExceptionEnum c: MpExceptionEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
