package com.adrianenciu.controller;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.dispatch.OnComplete;
import akka.dispatch.OnSuccess;
import akka.pattern.Patterns;
import com.adrianenciu.actor.DomainListValidationActor;
import com.adrianenciu.actor.messages.DomainSetForValidationMsg;
import com.adrianenciu.actor.messages.ValidatedDomainMapMsg;
import com.adrianenciu.config.SpringExtension;
import com.adrianenciu.model.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import scala.concurrent.Future;

import java.util.*;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("domain-validation")
public class DomainValidationController {
    private static final int TIMEOUT = 1000;

    @Autowired
    private ActorSystem actorSystem;

    @Autowired
    private SpringExtension springExtension;

    @RequestMapping(method = RequestMethod.POST, value = "domains")
    public Callable<ResponseEntity> validateDomains(final HashSet<String> domainNames) {
        System.out.println("Salut");
        return new Callable<ResponseEntity>() {

            private ResponseEntity responseEntity;

            @Override
            public ResponseEntity call() throws Exception {
                ActorRef domainListValidationActor = actorSystem.actorOf(springExtension.props(DomainListValidationActor.class));
                Future<Object> future = Patterns.ask(domainListValidationActor, new DomainSetForValidationMsg(domainNames), TIMEOUT);

                future.onComplete(new OnComplete<Object>() {
                    @Override
                    public void onComplete(Throwable failure, Object result) throws Throwable {
                        if (failure != null) {
                            responseEntity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                        } else {
                            ValidatedDomainMapMsg validatedDomainsMsg = (ValidatedDomainMapMsg) result;
                            responseEntity = new ResponseEntity(validatedDomainsMsg.getValidatedDomains(), HttpStatus.OK);
                        }
                    }
                }, actorSystem.dispatcher());
                return responseEntity;
            }
        };
    }
}
