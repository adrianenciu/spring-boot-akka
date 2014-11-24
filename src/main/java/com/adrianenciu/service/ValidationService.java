package com.adrianenciu.service;

import com.adrianenciu.model.*;
import com.adrianenciu.model.Error;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ValidationService {

    public List<Error> validate(String domainName) {
        List<Error> errors = new ArrayList<Error>();
        if (domainName.equals("adrian.com")) {
            Error invalidDomainError = new Error("INVALID_DOMAIN", "this domain is invalid", new String[] {"domain.name"});
            Error anotherBadError = new Error("ANOTHER_BAD_ERROR", "this is a really bad error", new String[] {"domain.name"});
            errors.add(invalidDomainError);
            errors.add(anotherBadError);
        } else if (domainName.equals("alice.com")) {
            Error funnyError = new Error("TOO_FUNNY_DOMAIN", "really funny domain error", new String[] {"domain.name"});
            errors.add(funnyError);
        }
        return errors;
    }

}
