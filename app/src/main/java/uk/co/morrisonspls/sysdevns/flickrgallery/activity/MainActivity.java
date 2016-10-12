package uk.co.morrisonspls.sysdevns.flickrgallery.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.morrisonspls.sysdevns.flickrgallery.R;
import uk.co.morrisonspls.sysdevns.flickrgallery.model.FlickrApi;
import uk.co.morrisonspls.sysdevns.flickrgallery.model.JsonFlickrFeed;
import uk.co.morrisonspls.sysdevns.flickrgallery.model.JsonFlickrPhoto;

// Use GLIDE to load images
// Use Retrofit to load JSON from feed
// URL "https://api.flickr.com"


public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private final String url = "https://api.flickr.com/services/feeds/";

    // Butterknife associations
    @BindView(R.id.textView1) TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //getRetrofitArray();
        getRetrofitObject();
    }


    private void getRetrofitObject() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create(gson)).build();
        FlickrApi service = retrofit.create(FlickrApi.class);
        Call<JsonFlickrFeed> call = service.getPhotoFeed();
        call.enqueue(new Callback<JsonFlickrFeed>() {
            @Override
            public void onResponse(Call<JsonFlickrFeed> call, Response<JsonFlickrFeed> response) {
                Toast.makeText(MainActivity.this,"success",Toast.LENGTH_LONG).show();
                if (response.isSuccessful())
                    textView1.setText(response.body().getModified());
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


    private void getRetrofitArray() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        FlickrApi service = retrofit.create(FlickrApi.class);
        Call<List<JsonFlickrPhoto>> call = service.getPhotos();

        call.enqueue(new Callback<List<JsonFlickrPhoto>>() {
            @Override
            public void onResponse(Call<List<JsonFlickrPhoto>> call, Response<List<JsonFlickrPhoto>> response) {

                List<JsonFlickrPhoto> flickrPhotos = response.body();
                Toast.makeText(MainActivity.this,"success",Toast.LENGTH_LONG).show();
                textView1.setText(flickrPhotos.size());
            }

            @Override
            public void onFailure(Call<List<JsonFlickrPhoto>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"failed",Toast.LENGTH_LONG).show();
            }
        });

    }

}
