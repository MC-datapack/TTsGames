package dev.TTs.resources.Json;

import com.google.gson.JsonParseException;

public class TTsReadException extends JsonParseException {
    public TTsReadException(String message) {
        super(message);
    }

    public TTsReadException(Exception exception) {
        this(exception.getMessage());
    }
}
