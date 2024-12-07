package dev.TTs.lang;

public final class TTsException extends RuntimeException {
    private final String message;

    public TTsException(String message) {
        super(message);
        this.message = message;
        System.exit(ExitCodes.ERROR);
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
