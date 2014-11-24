package com.adrianenciu.actor.messages;

import com.adrianenciu.model.Error;

import java.util.List;

public class ValidatedDomainMsg {

    private String domainName;
    private List<Error> validationErrors;

    public ValidatedDomainMsg(String domainName, List<Error> validationErrors) {
        this.domainName = domainName;
        this.validationErrors = validationErrors;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public List<Error> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<Error> validationErrors) {
        this.validationErrors = validationErrors;
    }

    @Override
    public String toString() {
        return "ValidatedDomainMsg{" +
                "domainName='" + domainName + '\'' +
                ", validationErrors=" + validationErrors +
                '}';
    }
}
