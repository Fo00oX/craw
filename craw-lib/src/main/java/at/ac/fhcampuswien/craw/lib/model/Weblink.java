package at.ac.fhcampuswien.craw.lib.model;

public class Weblink {
    private String url;
    private String name;

    public Weblink(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String prettifiedString() {
        return String.format("%s: %s", getName(), getUrl());
    }
}
