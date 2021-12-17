package at.ac.fhcampuswien.craw.cli.exceptions;

import at.ac.fhcampuswien.craw.lib.exceptions.BaseCrawException;

public class CliException extends BaseCrawException {

    private CliException(int errorCode, String message, String uiErrorMessage) {
        super(errorCode, message, uiErrorMessage);
    }

    private CliException(int errorCode, String message) {
        super(errorCode, message, message);
    }

    public static CliException ForNoArgumentsProvided() {
        return new CliException(201, "Empty Args Array. No command specified.", "A command needs to be provided. See 'craw --help' for more information.");
    }

    public static CliException ForInvalidCommand(String command) {
        return new CliException(202, "The specified command '" + command + "' is not valid. See 'craw --help' for more information.");
    }
}
