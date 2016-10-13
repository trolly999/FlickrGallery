package uk.co.morrisonspls.sysdevns.flickrgallery.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sysdevns on 12/10/2016.
 */

public class JsonFlickrFeed {

    private String title;
    private String link;
    private String description;
    private String modified;
    private String generator;
    private ArrayList<JsonFlickrPhoto> items;

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getModified() {
        return modified;
    }

    public String getGenerator() {
        return generator;
    }

    public ArrayList<JsonFlickrPhoto> getItems() {
        return items;
    }
}
