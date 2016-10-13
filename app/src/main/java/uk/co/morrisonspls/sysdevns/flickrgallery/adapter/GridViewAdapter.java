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

    private static class ViewHolder {
        public final ImageView imageView;
        public ViewHolder(ImageView imageView) {
            this.imageView = imageView;
        }
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView;
        if (convertView == null) {
            //imageView = LayoutInflater.from(getContext()).inflate(R.layout.gridview_item, parent, false);
             //imageView = (ImageView) convertView.findViewById(R.id.gridviewImage);
            imageView = new ImageView(this.getContext());
            //imageView.setLayoutParams(new GridView.LayoutParams(85d,85d));
            //imageView.setAdjustViewBounds(true);
            //imageView.setMinimumHeight(100);
            imageView.setAdjustViewBounds(true);
            //imageView.setPadding(3,3,3,3);

            imageView.setLayoutParams(new ViewGroup.LayoutParams(parent.getWidth()/3,parent.getWidth()/3));
            //convertView.setTag(new ViewHolder(imageView));
        } else {
            //ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            //imageView = viewHolder.imageView;
            imageView = (ImageView) convertView;
        }

        JsonFlickrPhoto jsonFlickrPhoto = getItem(position);
        Glide.with(getContext()).load(jsonFlickrPhoto.getMedia().getM()).centerCrop().into(imageView);

        return imageView;
    }
}
