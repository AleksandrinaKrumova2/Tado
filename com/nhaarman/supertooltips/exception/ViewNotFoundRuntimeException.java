package com.nhaarman.supertooltips.exception;

public class ViewNotFoundRuntimeException extends RuntimeException {
    public ViewNotFoundRuntimeException() {
        super("View not found for this resource id. Are you sure it exists?");
    }
}
