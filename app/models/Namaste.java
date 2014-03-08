package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongojack.MongoCollection;
import org.mongojack.ObjectId;

@MongoCollection(name = "namaste")
public class Namaste {

	private String id;
	private String fileName;


	@ObjectId
	@JsonProperty("_id")
	public String getId(){
		return id;
	}

	public Namaste(){}

	public Namaste(String fileName){
	this.fileName = fileName;
	}
}