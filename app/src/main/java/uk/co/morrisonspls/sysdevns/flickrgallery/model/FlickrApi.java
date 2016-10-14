package uk.co.morrisonspls.sysdevns.flickrgallery.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sysdevns on 12/10/2016.
 */

public interface FlickrApi {

    @GET("photos_public.gne?format=json&nojsoncallback=1")
    Call<JsonFlickrFeed> getPhotoFeed();

}
