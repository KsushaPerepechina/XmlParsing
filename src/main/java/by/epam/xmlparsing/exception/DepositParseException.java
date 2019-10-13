package by.epam.xmlparsing.exception;

public class DepositParseException extends Exception {
    public DepositParseException() {
    }

    public DepositParseException(String message) {
        super(message);
    }

    public DepositParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepositParseException(Throwable cause) {
        super(cause);
    }
}
