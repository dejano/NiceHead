package rs.ac.uns.ftn.xmlbsep.security.beans;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("object")
public class RbacObject {

    @Id
    private String id;
    private String name;

    public RbacObject() {
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
        return "RbacObject{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
