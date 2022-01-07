package at.ac.fhcampuswien.craw.lib.exceptions;

/**
 * Wraps the {@link Exception} class to differentiate Exceptions raised by the craw application due to errors from other thrown by dependencies.
 */
public class CrawException extends Exception {
    public CrawException(String errorMessage) {
        super(errorMessage);
    }

    public CrawException(String errorMessage, Exception e) {
        super(errorMessage, e);
    }
}
