package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*;
import views.html.*;


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

public class Application extends Controller {
    public static Result index() {
       	return ok(index.render(""));
    }
    public static Result eat() {
        return ok(eat.render("eat"));
    }
    public static Result playy() {
        return ok(playy.render("play"));
    }
    public static Result work() {
       return ok(work.render("work"));
    }
    public static Result namaste() {
        return ok(namaste.render("namaste"));
    }
    public static Result archivedEats(){
        return ok(archivedEats.render("archivedEats"));
    }
    public static Result contact(){
        return ok(contact.render("Contact"));
    }
}

