package rs.ac.uns.ftn.xmlbsep.dao.impl;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import rs.ac.uns.ftn.xmlbsep.beans.jaxb.User;
import rs.ac.uns.ftn.xmlbsep.dao.UserDaoLocal;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.net.UnknownHostException;
import java.util.List;

@Stateless
@Local(UserDaoLocal.class)
public class UserDaoImpl implements UserDaoLocal {

    @Context
    HttpServletRequest request;

    public User login(String username, String password) {
        final Morphia morphia = new Morphia();
// tell Morphia where to find your classes
// can be called multiple times with different packages or classes
        morphia.mapPackage("rs.ac.uns.ftn.xmlbsep.beans.jaxb");
        try {
            // create the Datastore connecting to the default port on the local host
            MongoClient mongoClient = new MongoClient();
            final Datastore datastore = morphia.createDatastore(mongoClient, "company");
            datastore.ensureIndexes();

            List<User> user = datastore.createQuery(User.class).field("username").equal(username).field("password").equal(password).asList();
            if (user.size() > 0) {
                request.getSession().setAttribute("user", user.get(0));
                return user.get(0);
            }
            mongoClient.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void logout() {
        request.getSession().invalidate();
    }
}
