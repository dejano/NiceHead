package rs.ac.uns.ftn.xmlbsep.security.beans;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("operation")
public class Operation {

    @Id
    private String id;
    private String name;

    public Operation() {
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

    @Override
    public String toString() {
        return "Operation{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
