package com.ayoubuto.facendroid;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class StreamParser {

	public static String getURLContent(String url) throws Exception {
		HttpClient client = new DefaultHttpClient();
		String getURL = url;

		HttpGet get = new HttpGet(getURL);
		// LLLog.d("Station", "GetContentOKFct1");
		HttpResponse responseGet = client.execute(get);
		// LLLog.d("Station", "GetContentOKFct2");
		return EntityUtils.toString(responseGet.getEntity());
	}
}
