package rs.ac.uns.ftn.xmlbsep.dao.impl;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import rs.ac.uns.ftn.xmlbsep.beans.jaxb.Config;
import rs.ac.uns.ftn.xmlbsep.beans.jaxb.User;
import rs.ac.uns.ftn.xmlbsep.dao.ConfigDao;
import rs.ac.uns.ftn.xmlbsep.dao.UserDaoLocal;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.net.UnknownHostException;
import java.util.List;

@Stateless
@Local(ConfigDao.class)
public class ConfigDaoImpl implements ConfigDao {

    @Context
    HttpServletRequest request;

    @Override
    public Config get() {
        final Morphia morphia = new Morphia();
// tell Morphia where to find your classes
// can be called multiple times with different packages or classes
        morphia.mapPackage("rs.ac.uns.ftn.xmlbsep.beans.jaxb");
        MongoClient mongoClient = null;
        try {
            // create the Datastore connecting to the default port on the local host
            mongoClient = new MongoClient();
            final Datastore datastore = morphia.createDatastore(mongoClient, "company");
            datastore.ensureIndexes();

            List<Config> configs = datastore.find(Config.class).asList();
            if (configs.size() > 0) {
                return configs.get(0);
            }
            mongoClient.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } finally {
            if (mongoClient != null) {
                mongoClient.close();
            }
        }
        return null;
    }

}
