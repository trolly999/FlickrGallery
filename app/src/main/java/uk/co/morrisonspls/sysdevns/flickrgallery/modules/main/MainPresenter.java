package uk.co.morrisonspls.sysdevns.flickrgallery.modules.main;

import android.content.Context;
import android.content.Intent;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;

import uk.co.morrisonspls.sysdevns.flickrgallery.app.FlickrGalleryApplication;
import uk.co.morrisonspls.sysdevns.flickrgallery.model.FlickrPhotoLoader;
import uk.co.morrisonspls.sysdevns.flickrgallery.model.JsonFlickrPhoto;
import uk.co.morrisonspls.sysdevns.flickrgallery.modules.detail.DetailActivity;


public class MainPresenter extends MvpBasePresenter<MainView> implements MainPresenterView {

    ArrayList<JsonFlickrPhoto> jsonFlickrPhotos;
//    FlickrGalleryApplication global = (FlickrGalleryApplication) getApplicationContext();

    @Override
    public void getMorePhotos() {
        jsonFlickrPhotos = null;
        loadPhotos();
    }

    @Override
    public void photoClicked(int position) {
        if (isViewAttached()) {
            Context context = getView().getContext();
            FlickrGalleryApplication global = (FlickrGalleryApplication) context.getApplicationContext();
            global.setJsonFlickrPhotos(jsonFlickrPhotos);
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("flickrPhotoPosition", position);
            context.startActivity(intent);
        }
    }

    @Override
    public void loadPhotos() {
        if (jsonFlickrPhotos == null) {
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
            if (isViewAttached()) {
                getView().setData(jsonFlickrPhotos);
            }
        }
    }
}
