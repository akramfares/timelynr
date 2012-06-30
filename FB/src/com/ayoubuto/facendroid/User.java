package com.ayoubuto.facendroid;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;

public class User extends TimeLynrActivity{

	public Bitmap getPicture() {
		return Picture;
	}
	private String ID;
	private String Name;
	private String FirstName;
	private String LastName;
	private String Link;
	private Bitmap Picture;
	
	public User(String JSON){
		JSONObject Info;
		try {
			Info = new JSONObject(JSON);
			ID=Info.optString("id");
			Name=Info.optString("name");
			FirstName=Info.optString("first_name");
			LastName=Info.optString("last_name");
			Link=Info.optString("link");
			Picture=super.downloadFile("http://graph.facebook.com/"+ID+"/picture");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public String getID(){
		return ID;
	}
	public String getName(){
		return Name;
	}
	
	public String getFirstName() {
		return FirstName;
	}

	public String getLastName() {
		return LastName;
	}

	public String getLink(){
		return Link;
	}
	
}
