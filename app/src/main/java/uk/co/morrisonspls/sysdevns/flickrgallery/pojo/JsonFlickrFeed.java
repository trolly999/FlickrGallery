package uk.co.morrisonspls.sysdevns.flickrgallery.pojo;

import java.util.List;

/**
 * Created by sysdevns on 12/10/2016.
 */

public class JsonFlickrFeed {

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

    private String title;
    private String link;
    private String description;
    private String modified;
    private String generator;
    private List<JsonFlickrPhoto> items;

    public List<JsonFlickrPhoto> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "JsonFlickrFeed{" +
                "generator='" + generator + '\'' +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", modified='" + modified + '\'' +
                '}';
    }
}
