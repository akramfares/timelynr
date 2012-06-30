package com.ayoubuto.facendroid;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class PostAdapter extends ArrayAdapter<Post> {

	Context context;
	int layoutResourceId;
	Bitmap ImgIcon;
	Post data[] = null;

	public PostAdapter(Context context, int layoutResourceId, Post[] data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
		this.ImgIcon = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.fbicon);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		PostHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new PostHolder();
			holder.imgIcon = (ImageView) row.findViewById(R.id.imgIcon);
			holder.ProfileImg = (ImageView) row.findViewById(R.id.ProfileImg);
			holder.txtFrom = (TextView) row.findViewById(R.id.txtFrom);
			holder.txtMessage = (TextView) row.findViewById(R.id.txtMessage);
			holder.txtDescription = (TextView) row
					.findViewById(R.id.txtDescription);
			holder.txtName = (TextView) row.findViewById(R.id.txtName);
			holder.txtCreated = (TextView) row.findViewById(R.id.txtCreated);

			holder.IconType = (ImageView) row.findViewById(R.id.IconType);
			holder.FrameImg = (FrameLayout) row.findViewById(R.id.frameLayout1);

			row.setTag(holder);
		} else {
			holder = (PostHolder) row.getTag();
		}

		Post sPost = data[position];
		holder.txtFrom.setText(sPost.getFromUser().getName());
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		holder.IconType.setImageBitmap(sPost.getIcon());
		Calendar CalFB = Calendar.getInstance();
		CalFB.setTime(sPost.getCreatedTime());
		CalFB.setTimeZone(TimeZone.getTimeZone("GMT"));

		Calendar CalNow = Calendar.getInstance();
		CalNow.setTime(new Date());
		CalNow.setTimeZone(TimeZone.getTimeZone("GMT"));

		holder.txtCreated.setText(DateUtils.getRelativeTimeSpanString(
				CalFB.getTimeInMillis(), CalNow.getTimeInMillis(), 0));

		if (sPost.getMessage().equals("")) {
			holder.txtMessage.setVisibility(View.GONE);
			// holder.txtMessage.setHeight(0);
		} else {
			holder.txtMessage.setVisibility(View.VISIBLE);
			holder.txtMessage.setText(sPost.getMessage());
		}

		if (sPost.getDescription().equals("")) {
			holder.txtDescription.setVisibility(View.GONE);
			// holder.txtDescription.setHeight(0);
		} else {
			holder.txtDescription.setVisibility(View.VISIBLE);
			holder.txtDescription.setText(sPost.getDescription());
		}

		if (sPost.getName().equals("")) {
			holder.txtName.setVisibility(View.GONE);
			// holder.txtName.setHeight(0);
		} else {
			holder.txtName.setVisibility(View.VISIBLE);
			holder.txtName.setText(sPost.getName());
		}

		if (sPost.getPicture() == null)
			// holder.FrameImg.setVisibility();
			holder.imgIcon.setImageBitmap(ImgIcon);
		else {
			// holder.FrameImg.setVisibility(View.VISIBLE);
			holder.imgIcon.setImageBitmap(sPost.getPicture());
		}
		
		if (sPost.getFromUser().getPicture() == null)
			// holder.FrameImg.setVisibility();
			holder.ProfileImg.setImageBitmap(ImgIcon);
		else {
			// holder.FrameImg.setVisibility(View.VISIBLE);
			holder.ProfileImg.setImageBitmap(sPost.getFromUser().getPicture());
		}
		
		return row;
	}

	static class PostHolder {
		ImageView imgIcon;
		ImageView ProfileImg;
		TextView txtMessage;
		TextView txtDescription;
		TextView txtName;
		TextView txtFrom;
		TextView txtCreated;
		ImageView IconType;
		FrameLayout FrameImg;
	}
}