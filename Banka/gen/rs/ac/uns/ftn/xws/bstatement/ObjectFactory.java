
package rs.ac.uns.ftn.xws.bstatement;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ac.uns.ftn.xws.bstatement package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Asset_QNAME = new QName("http://www.ftn.uns.ac.rs/xws/xsd/bstatement", "asset");
    private final static QName _AssetsData_QNAME = new QName("http://www.ftn.uns.ac.rs/xws/xsd/bstatement", "assetsData");
    private final static QName _SectionalHeader_QNAME = new QName("http://www.ftn.uns.ac.rs/xws/xsd/bstatement", "sectionalHeader");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.xws.bstatement
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AssetsData }
     * 
     */
    public AssetsData createAssetsData() {
        return new AssetsData();
    }

    /**
     * Create an instance of {@link SectionalData }
     * 
     */
    public SectionalData createSectionalData() {
        return new SectionalData();
    }

    /**
     * Create an instance of {@link SectionalHeader }
     * 
     */
    public SectionalHeader createSectionalHeader() {
        return new SectionalHeader();
    }

    /**
     * Create an instance of {@link Asset }
     * 
     */
    public Asset createAsset() {
        return new Asset();
    }

    /**
     * Create an instance of {@link AssetsData.Assets }
     * 
     */
    public AssetsData.Assets createAssetsDataAssets() {
        return new AssetsData.Assets();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Asset }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/xws/xsd/bstatement", name = "asset")
    public JAXBElement<Asset> createAsset(Asset value) {
        return new JAXBElement<Asset>(_Asset_QNAME, Asset.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssetsData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/xws/xsd/bstatement", name = "assetsData")
    public JAXBElement<AssetsData> createAssetsData(AssetsData value) {
        return new JAXBElement<AssetsData>(_AssetsData_QNAME, AssetsData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SectionalHeader }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/xws/xsd/bstatement", name = "sectionalHeader")
    public JAXBElement<SectionalHeader> createSectionalHeader(SectionalHeader value) {
        return new JAXBElement<SectionalHeader>(_SectionalHeader_QNAME, SectionalHeader.class, null, value);
    }

}
