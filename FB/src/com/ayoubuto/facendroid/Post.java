package com.ayoubuto.facendroid;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.util.Log;

public class Post extends TimeLynrActivity {

	private User FromUser = null;
	private String CommentLink = null;
	private String LikeLink = null;
	private Integer NLikes = 0;
	private Bitmap Picture = null;
	private String Type = null;
	private Integer NComments = 0;
	private String Message = null;
	private Date CreatedTime = null;
	private String Name = null;
	private String Description = null;
	private String ID = null;
	private Bitmap Icon = null;
	private String Link = null;

	public Post(JSONObject JSONObj) {

		try {

			FromUser = new User(JSONObj.getString("from"));
			ID = JSONObj.getString("id");
			Log.d("Facebook", "1P");

			Message = JSONObj.optString("message");
			Description = JSONObj.optString("description");
			Name = JSONObj.optString("name");
			Link = JSONObj.optString("link");
			Type = JSONObj.optString("type");
			String TmpS = JSONObj.optString("created_time");
			DateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
			try {
				CreatedTime = f.parse(TmpS);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Log.d("Facebook","1P");
			Log.d("Facebook", "2P");
			if (JSONObj.optString("picture").equals(""))
				Picture = null;
			else
				Picture = super.downloadFile(JSONObj.optString("picture"));

			if (JSONObj.optString("icon").equals(""))
				Icon = null;
			else
				Icon = super.downloadFile(JSONObj.optString("icon"));
			Log.d("Facebook", "3P");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.d("Facebook", "FINP");
	}

	public Date getCreatedTime() {
		return CreatedTime;
	}

	public String getMessage() {
		return Message;
	}

	public String getID() {
		return ID;
	}

	public User getFromUser() {
		return FromUser;
	}

	public String getCommentLink() {
		return CommentLink;
	}

	public String getLikeLink() {
		return LikeLink;
	}

	public String getLink() {
		return Link;
	}

	public String getType() {
		return Type;
	}

	public Integer getNLikes() {
		return NLikes;
	}

	public Integer getNComments() {
		return NComments;
	}

	public Bitmap getPicture() {
		return Picture;
	}

	public Bitmap getIcon() {
		return Icon;
	}

	public boolean isVideo() {
		if (Type.equals("video"))
			return true;
		return false;
	}

	public boolean isLink() {
		if (Type.equals("link") == true)
			return true;
		return false;
	}

	public boolean isPhoto() {
		if (Type.equals("photo") == true)
			return true;
		return false;
	}

	public String getName() {
		return Name;
	}

	public String getDescription() {
		return Description;
	}

	public boolean isStatus() {
		if (Type.equals("status") == true)
			return true;
		return false;
	}
}
