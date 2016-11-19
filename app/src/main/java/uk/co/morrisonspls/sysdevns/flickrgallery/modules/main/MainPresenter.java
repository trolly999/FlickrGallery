package uk.co.morrisonspls.sysdevns.flickrgallery.modules.main;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import uk.co.morrisonspls.sysdevns.flickrgallery.model.FlickrPhotoLoader;
import uk.co.morrisonspls.sysdevns.flickrgallery.model.JsonFlickrPhoto;


public class MainPresenter extends MvpBasePresenter<MainView> implements MainPresenterView {

    private static final String TAG = "MainPresenter";

    ArrayList<JsonFlickrPhoto> jsonFlickrPhotos;

    @Override
    public void getMorePhotos() {
        jsonFlickrPhotos = null;
        loadPhotos();
    }

    @Override
    public void photoClicked(int position) {
        if (isViewAttached() && jsonFlickrPhotos != null) {
            EventBus.getDefault().postSticky(jsonFlickrPhotos);
            getView().launchDetail(position);
        }
    }

    @Override
    public void loadPhotos() {
        if (jsonFlickrPhotos == null) {
            Log.d(TAG,"Calling API to get photos");
            FlickrPhotoLoader flickrPhotoLoader = new FlickrPhotoLoader(new FlickrPhotoLoader.FlickrPhotoListener() {
                @Override
                public void onSuccess(ArrayList<JsonFlickrPhoto> jsonFlickrPhotos) {
                    MainPresenter.this.jsonFlickrPhotos = jsonFlickrPhotos;
                    if (isViewAttached()) {
                        getView().setData(jsonFlickrPhotos);
                        //getView().showContent();
                    }
                }

                @Override
                public void onError(String errorMsg) {
                    if (isViewAttached()) {
                        getView().showError(errorMsg);
                    }
                }
            });
            flickrPhotoLoader.execute();
        } else {
            Log.d(TAG,"Resusing photos");
            if (isViewAttached()) {
                Log.d(TAG,"view is attached");
                getView().setData(jsonFlickrPhotos);
            }
        }
    }
}
