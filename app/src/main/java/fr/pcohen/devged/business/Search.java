package fr.pcohen.devged.business;

import java.util.Date;
import java.util.List;

public class Search {

    private String title;
    private String res;
    private Date date ;
    private List<String> tags;
    private String type;

    public Search(String title, String res, Date date, List<String> tags, String type) {
        this.title = title;
        this.res = res;
        this.date = date;
        this.tags = tags;
        this.type = type;
    }

    public Search(){};

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Search{" +
                "title='" + title + '\'' +
                ", res='" + res + '\'' +
                ", date='" + date + '\'' +
                ", tags=" + tags +
                ", type='" + type + '\'' +
                '}';
    }
}
