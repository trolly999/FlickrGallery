package uk.co.morrisonspls.sysdevns.flickrgallery.modules.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.morrisonspls.sysdevns.flickrgallery.R;
import uk.co.morrisonspls.sysdevns.flickrgallery.app.FlickrGalleryApplication;
import uk.co.morrisonspls.sysdevns.flickrgallery.model.JsonFlickrPhoto;
import uk.co.morrisonspls.sysdevns.flickrgallery.modules.main.MainPresenter;
import uk.co.morrisonspls.sysdevns.flickrgallery.modules.main.MainView;

public class DetailActivity extends MvpActivity<DetailView, DetailPresenter> implements DetailView {

    private JsonFlickrPhoto jsonFlickrPhoto;

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

        setRetainInstance(true);

        Intent intent = getIntent();
        int position = intent.getIntExtra("flickrPhotoPosition",-1);
        presenter.setPhoto(position);
        presenter.loadPhoto();
    }

    @Override
    public void showPhoto(JsonFlickrPhoto jsonFlickrPhoto) {
        Glide.with(this).load(jsonFlickrPhoto.getMedia().getM()).centerCrop().into(ivDetailImage);
        tvTitle.setText(jsonFlickrPhoto.getTitle());
        tvTimestamp.setText(jsonFlickrPhoto.getDate_taken());
        tvDescription.setText(jsonFlickrPhoto.getDescription());
        tvAuthor.setText(jsonFlickrPhoto.getAuthor());
        tvTags.setText(jsonFlickrPhoto.getTags());
    }

    @Override
    public void showError(String errorMessage) {
        tvTitle.setText(errorMessage);
    }

    @Override
    public Context getContext() {
     return getApplicationContext();
    }


    @NonNull
    @Override
    public DetailPresenter createPresenter() {
        return new DetailPresenter();
    }

}
