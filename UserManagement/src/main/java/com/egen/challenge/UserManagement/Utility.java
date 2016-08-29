package com.egen.challenge.UserManagement;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class Utility {
	public static String toJson(Object object) {
		return new Gson().toJson(object);
	}

	public static ResponseTransformer json() {
		return Utility::toJson;
	}
	
	public static User fromJson(String json){
		
		return new Gson().fromJson(json, User.class);
	}
}
