package rs.ac.uns.ftn.xmlbsep.beans.jaxb;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;
import rs.ac.uns.ftn.xmlbsep.security.beans.Permission;
import rs.ac.uns.ftn.xmlbsep.security.beans.Role;

import java.util.List;

@Entity("user")
public class User {

    @Id
    private String id;

    @Reference
    private List<Role> roles;

    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", roles=" + roles +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public boolean hasPermission(String neededPerm) {
        for (Role role : roles) {
            for (Permission permission : role.getPermissions()) {
                System.out.println(permission.getName());
                if (permission.getName().equals(neededPerm)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean hasRole(String neededRole) {
        for (Role role : roles) {
            if (role.getName().equals(neededRole)) {
                return true;
            }
        }

        return false;
    }
}
