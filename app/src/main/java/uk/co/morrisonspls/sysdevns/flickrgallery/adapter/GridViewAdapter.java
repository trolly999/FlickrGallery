package uk.co.morrisonspls.sysdevns.flickrgallery.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import uk.co.morrisonspls.sysdevns.flickrgallery.R;
import uk.co.morrisonspls.sysdevns.flickrgallery.model.JsonFlickrPhoto;

/**
 * Created by sysdevns on 12/10/2016.
 */

public class GridViewAdapter extends ArrayAdapter<JsonFlickrPhoto> {

    public GridViewAdapter(Context context, ArrayList<JsonFlickrPhoto> jsonFlickrPhotos) {
        super(context,0,jsonFlickrPhotos);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(this.getContext());
            imageView.setAdjustViewBounds(true);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(parent.getWidth()/3,parent.getWidth()/3));
        } else {
            imageView = (ImageView) convertView;
        }

        JsonFlickrPhoto jsonFlickrPhoto = getItem(position);
        Glide.with(getContext()).load(jsonFlickrPhoto.getMedia().getM()).centerCrop().into(imageView);

        return imageView;
    }
}
