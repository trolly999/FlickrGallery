package uk.co.morrisonspls.sysdevns.flickrgallery.modules.main;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

import uk.co.morrisonspls.sysdevns.flickrgallery.pojo.JsonFlickrPhoto;

public interface MainView extends MvpView {


    // Called when load data succeeds
    void setData(List<JsonFlickrPhoto> jsonFlickrPhotos);

    // Called if load data fails
    void showError(String errorMsg);

    // Returns the view context to the presenter - not sure if this is good practise
    void launchDetail(int position);




}
