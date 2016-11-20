package uk.co.morrisonspls.sysdevns.flickrgallery.manager;

import android.util.Log;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import uk.co.morrisonspls.sysdevns.flickrgallery.model.FlickrApi;
import uk.co.morrisonspls.sysdevns.flickrgallery.pojo.JsonFlickrFeed;
import uk.co.morrisonspls.sysdevns.flickrgallery.pojo.JsonFlickrPhoto;

public class FlickrManager {

    private static final String TAG = "FlickrManager";

    private static String URL = "https://api.flickr.com/services/feeds/";

    private Retrofit retrofit;
    private FlickrManagerCallback callback;

    public FlickrManager(FlickrManagerCallback callback) {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        this.callback = callback;
    }

    public void doCall() {
        FlickrApi api = retrofit.create(FlickrApi.class);
        Observable<JsonFlickrFeed> jsonFlickrFeedObservable = api.getPhotoFeedRx();

        jsonFlickrFeedObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<JsonFlickrFeed, List<JsonFlickrPhoto>>() {
                    @Override
                    public List<JsonFlickrPhoto> call(JsonFlickrFeed jsonFlickrFeed) {
                        return jsonFlickrFeed.getItems();
                    }
                })
                .flatMap(new Func1<List<JsonFlickrPhoto>, Observable<JsonFlickrPhoto>>() {
                    @Override
                    public Observable<JsonFlickrPhoto> call(List<JsonFlickrPhoto> jsonFlickrPhotos) {
                        return Observable.from(jsonFlickrPhotos);
                    }
                })
                .toList()
                .subscribe(new Observer<List<JsonFlickrPhoto>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                        callback.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<JsonFlickrPhoto> jsonFlickrPhotos) {
                        Log.d(TAG, "onNext: List size is " + jsonFlickrPhotos.size());
                        callback.onSuccess(jsonFlickrPhotos);
                    }

                });

        Log.d(TAG, "Finished");
    }

    public interface FlickrManagerCallback {
        void onSuccess(List<JsonFlickrPhoto> jsonFlickrPhotos);

        void onError(String errorMsg);
    }

}
