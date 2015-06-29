package rs.ac.uns.ftn.xmlbsep.beans.jaxb;

import rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated.invoice.Invoice;
import rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated.invoice.Item;
import rs.ac.uns.ftn.xmlbsep.beans.jaxb.generated.partner.Partner;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by dejan on 28.5.2015..
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "result"
})
@XmlRootElement( namespace = "http://www.ftn.uns.ac.rs/xmlbsep/company/invoice")
@XmlSeeAlso({Invoice.class, Item.class, Partner.class})
public class ResultWrapper {

    public ResultWrapper() {
    }

    @XmlAnyElement(lax = true)
    private List result;

    public void setResult(List result) {
        this.result = result;
    }

}
