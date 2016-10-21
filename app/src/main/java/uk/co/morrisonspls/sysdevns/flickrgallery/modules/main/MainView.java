package uk.co.morrisonspls.sysdevns.flickrgallery.modules.main;

import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.ArrayList;
import java.util.List;

import uk.co.morrisonspls.sysdevns.flickrgallery.model.JsonFlickrPhoto;

public interface MainView extends MvpView {


    // Called when load data succeeds
    void setData(ArrayList<JsonFlickrPhoto> jsonFlickrPhotos);

    // Called if load data fails
    void showError(String errorMsg);

    // Returns the view context to the presenter - not sure if this is good practise
    Context getContext();


}
