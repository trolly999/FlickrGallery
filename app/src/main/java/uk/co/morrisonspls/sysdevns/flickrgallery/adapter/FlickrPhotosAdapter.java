package uk.co.morrisonspls.sysdevns.flickrgallery.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import uk.co.morrisonspls.sysdevns.flickrgallery.pojo.JsonFlickrPhoto;


public class FlickrPhotosAdapter extends ArrayAdapter<JsonFlickrPhoto> {

    public FlickrPhotosAdapter(Context context, ArrayList<JsonFlickrPhoto> jsonFlickrPhotos) {
        super(context, 0, jsonFlickrPhotos);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(this.getContext());
            imageView.setAdjustViewBounds(true);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(parent.getWidth() / 3, parent.getWidth() / 3));
        } else {
            imageView = (ImageView) convertView;
        }

        JsonFlickrPhoto jsonFlickrPhoto = getItem(position);
        Glide.with(getContext()).load(jsonFlickrPhoto.getMedia().getM()).centerCrop().into(imageView);

        return imageView;
    }
}
