package com.adrianenciu.actor;

import akka.actor.UntypedActor;
import com.adrianenciu.actor.messages.DomainForValidationMsg;
import com.adrianenciu.actor.messages.ValidatedDomainMsg;
import com.adrianenciu.model.Error;
import com.adrianenciu.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class DomainValidationActor extends UntypedActor {

    @Autowired
    private ValidationService validationService;

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof DomainForValidationMsg) {
            DomainForValidationMsg domain = (DomainForValidationMsg) message;
            List<Error> validationErrors = validationService.validate(domain.getDomainName());
            getSender().tell(new ValidatedDomainMsg(domain.getDomainName(), validationErrors), getSelf());
            getContext().stop(getSelf());
        }
    }
}
