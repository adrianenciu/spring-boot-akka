package com.adrianenciu.actor.messages;

import com.adrianenciu.model.Error;

import java.util.List;
import java.util.Map;

public class ValidatedDomainMapMsg {

    private Map<String, List<Error>> validatedDomains;

    public ValidatedDomainMapMsg(Map<String, List<Error>> validatedDomains) {
        this.validatedDomains = validatedDomains;
    }

    public Map<String, List<Error>> getValidatedDomains() {
        return validatedDomains;
    }

    public void setValidatedDomains(Map<String, List<Error>> validatedDomains) {
        this.validatedDomains = validatedDomains;
    }

    @Override
    public String toString() {
        return "ValidatedDomainMapMsg{" +
                "validatedDomains=" + validatedDomains +
                '}';
    }
}
