package at.ac.fhcampuswien.craw.lib.model;

public class Weblink {
    private String URL;
    private String name;

    // TODO Getter + Setters

    public Weblink(String URL, String name) {
        this.URL = URL;
        this.name = name;
    }

    public Weblink() {
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getURL() {
        return URL;
    }

    public String getName() {
        return name;
    }
}
