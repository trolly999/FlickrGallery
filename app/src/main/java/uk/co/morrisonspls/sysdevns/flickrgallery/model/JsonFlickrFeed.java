package uk.co.morrisonspls.sysdevns.flickrgallery.model;

import org.json.JSONArray;

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
    //private JSONArray items;
    private List<JsonFlickrPhoto> items;

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

    public List<JsonFlickrPhoto> getItems() {
        return items;
    }
}
