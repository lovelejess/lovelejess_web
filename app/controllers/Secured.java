package controllers;

import play.mvc.*;
import play.mvc.Http.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongojack.MongoCollection;
import org.mongojack.ObjectId;


import models.*;

public class Secured extends Security.Authenticator {
    
    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get("email");
    }
    
    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(routes.Application.login());
    }

}
