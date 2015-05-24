//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.05.24 at 08:28:05 PM CEST 
//


package rs.ac.uns.ftn.xws.generated.bs;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ac.uns.ftn.xws.generated.bs package. 
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

    private final static QName _StatementRequest_QNAME = new QName("http://www.ftn.uns.ac.rs/xws/xsd/bs", "statementRequest");
    private final static QName _Statement_QNAME = new QName("http://www.ftn.uns.ac.rs/xws/xsd/bs", "statement");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.xws.generated.bs
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Statement }
     * 
     */
    public Statement createStatement() {
        return new Statement();
    }

    /**
     * Create an instance of {@link StatementRequest }
     * 
     */
    public StatementRequest createStatementRequest() {
        return new StatementRequest();
    }

    /**
     * Create an instance of {@link StatementItem }
     * 
     */
    public StatementItem createStatementItem() {
        return new StatementItem();
    }

    /**
     * Create an instance of {@link Statement.Items }
     * 
     */
    public Statement.Items createStatementItems() {
        return new Statement.Items();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StatementRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/xws/xsd/bs", name = "statementRequest")
    public JAXBElement<StatementRequest> createStatementRequest(StatementRequest value) {
        return new JAXBElement<StatementRequest>(_StatementRequest_QNAME, StatementRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Statement }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/xws/xsd/bs", name = "statement")
    public JAXBElement<Statement> createStatement(Statement value) {
        return new JAXBElement<Statement>(_Statement_QNAME, Statement.class, null, value);
    }

}
