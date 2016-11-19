package uk.co.morrisonspls.sysdevns.flickrgallery.modules.main;

// Use GLIDE to load images
// Use Retrofit to load JSON from feed
// URL "https://api.flickr.com"


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
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
import uk.co.morrisonspls.sysdevns.flickrgallery.modules.detail.DetailActivity;
import uk.co.morrisonspls.sysdevns.flickrgallery.pojo.JsonFlickrPhoto;

public class MainActivity extends MvpActivity<MainView, MainPresenter> implements MainView, GestureDetector.OnGestureListener {

    private static final String TAG = "MainActivity";

    private final int SWIPE_DISTANCE = 200;
    private GestureDetector gestureDetector;

    // Butterknife associations
    @BindView(R.id.gridView)
    GridView gridView;
    @BindView(R.id.errorView)
    TextView errorMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setRetainInstance(true);

        Log.d(TAG,"Creating activity");

        presenter.loadPhotos();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.photoClicked(position);
            }
        });


        gestureDetector = new GestureDetector(this, this);

        gridView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return false;
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
        this.errorMsg.setVisibility(View.VISIBLE);
    }

    @Override
    public void launchDetail(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("flickrPhotoPosition", position);
        startActivity(intent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        //Toast.makeText(this, "X1=" + e1.getX() + "    X2=" + e2.getX(), Toast.LENGTH_LONG).show();
        if (e1.getX() - e2.getX() > SWIPE_DISTANCE)
            presenter.getMorePhotos();
        else
            return false;
        return true;
    }
}
