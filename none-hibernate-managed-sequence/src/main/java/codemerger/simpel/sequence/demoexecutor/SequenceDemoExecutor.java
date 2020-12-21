package codemerger.simpel.sequence.demoexecutor;

import codemerger.simpel.sequence.entities.Person;
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
public class SequenceDemoExecutor {

    @Autowired
    private PersonHandlerService personHandlerService;

    @EventListener(ApplicationReadyEvent.class)
    public void runDemo() {
        final Person storedPerson = getPerson();

        final Person storedPerson2 = getPerson();

        System.out.println("Here we go:");
        System.out.println(storedPerson);
        System.out.println(storedPerson2);
    }

    private Person getPerson() {
        final Person sequentialDemoPerson = personHandlerService.createRandomPerson();
        return personHandlerService.savePerson(sequentialDemoPerson);
    }
}
