package com.adrianenciu.actor.messages;

public class DomainForValidationMsg {

    private String domainName;

    public DomainForValidationMsg(String domainName) {
        this.domainName = domainName;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    @Override
    public String toString() {
        return "DomainForValidationMsg{" +
                "domainName='" + domainName + '\'' +
                '}';
    }
}
