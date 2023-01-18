
package rsd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rsd package. 
 * &lt;p&gt;An ObjectFactory allows you to programatically 
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

    private final static QName _Resolve_QNAME = new QName("http://rzk/", "resolve");
    private final static QName _ResolveResponse_QNAME = new QName("http://rzk/", "resolveResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rsd
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Resolve }
     * 
     */
    public Resolve createResolve() {
        return new Resolve();
    }

    /**
     * Create an instance of {@link ResolveResponse }
     * 
     */
    public ResolveResponse createResolveResponse() {
        return new ResolveResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Resolve }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Resolve }{@code >}
     */
    @XmlElementDecl(namespace = "http://rzk/", name = "resolve")
    public JAXBElement<Resolve> createResolve(Resolve value) {
        return new JAXBElement<Resolve>(_Resolve_QNAME, Resolve.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResolveResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ResolveResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://rzk/", name = "resolveResponse")
    public JAXBElement<ResolveResponse> createResolveResponse(ResolveResponse value) {
        return new JAXBElement<ResolveResponse>(_ResolveResponse_QNAME, ResolveResponse.class, null, value);
    }

}
