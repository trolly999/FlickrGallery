package uk.co.morrisonspls.sysdevns.flickrgallery.pojo;

import java.util.ArrayList;

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

    public ArrayList<JsonFlickrPhoto> getItems() {
        return items;
    }
}
