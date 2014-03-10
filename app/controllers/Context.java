package controllers;

import org.mongojack.JacksonDBCollection;
import play.mvc.Controller;
import util.CollectionService;

public class Context extends Controller {

    protected static CollectionService collectionService;

    public static JacksonDBCollection getCollection(String collection, Class clazz) {
        if(collectionService == null)
            collectionService = new CollectionService();

        return collectionService.getCollection(collection, clazz);
    }

}
