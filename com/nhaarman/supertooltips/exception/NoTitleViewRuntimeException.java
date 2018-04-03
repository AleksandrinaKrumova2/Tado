package com.nhaarman.supertooltips.exception;

public class NoTitleViewRuntimeException extends RuntimeException {
    public NoTitleViewRuntimeException() {
        super("No title View found. Are you sure it exists?");
    }
}
