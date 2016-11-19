package uk.co.morrisonspls.sysdevns.flickrgallery.model;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.morrisonspls.sysdevns.flickrgallery.pojo.JsonFlickrFeed;
import uk.co.morrisonspls.sysdevns.flickrgallery.pojo.JsonFlickrPhoto;

/**
 * Created by sysdevns on 20/10/2016.
 */

public class FlickrPhotoLoader {

    private final String url = "https://api.flickr.com/services/feeds/";

    public interface FlickrPhotoListener {
        void onSuccess(ArrayList<JsonFlickrPhoto> jsonFlickrPhotos);
        void onError(String errorMsg);
    }

    private FlickrPhotoListener listener;
    private FlickrApi service;
    private Retrofit retrofit;
    private ArrayList<JsonFlickrPhoto> jsonFlickrPhotos;

    public FlickrPhotoLoader(FlickrPhotoListener listener) {
        this.listener = listener;
        this.retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        this.service = retrofit.create(FlickrApi.class);
    }

    // Pulls JSON data from Flickr feed, and populates arraylist
    public void execute() {
        Call<JsonFlickrFeed> call = service.getPhotoFeed();
        call.enqueue(new Callback<JsonFlickrFeed>() {
            @Override
            public void onResponse(Call<JsonFlickrFeed> call, Response<JsonFlickrFeed> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body().getItems());
                } else {
                    listener.onError("Flickr site returned a bad response");
                }
            }

            @Override
            public void onFailure(Call<JsonFlickrFeed> call, Throwable t) {
                listener.onError("Unable to connect to Flickr website");
            }
        });
    }
}
