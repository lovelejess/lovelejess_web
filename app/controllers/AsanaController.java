package controllers;
 
 
import com.fasterxml.jackson.databind.JsonNode;
import models.Asana;
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
 
public class AsanaController {
 
    public static Result allAsanas(){
        List<Asana> asanas = new Model.Finder<String , Asana>(String.class, Asana.class).all();
        return Results.ok(Json.toJson(asanas));
    }
 
    @BodyParser.Of(BodyParser.Json.class)
    public static Result submitAsana(){
        JsonNode jsonNode = Controller.request().body().asJson();
        String url = jsonNode.findPath("url").asText();
        String fullname = jsonNode.findPath("fullname").asText();
        JsonNode response = fetchInformation(url);
        Asana asana = null;
        if(response == null){
            asana = new Asana(url,fullname);
        }else{
            String image = response.findPath("image").textValue();
            String text = response.findPath("text").textValue();
            String title = response.findPath("title").textValue();
            asana = new Asana(url,fullname, image , text , title);
        }
        asana.save();
        return Results.created();
    }
 
    public static Result getAsana(String asanaId){
        Asana asana = new Model.Finder<String, Asana>(String.class, Asana.class).byId(asanaId);
        if(asana == null){
            return Results.notFound("No asana found with asanaId " + asanaId);
        }
        return Results.ok(Json.toJson(asana));
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