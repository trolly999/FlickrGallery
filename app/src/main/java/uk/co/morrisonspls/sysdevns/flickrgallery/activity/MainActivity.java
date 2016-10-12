package uk.co.morrisonspls.sysdevns.flickrgallery.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.ImageView;
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
import uk.co.morrisonspls.sysdevns.flickrgallery.adapter.ScrollViewAdapter;
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

    // Butterknife associations
    @BindView(R.id.textView1) TextView textView1;
    @BindView(R.id.imageView) ImageView imageView;
    @BindView(R.id.gridView)  GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getDataFromFlickr();
    }


    private void getDataFromFlickr() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        FlickrApi service = retrofit.create(FlickrApi.class);
        Call<JsonFlickrFeed> call = service.getPhotoFeed();
        call.enqueue(new Callback<JsonFlickrFeed>() {
            @Override
            public void onResponse(Call<JsonFlickrFeed> call, Response<JsonFlickrFeed> response) {
                Toast.makeText(MainActivity.this,"success",Toast.LENGTH_LONG).show();
                if (response.isSuccessful()) {
                    jsonFlickrPhotos = response.body().getItems();
                    gridView.setAdapter(new ScrollViewAdapter(MainActivity.this, jsonFlickrPhotos));
                    //textView1.setText(jsonFlickrPhotos.get(0).getLink());
                    //Glide.with(MainActivity.this).load(jsonFlickrPhotos.get(0).getMedia().getM()).centerCrop().into(imageView);
                }
                else
                    textView1.setText("response is failure");
            }

            @Override
            public void onFailure(Call<JsonFlickrFeed> call, Throwable t) {
                Toast.makeText(MainActivity.this,"failure",Toast.LENGTH_LONG).show();
                textView1.setText("onFailure");
            }
        });
    }
}
