package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongojack.MongoCollection;
import org.mongojack.ObjectId;

@MongoCollection(name = "namaste")
public class Namaste {

	private String id;

	@ObjectId
	@JsonProperty("_id")
	public String getId(){
		return id;
	}
}