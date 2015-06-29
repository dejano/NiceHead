package rs.ac.uns.ftn.xmlbsep.mongodb;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.net.UnknownHostException;

public class EntityManagerMongoDB {

    private final MongoClient mongoClient;
    private final Morphia morphia;
    private final Datastore datastore;

    public EntityManagerMongoDB() throws UnknownHostException {
        mongoClient = new MongoClient();
        morphia = new Morphia();
        morphia.mapPackage("rs.ac.uns.ftn.xmlbsep.beans.jaxb");
        datastore = morphia.createDatastore(mongoClient, "company");
    }

    void findAll() {

    }

    void findById() {

    }

    void findBy() {

    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public Morphia getMorphia() {
        return morphia;
    }

    public Datastore getDatastore() {
        return datastore;
    }
}
