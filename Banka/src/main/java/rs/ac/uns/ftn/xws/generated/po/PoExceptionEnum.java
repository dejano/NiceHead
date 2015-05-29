
package rs.ac.uns.ftn.xws.generated.po;

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
 *     &lt;enumeration value="foo"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PoExceptionEnum")
@XmlEnum
public enum PoExceptionEnum {

    @XmlEnumValue("foo")
    FOO("foo");
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
