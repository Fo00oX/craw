package at.ac.fhcampuswien.craw.lib.model;

import at.ac.fhcampuswien.craw.lib.model.Weblink;

public class BrokenLink extends Weblink {


    private States linkStatus;

    // For filtering purposes later on.
    public enum States {
        MALFORMED,
        CONNECTION_ERROR,
        NOT_OK
    }


    public BrokenLink(String URL, String name, States linkStatus) {
        super(URL, name);
        this.linkStatus = linkStatus;
    }

    public States getLinkStatus() {
        return linkStatus;
    }
}
