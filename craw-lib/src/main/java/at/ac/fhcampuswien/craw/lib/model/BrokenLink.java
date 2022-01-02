package at.ac.fhcampuswien.craw.lib.model;

public class BrokenLink extends Weblink {

    // IntelliJ suggested to make this final. Rethink if it makes sense. It also makes sense not to be static.
    private final States linkStatus;

    /*
        For filtering purposes later on.
        MALFORMED is when the link is not valid and could not be parsed
        CONNECTION_ERROR is when there was an error writing/reading from or connecting to the socket
        NOT_OK is when the HTTP response code != 200
    */
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
