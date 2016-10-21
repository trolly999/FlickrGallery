package uk.co.morrisonspls.sysdevns.flickrgallery.modules.detail;

import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import uk.co.morrisonspls.sysdevns.flickrgallery.app.FlickrGalleryApplication;
import uk.co.morrisonspls.sysdevns.flickrgallery.model.JsonFlickrPhoto;

/**
 * Created by sysdevns on 21/10/2016.
 */
public class DetailPresenter extends MvpBasePresenter<DetailView> implements DetailPresenterView {


    private int position;

    @Override
    public void setPhoto(int position) {
        this.position = position;
    }

    @Override
    public void loadPhoto() {
        Context context = getView().getContext();
        FlickrGalleryApplication global = (FlickrGalleryApplication) context.getApplicationContext();
        JsonFlickrPhoto jsonFlickrPhoto = global.getJsonFlickrPhotos().get(position);
        if (isViewAttached()) {
            getView().showPhoto(jsonFlickrPhoto);
        }
    }
}
