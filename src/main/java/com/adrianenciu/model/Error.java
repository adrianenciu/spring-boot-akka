package com.adrianenciu.model;

import java.util.Arrays;

public class Error {

    private String errorCode;
    private String description;
    private String[] fields;

    public Error(String errorCode, String description, String[] fields) {
        this.errorCode = errorCode;
        this.description = description;
        this.fields = fields;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "Error{" +
                "errorCode='" + errorCode + '\'' +
                ", description='" + description + '\'' +
                ", fields=" + Arrays.toString(fields) +
                '}';
    }
}
