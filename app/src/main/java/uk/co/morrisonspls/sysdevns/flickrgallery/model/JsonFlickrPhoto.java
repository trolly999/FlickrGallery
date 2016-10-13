package uk.co.morrisonspls.sysdevns.flickrgallery.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sysdevns on 12/10/2016.
 */
public class JsonFlickrPhoto {
    private String title;
    private String link;
    private Media media;
    private String date_taken;
    private String description;
    private String published;
    private String author;
    private String author_id;
    private String tags;

    public JsonFlickrPhoto(String[] flickrPhotoDetails) {
        this.title = flickrPhotoDetails[0];
        this.media = new Media(flickrPhotoDetails[1]);
        this.date_taken = flickrPhotoDetails[2];
        this.description = flickrPhotoDetails[3];
        this.author = flickrPhotoDetails[4];
        this.tags = flickrPhotoDetails[5];
    }

    public String getTitle() {
        return title;
    }

    public Media getMedia() {
        return media;
    }

    public String getDate_taken() {
        return date_taken;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getTags() {
        return tags;
    }

}

