package rs.ac.uns.ftn.xmlbsep.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) {
        MongoClient mongoClient = null;
        try {
            mongoClient = new MongoClient("localhost", 27017);
            DB db = mongoClient.getDB("company");
            BasicDBObject document = new BasicDBObject();
            document.put("username", "user1");
            DBCursor cursor = db.getCollection("user").find();
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }

            mongoClient.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
