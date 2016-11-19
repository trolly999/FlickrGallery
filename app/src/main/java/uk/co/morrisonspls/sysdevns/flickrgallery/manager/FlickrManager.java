package uk.co.morrisonspls.sysdevns.flickrgallery.manager;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FlickrManager {

    private static String URL = "https://api.flickr.com/services/feeds/";

    private Retrofit retrofit;

    public FlickrManager() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


}
