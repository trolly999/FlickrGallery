package uk.co.morrisonspls.sysdevns.flickrgallery.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.morrisonspls.sysdevns.flickrgallery.R;
import uk.co.morrisonspls.sysdevns.flickrgallery.app.FlickrGalleryApplication;
import uk.co.morrisonspls.sysdevns.flickrgallery.model.JsonFlickrPhoto;

public class DetailActivity extends AppCompatActivity {

    private ArrayList<JsonFlickrPhoto> jsonFlickrPhotos;

    // Butterknife associations
    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.tvTimestamp) TextView tvTimestamp;
    @BindView(R.id.tvDescription) TextView tvDescription;
    @BindView(R.id.tvAuthor) TextView tvAuthor;
    @BindView(R.id.tvTags) TextView tvTags;
    @BindView(R.id.ivDetailImage) ImageView ivDetailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        FlickrGalleryApplication global = (FlickrGalleryApplication) getApplicationContext();
        jsonFlickrPhotos = global.getJsonFlickrPhotos();

        Intent intent = getIntent();
        int postion = intent.getIntExtra("flickrPhotoPosition",-1);
        Glide.with(this).load(jsonFlickrPhotos.get(postion).getMedia().getM()).centerCrop().into(ivDetailImage);
        tvTitle.setText(jsonFlickrPhotos.get(postion).getTitle());
        tvTimestamp.setText(jsonFlickrPhotos.get(postion).getDate_taken());
        tvDescription.setText(jsonFlickrPhotos.get(postion).getDescription());
        tvAuthor.setText(jsonFlickrPhotos.get(postion).getAuthor());
        tvTags.setText(jsonFlickrPhotos.get(postion).getTags());
    }

}
