package codemerger.simpel.sequence.eventhandler;

import codemerger.simpel.sequence.dto.SequentialPerson;
import codemerger.simpel.sequence.service.PersonHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Organisation: Codemerger Ldt.
 * Project: sequence
 * Package: codemerger.simpel.sequence.eventhandler
 * Date: 13.12.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@Component
public class SpringEventHandler {

    @Autowired
    private PersonHandlerService personHandlerService;

    @EventListener(ApplicationReadyEvent.class)
    public void runDemo() {
        final SequentialPerson sequentialDemoPerson = personHandlerService.createRandomPerson();
        final SequentialPerson storedPerson = personHandlerService.savePerson(sequentialDemoPerson);

        final SequentialPerson sequentialDemoPerson2 = personHandlerService.createRandomPerson();
        final SequentialPerson storedPerson2 = personHandlerService.savePerson(sequentialDemoPerson2);

        System.out.println("Here we go:");
        System.out.println(storedPerson);
        System.out.println(storedPerson2);
    }
}
