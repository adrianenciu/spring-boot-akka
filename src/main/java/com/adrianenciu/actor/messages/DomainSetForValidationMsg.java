package com.adrianenciu.actor.messages;

import java.util.Set;

public class DomainSetForValidationMsg {

    private Set<String> domainNames;

    public DomainSetForValidationMsg(Set<String> domainNames) {
        this.domainNames = domainNames;
    }

    public Set<String> getDomainNames() {
        return domainNames;
    }

    public void setDomainNames(Set<String> domainNames) {
        this.domainNames = domainNames;
    }

    @Override
    public String toString() {
        return "DomainSetForValidationMsg{" +
                "domainNames=" + domainNames +
                '}';
    }
}
