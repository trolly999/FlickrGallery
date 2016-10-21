package uk.co.morrisonspls.sysdevns.flickrgallery.modules.main;

// Use GLIDE to load images
// Use Retrofit to load JSON from feed
// URL "https://api.flickr.com"


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.morrisonspls.sysdevns.flickrgallery.R;
import uk.co.morrisonspls.sysdevns.flickrgallery.adapter.FlickrPhotosAdapter;
import uk.co.morrisonspls.sysdevns.flickrgallery.model.JsonFlickrPhoto;

public class MainActivity extends MvpActivity<MainView, MainPresenter> implements MainView
{
    // Butterknife associations
    @BindView(R.id.gridView) GridView gridView;
    @BindView(R.id.errorView) TextView errorMsg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setRetainInstance(true);

        presenter.loadPhotos();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.photoClicked(position);
            }
        });


    }



    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void setData(ArrayList<JsonFlickrPhoto> jsonFlickrPhotos) {
        gridView.setAdapter(new FlickrPhotosAdapter(this, jsonFlickrPhotos));
    }

    @Override
    public void showError(String errorMsg) {
        this.errorMsg.setText(errorMsg);
    }

    @Override
    public Context getContext() {
        return MainActivity.this;
    }



    /*
    // Invoked when photo clicked, launches photo detail activity
    private void launchDetailActivity(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("flickrPhotoPosition", position);
        startActivity(intent);
    }
*/
}
