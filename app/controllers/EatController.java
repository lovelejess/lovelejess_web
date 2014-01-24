package controllers;
 
 
import com.fasterxml.jackson.databind.JsonNode;
import models.Eat;
import play.api.libs.ws.Response;
import play.api.libs.ws.WS;
import play.db.ebean.Model;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
 
import java.util.List;
import java.util.concurrent.TimeUnit;
 
public class EatController {
 
    public static Result allEats(){
        List<Eat> eats = new Model.Finder<String,Eat>(String.class, Eat.class).all();
        return Results.ok(Json.toJson(eats));
    }
 
    @BodyParser.Of(BodyParser.Json.class)
    public static Result submitEat(){
        JsonNode jsonNode = Controller.request().body().asJson();
        String url = jsonNode.findPath("url").asText();
        String fullname = jsonNode.findPath("fullname").asText();
        JsonNode response = fetchInformation(url);
        Eat eat = null;
        if(response == null){
            eat = new Eat(url,fullname);
        }else{
            String image = response.findPath("image").textValue();
            String text = response.findPath("text").textValue();
            String title = response.findPath("title").textValue();
            eat = new Eat(url,fullname, image , text , title);
        }
        eat.save();
        return Results.created();
    }
 
    public static Result getEat(String eatId){
        Eat eat = new Model.Finder<String, Eat>(String.class, Eat.class).byId(eatId);
        if(eat == null){
            return Results.notFound("No archivedeats found with eatId " + eatId);
        }
        return Results.ok(Json.toJson(eat));
    }
 
    private static JsonNode fetchInformation(String url){
        String restServiceUrl = "http://gooseextractor-t20.rhcloud.com/api/v1/extract?url="+url;
        Future<Response> future = WS.url(restServiceUrl).get();
        try {
            Response result = Await.result(future, Duration.apply(30, TimeUnit.SECONDS));
            JsonNode jsonNode = Json.parse(result.json().toString());
            return jsonNode;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
 
    }
 
}