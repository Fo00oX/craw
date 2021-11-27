package at.ac.fhcampuswien.craw.lib.exceptions;

public abstract class BaseCrawException extends Exception {
    /**
     * The cause of the error that is safe to be displayed to the UI
     */
    protected String errorMessage;

    protected BaseCrawException(String message, String uiErrorMessage) {
        super(message);
        this.errorMessage = uiErrorMessage;
    }

    /**
     * @return the UI-safe error message of this Exception
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}
