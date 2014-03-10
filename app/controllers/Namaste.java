package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*;
import views.html.*;
import util.*;

import java.util.LinkedList;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.io.IOException;
import play.libs.Json;

public class Namaste extends Context{

	public static JacksonDBCollection<models.Namaste,String> namasteDB;
	public static Result displayPhotos(){
		try{

			MongoClient mongoClient = new MongoClient("ds033699.mongolab.com", 33699);

            DB namaste_DB = mongoClient.getDB("namaste");

            //todo this needs to go in an ignored config file
            namaste_DB.authenticate("namaste_user", "rzDT4D64m5".toCharArray());

			GridFS fsNamaste = new GridFS(namaste_DB);

			DBCursor cursor = fsNamaste.getFileList();
			while (cursor.hasNext()){
				System.out.println(cursor.next());
			}

		} catch (UnknownHostException e){
			e.printStackTrace();
		} catch (MongoException e){
			e.printStackTrace();
		}

		 return ok(Json.toJson(getCollection("namaste", models.Namaste.class).find().toArray()));
	}
}