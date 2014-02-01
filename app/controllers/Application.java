package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*;
import views.html.*;

public class Application extends Controller {

    public static class Login {
        
        public String email;
        public String password;
        
        public String validate() {
            if(User.authenticate(email, password) == null) {
                return "Invalid user or password";
            }
            return null;
        }
    }    
    public static Result login() {
        System.out.println("login page");
        return ok(
            login.render(form(Login.class)));
    }
    public static Result index() {
       	return ok(index.render(""));
    }
    public static Result authenticate() {
        System.out.println("authenticate");
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            System.out.println("has bad errors"); 
            return badRequest(login.render(loginForm));
        } else {
            session("email", loginForm.get().email);
            System.out.println("redirecting");
            return redirect(
                routes.Application.index());
            }
    }
    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(
            routes.Application.login()
        );
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
/*
  * mongo.local.hostname=localhost
  * mongo.local.port=27017
  * mongo.remote.hostname=
  * mongo.remote.port=25189
  * mongo.remote.username=
  * mongo.remote.password=
  */
  //   private static DB getDb() {
  //     String userName = play.Configuration.root().getString("mongo.remote.username");
  //     String password = play.Configuration.root().getString("mongo.remote.password");
  //     boolean local  = true;
    
  //     String localHostName = play.Configuration.root().getString("mongo.local.hostname");
  //     Integer  localPort = play.Configuration.root().getInt("mongo.local.port");
      
  //     String remoteHostName = play.Configuration.root().getString("mongo.remote.hostname");
  //     Integer remotePort = play.Configuration.root().getInt("mongo.remote.port");

  //     Mongo m;
  //     DB db = null;
  //     if(local){
  //       String hostname = localhost;
  //       int port = 27017;
  //       try {
  //       m = new Mongo( hostname, port);   
  //       db = m.getDB( "db" );
  //       }catch(Exception e) {
  //          Logger.error("Exception while intiating Local MongoDB", e);    
  //       }
  //     }else {
  //         String hostname = remoteHostName;
  //           int port = remotePort;
  //           try {
  //           m = new Mongo( hostname , port);   
  //           db = m.getDB( "db" );
  //           boolean auth = db.authenticate(userName, password.toCharArray());
  //           }catch(Exception e) {
  //               Logger.error("Exception while intiating Local MongoDB", e);    
  //           }
  //     }
  //     return db;
  // }
}

