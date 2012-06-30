package com.ayoubuto.facendroid;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class TimeLynrActivity extends Activity {
	/** Called when the activity is first created. */
	TextView T1;
	Button Login, Logout, Post;
	SearchHandler SH = null;
	ImageView ImageFP;
	Post[] Posts;

	ProgressDialog myProgressDialog;

	public void A() {
		Thread background = new Thread(new Runnable() {
			@Override
			public void run() {
				Looper.prepare();
				try {
					myProgressDialog = ProgressDialog.show(
							TimeLynrActivity.this, "", "Loading feeds ...",
							true);
				} catch (Throwable t) {
					Log.d("Facebook", "Throw M:" + t.getMessage());
					// Termine le thread en arrière–plan
				}
				Looper.loop();
			}
		});

		background.start();
		SH = new SearchHandler("Mark");
		myProgressDialog.dismiss();

		setContentView(R.layout.results);
		ListView LVPosts;
		int L = SH.getTPosts();
		Posts = new Post[L];
		for (int i = 0; i < L; i++) {
			Posts[i] = SH.getPosts(i);
		}

		PostAdapter adapter = new PostAdapter(TimeLynrActivity.this,
				R.layout.listview_item_row, Posts);
		LVPosts = (ListView) findViewById(R.id.listView1);

		DecimalFormat formatter = new DecimalFormat("###,###,###");

		LVPosts.setAdapter(adapter);

		LVPosts.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				AffOptions(position);
			}
		});
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loadscr);
		// FBLogin();
		A();
	}

	public Bitmap downloadFile(String fileUrl) {
		Bitmap bmImg = null;
		URL myFileUrl = null;
		try {
			myFileUrl = new URL(fileUrl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			HttpURLConnection conn = (HttpURLConnection) myFileUrl
					.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();

			bmImg = BitmapFactory.decodeStream(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bmImg;
	}

	public void ShowAlert(String Title, String Message) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle(Title);
		alertDialog.setMessage(Message);
		// alertDialog.setIcon(R.drawable.search);
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				return;
			}
		});
		alertDialog.show();
	}

	public void AffError() {
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("AndroidFanApp error");
		alertDialog
				.setMessage("There was an error while trying to connect to Facebook."
						+ "Please check your internet connection and try again.");
		// alertDialog.setIcon(R.drawable.search);
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				TimeLynrActivity.this.finish();
				return;
			}
		});
		alertDialog.show();
	}

	public void AffOptions(int position) {
		CharSequence[] items;
		// Status : Comment, Like, Share
		// Photo : Comment, Like, Share, View
		// Video : Comment, Like, Share, Watch video
		// Link : Comment, Like, Share, Open Link

		if (Posts[position].isStatus()){
			items = new CharSequence[3];
		}else
			items = new CharSequence[4];

		items[0] = "Comment";
		items[1] = "Like";
		items[2] = "Share";

		if (Posts[position].isPhoto())
			items[3] = "View photo";
		if (Posts[position].isVideo())
			items[3] = "Watch video";
		if (Posts[position].isLink())
			items[3] = "Open Link";

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// builder.setTitle(FP.getName()+"'s post");
		// builder.setIcon(R.drawable.icon);
		builder.setItems(items, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int item) {
				dialogInterface.dismiss();
			}
		});
		builder.show();
		
	}

}