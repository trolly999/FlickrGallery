package uk.co.morrisonspls.sysdevns.flickrgallery.model;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;
import uk.co.morrisonspls.sysdevns.flickrgallery.pojo.JsonFlickrFeed;

/**
 * Created by sysdevns on 12/10/2016.
 */

public interface FlickrApi {

    @GET("photos_public.gne?format=json&nojsoncallback=1")
    Call<JsonFlickrFeed> getPhotoFeed();

    @GET("photos_public.gne?format=json&nojsoncallback=1")
    Observable<JsonFlickrFeed> getPhotoFeedRx();

}
