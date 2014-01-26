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
    /**
     * Login page.
     */
    public static Result login() {
        return ok(
            login.render(form(Login.class))
        );
    }

    public static Result index() {
       	return ok(index.render(""));
    }
      // -- Authentication
    
    /**
     * Handle login form submission.
     */
    public static Result authenticate() {
    Form<Login> loginForm = form(Login.class).bindFromRequest();
    if (loginForm.hasErrors()) {
        return badRequest(login.render(loginForm));
    } else {
        session("email", loginForm.get().email);
        return redirect(
            //routes.Application.index()
            "http://www.npr.org/"
        );
        }
    }

    /**
     * Logout and clean the session.
     */
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
}

