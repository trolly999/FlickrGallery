package uk.co.morrisonspls.sysdevns.flickrgallery.modules.detail;

import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpView;

import uk.co.morrisonspls.sysdevns.flickrgallery.model.JsonFlickrPhoto;

/**
 * Created by sysdevns on 21/10/2016.
 */
public interface DetailView extends MvpView {

    void showPhoto(JsonFlickrPhoto jsonFlickrPhoto);

    void showError(String errorMessage);


    // Returns the view context to the presenter - not sure if this is good practise
    Context getContext();


}
