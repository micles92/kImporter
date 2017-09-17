package com.micles92.importer.model;

import com.micles92.importer.logic.FileProcessorExecutor;

import java.util.Optional;

/**
 * Created by mlesniak on 2017-09-14.
 *
 */
public class FileProcessingResult {


    private Optional<Exception> exception = Optional.empty();
    private boolean valid;
    private String message;

    public Optional<Exception> getException() {
        return exception;
    }

    public void setException(Optional<Exception> exception) {
        this.exception = exception;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static FileProcessingResult ofInvalid(String message, Exception ex) {
        FileProcessingResult result = new FileProcessingResult();
        result.setMessage(message);
        result.setValid(false);
        result.setException(Optional.of(ex));
        return result;

    }

    public static FileProcessingResult ofValid() {
        FileProcessingResult result = new FileProcessingResult();
        result.setMessage("Operation finished successfully" );
        result.setValid(true);
        return result;

    }

}
