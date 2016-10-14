package uk.co.morrisonspls.sysdevns.flickrgallery.app;

import android.app.Application;

import java.util.ArrayList;

import uk.co.morrisonspls.sysdevns.flickrgallery.model.JsonFlickrPhoto;

/**
 * Created by sysdevns on 11/10/2016.
 */

public class FlickrGalleryApplication extends Application {

    // global variables used throughout app
    private ArrayList<JsonFlickrPhoto> jsonFlickrPhotos;

    public ArrayList<JsonFlickrPhoto> getJsonFlickrPhotos() {
        return jsonFlickrPhotos;
    }

    public void setJsonFlickrPhotos(ArrayList<JsonFlickrPhoto> jsonFlickrPhotos) {
        this.jsonFlickrPhotos = jsonFlickrPhotos;
    }
}
