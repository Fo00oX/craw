package at.ac.fhcampuswien.craw.cli.exceptions;

import at.ac.fhcampuswien.craw.lib.exceptions.BaseCrawException;

public class CliException extends BaseCrawException {

    private CliException(String message, String uiErrorMessage) {
        super(message, uiErrorMessage);
    }

    private CliException(String message) {
        super(message);
    }

    public static CliException ForNoArgumentsProvided() {
        return new CliException("Empty Args Array. No command specified.", "A command needs to be provided. See 'craw --help' for more information.");
    }

    public static CliException ForInvalidCommand(String command) {
        return new CliException("The specified command '" + command + "' is not valid. See 'craw --help' for more information.");
    }
}
