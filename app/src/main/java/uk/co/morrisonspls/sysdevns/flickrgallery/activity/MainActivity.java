package uk.co.morrisonspls.sysdevns.flickrgallery.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.morrisonspls.sysdevns.flickrgallery.R;
import uk.co.morrisonspls.sysdevns.flickrgallery.adapter.GridViewAdapter;
import uk.co.morrisonspls.sysdevns.flickrgallery.app.FlickrGalleryApplication;
import uk.co.morrisonspls.sysdevns.flickrgallery.model.FlickrApi;
import uk.co.morrisonspls.sysdevns.flickrgallery.model.JsonFlickrFeed;
import uk.co.morrisonspls.sysdevns.flickrgallery.model.JsonFlickrPhoto;

// Use GLIDE to load images
// Use Retrofit to load JSON from feed
// URL "https://api.flickr.com"


public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private final String url = "https://api.flickr.com/services/feeds/";
    private ArrayList<JsonFlickrPhoto> jsonFlickrPhotos;
    FlickrGalleryApplication global;

    // Butterknife associations
    @BindView(R.id.tvError) TextView tvError;
    @BindView(R.id.gridView)  GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        global = (FlickrGalleryApplication) getApplicationContext();


            // Listen for click events
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    launchDetailActivity(position);
                }
            });
        if (global.getJsonFlickrPhotos() == null) {
            getDataFromFlickr();
        } else {
            jsonFlickrPhotos = global.getJsonFlickrPhotos();
            gridView.setAdapter(new GridViewAdapter(MainActivity.this, jsonFlickrPhotos));
        }
    }

    private void launchDetailActivity(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("flickrPhotoPosition", position);
        startActivity(intent);
    }


    private void getDataFromFlickr() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        FlickrApi service = retrofit.create(FlickrApi.class);
        Call<JsonFlickrFeed> call = service.getPhotoFeed();
        call.enqueue(new Callback<JsonFlickrFeed>() {
            @Override
            public void onResponse(Call<JsonFlickrFeed> call, Response<JsonFlickrFeed> response) {
                Toast.makeText(MainActivity.this, R.string.success,Toast.LENGTH_LONG).show();
                if (response.isSuccessful()) {
                    jsonFlickrPhotos = response.body().getItems();
                    global.setJsonFlickrPhotos(jsonFlickrPhotos);
                    gridView.setAdapter(new GridViewAdapter(MainActivity.this, jsonFlickrPhotos));                }
                else
                    tvError.setText(R.string.onBadResponse);
            }

            @Override
            public void onFailure(Call<JsonFlickrFeed> call, Throwable t) {
                gridView.setVisibility(View.GONE);
                tvError.setText(R.string.onFailure);
            }
        });
    }
}
