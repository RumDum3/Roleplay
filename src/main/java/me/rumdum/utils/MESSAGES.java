package me.rumdum.utils;

public enum MESSAGES {

    ERROR_MESSAGE("An error occurred. Please contact RumDum#9721");

    private final String message;

    MESSAGES(String message) {
        this.message = message;
    }

    public String getMessage() { return message; }

}
