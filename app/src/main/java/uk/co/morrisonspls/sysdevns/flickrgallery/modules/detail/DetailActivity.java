package uk.co.morrisonspls.sysdevns.flickrgallery.modules.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.morrisonspls.sysdevns.flickrgallery.R;
import uk.co.morrisonspls.sysdevns.flickrgallery.model.JsonFlickrPhoto;

public class DetailActivity extends MvpActivity<DetailView, DetailPresenter> implements DetailView, GestureDetector.OnGestureListener {

    private static final String TAG = "DetailActivity";

    private final int SWIPE_DISTANCE = 200;
    private GestureDetector gestureDetector;

    // Butterknife associations
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvTimestamp)
    TextView tvTimestamp;
    @BindView(R.id.tvDescription)
    TextView tvDescription;
    @BindView(R.id.tvAuthor)
    TextView tvAuthor;
    @BindView(R.id.tvTags)
    TextView tvTags;
    @BindView(R.id.ivDetailImage)
    ImageView ivDetailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        gestureDetector = new GestureDetector(this, this);

        setRetainInstance(true);

        Intent intent = getIntent();
        int position = intent.getIntExtra("flickrPhotoPosition", -1);
        presenter.setPhoto(position);
        presenter.loadPhoto();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
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
        Log.d(TAG, "Creating a new presenter");
        return new DetailPresenter();
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
        if (e1.getX() - e2.getX() > SWIPE_DISTANCE)
            presenter.getNext();
        else if (e2.getX() - e1.getX() > SWIPE_DISTANCE)
            presenter.getPrev();
        else
            return false;
        return true;
    }

}
