package rs.ac.uns.ftn.xmlbsep.beans.jaxb;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@Entity("config")
@XmlRootElement(name = "config", namespace = "")
public class Config {

    @Id
    private String id;
    private String name;
    private String address;
    private String url;
    private String pib;
    private List<String> accounts;

    public Config() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public List<String> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<String> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "Config{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", url='" + url + '\'' +
                ", pib='" + pib + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
