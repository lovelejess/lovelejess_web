package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
       	return ok(index.render("index"));
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


}
