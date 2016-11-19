package uk.co.morrisonspls.sysdevns.flickrgallery.modules.detail;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import uk.co.morrisonspls.sysdevns.flickrgallery.pojo.JsonFlickrPhoto;

/**
 * Created by sysdevns on 21/10/2016.
 */
public class DetailPresenter extends MvpBasePresenter<DetailView> implements DetailPresenterView {


    private int position = -1;
    private ArrayList<JsonFlickrPhoto> jsonFlickrPhotos;

    @Override
    public void setPhoto(int position) {
        if (this.position < 0)
            this.position = position;
    }

    @Override
    public void loadPhoto() {
        if (jsonFlickrPhotos == null) {
            jsonFlickrPhotos = EventBus.getDefault().removeStickyEvent(ArrayList.class);
            EventBus.getDefault().removeStickyEvent(ArrayList.class);
        }
        if (isViewAttached()) {
            getView().showPhoto(jsonFlickrPhotos.get(position));
        }
    }

    @Override
    public void getNext() {
        if (position < jsonFlickrPhotos.size() - 1) {
            position++;
        }
        loadPhoto();
    }

    @Override
    public void getPrev() {
        if (position > 0) {
            position--;
        }
        loadPhoto();
    }
}
