package at.ac.fhcampuswien.craw.lib.model;

public class Weblink {
    private String url;
    private String name;

    public Weblink(String URL, String name) {
        this.url = URL;
        this.name = name;
    }

    public void setURL(String URL) {
        this.url = URL;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getURL() {
        return url;
    }

    public String getName() {
        return name;
    }
}
