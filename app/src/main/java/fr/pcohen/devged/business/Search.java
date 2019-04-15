package fr.pcohen.devged.business;

import java.util.List;

public class Search {

    private String title;
    private String ressource;
    private String date ;
    private List<String> tags;

    public Search(String title, String ressource, String date, List<String> tags) {
        this.title = title;
        this.ressource = ressource;
        this.date = date;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRessource() {
        return ressource;
    }

    public void setRessource(String ressource) {
        this.ressource = ressource;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Search[" +
                "title='" + title + '\'' +
                ", ressource='" + ressource + '\'' +
                ", date='" + date + '\'' +
                ", tags=" + tags +
                ']';
    }
}
