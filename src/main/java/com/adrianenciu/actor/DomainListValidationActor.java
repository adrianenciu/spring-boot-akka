package com.adrianenciu.actor;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import com.adrianenciu.actor.messages.DomainForValidationMsg;
import com.adrianenciu.actor.messages.DomainSetForValidationMsg;
import com.adrianenciu.actor.messages.ValidatedDomainMapMsg;
import com.adrianenciu.actor.messages.ValidatedDomainMsg;
import com.adrianenciu.config.SpringExtension;
import com.adrianenciu.model.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@Scope("prototype")
public class DomainListValidationActor extends UntypedActor {

    private Map<String, List<Error>> validationErrors;
    private int noOfDomainsValidated;

    @Autowired
    private SpringExtension springExtension;

    @Override
    public void preStart() throws Exception {
        validationErrors = new HashMap<String, List<Error>>();
        super.preStart();
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof DomainSetForValidationMsg) {
            Set<String> domainNames = ((DomainSetForValidationMsg) message).getDomainNames();
            noOfDomainsValidated = domainNames.size();

            for (String domainName : domainNames) {
                ActorRef domainValidationActor = getContext().actorOf(springExtension.props(DomainValidationActor.class));
                domainValidationActor.tell(new DomainForValidationMsg(domainName), getSelf());
            }
        } else if (message instanceof ValidatedDomainMsg) {
            noOfDomainsValidated--;
            ValidatedDomainMsg validatedDomain = (ValidatedDomainMsg) message;
            validationErrors.put(validatedDomain.getDomainName(), validatedDomain.getValidationErrors());

            if (noOfDomainsValidated == 0) {
                getContext().parent().tell(new ValidatedDomainMapMsg(validationErrors), getSelf());
                getContext().stop(getSelf());
            }
        } else {
            unhandled(message);
        }
    }
}
