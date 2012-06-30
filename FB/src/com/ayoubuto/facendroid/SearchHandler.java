package com.ayoubuto.facendroid;

import java.net.MalformedURLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class SearchHandler extends TimeLynrActivity {
	private Post[] Posts = null;
	private Integer TPosts;
	private String Keyword;

	public SearchHandler(String Keyword){

		try {
			
			String JSON = getURLContent("https://graph.facebook.com/search?q=" + Keyword +"&limit=5&type=post");
			JSONObject Tmp = null;
			Tmp = new JSONObject(JSON);
			JSONArray PostsObj;
			PostsObj = Tmp.getJSONArray("data");
			Log.d("Facebook", "4");
			int T = PostsObj.length();
			Post[] PostF = new Post[T];
			Log.d("Facebook", "5");

			Log.d("Facebook", "Total : " + T);

			int j, i;
			for (j = 0, i = 0; i < T; i++, j++) {
				Log.d("Facebook", "JSONID : " + i);
				PostF[j] = new Post(PostsObj.getJSONObject(i));
				if (!PostF[j].isLink() && !PostF[j].isPhoto()
						&& !PostF[j].isStatus() && !PostF[j].isVideo())
					j--;
				else if (PostF[j].getDescription().equals("") == true
						&& PostF[j].getMessage().equals("") == true
						&& PostF[j].getName().equals("") == true
						&& PostF[j].getNLikes() == 0
						&& PostF[j].getNComments() == 0)
					j--;
				// Log.d("Facebook","Posts["+j+"]: "+PostF[j].getID());
			}
			Log.d("Facebook", "6");
			Posts = new Post[j];
			TPosts = j;
			for (i = 0; i < j; i++) {
				Posts[i] = PostF[i];
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.d("Facebook", "F");
	}
	
	
	public static String getURLContent(String url) throws Exception {
		HttpClient client = new DefaultHttpClient();
		String getURL = url;

		HttpGet get = new HttpGet(getURL);
		// LLLog.d("Station", "GetContentOKFct1");
		HttpResponse responseGet = client.execute(get);
		// LLLog.d("Station", "GetContentOKFct2");
		return EntityUtils.toString(responseGet.getEntity());
	}

	public Post getPosts(int index) {
		return Posts[index];
	}

	public Integer getTPosts() {
		return TPosts;
	}
}
