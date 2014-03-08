package util;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;

import java.net.UnknownHostException;

public class CollectionService<T> {

    public DB getDB() {

        try {
            MongoClient mongoClient = new MongoClient("ds033699.mongolab.com", 33699);

            DB namaste_DB = mongoClient.getDB("namaste");

            //todo this needs to go in an ignored config file
            namaste_DB.authenticate("namaste_user", "rzDT4D64m5".toCharArray());

            return namaste_DB;

        } catch (UnknownHostException e) {
            return null;
        }
    }

    public JacksonDBCollection getCollection(String collection,Class clazz){
        try{
            return JacksonDBCollection.wrap(getDB().getCollection(collection),clazz);
        
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}