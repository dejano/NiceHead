package rs.ac.uns.ftn.xmlbsep.beans.jaxb;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * Created by dejan on 27.5.2015..
 */
public abstract class AbstractBaseEntity<T> implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3708574669911748178L;

    /**
     * @return id
     */
    @XmlTransient
    public abstract T getId();

    /**
     * @param id
     */
    public abstract void setId(T id);

}
