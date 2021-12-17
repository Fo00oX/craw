package at.ac.fhcampuswien.craw.lib.exceptions;

public abstract class BaseCrawException extends Exception {
    /**
     * The cause of the error that is safe to be displayed to the UI
     */
    protected String errorMessage;
    private final int errorCode;

    protected BaseCrawException(int errorCode, String message, String uiErrorMessage) {
        super(message);
        this.errorCode = errorCode;
        this.errorMessage = uiErrorMessage;
    }

    /**
     * @return the UI-safe error message of this Exception
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @return errorId of this Exception. See the README in the repository root for more information.
     */
    public int getErrorCode() {
        return errorCode;
    }
}
