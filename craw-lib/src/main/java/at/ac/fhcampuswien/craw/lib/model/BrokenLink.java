package at.ac.fhcampuswien.craw.lib.model;

public class BrokenLink extends Weblink {


    private final States linkStatus;

    /**
     * Represents the different error states of a link
     * For filtering purposes later on.
     **/
    public enum States {

        /**
         * MALFORMED is when the link is not valid and could not be parsed
         **/
        MALFORMED,

        /**
         * CONNECTION_ERROR is when there was an error writing/reading from or connecting to the socket
         */
        CONNECTION_ERROR,

        /**
         * NOT_OK is when the HTTP response code != 200
         */
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
