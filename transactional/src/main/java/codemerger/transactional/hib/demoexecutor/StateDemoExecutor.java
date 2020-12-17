package codemerger.transactional.hib.demoexecutor;

import codemerger.transactional.hib.entities.Person;
import codemerger.transactional.hib.events.TriggerTransactionalDemoEvent;
import codemerger.transactional.hib.service.DataManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

/**
 * Organisation: Codemerger Ldt.
 * Project: JPA-Practising
 * Package: codemerger.transactional.hib.event
 * Date: 17.12.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@Component
public class StateDemoExecutor {

    @Autowired
    private DataManagerService dataManagerService;

    @EventListener(TriggerTransactionalDemoEvent.class)
    public void executeStateDemo() {

        final Person newPerson = getNewPerson();
        final Person sessionPersistedPerson = dataManagerService.save(newPerson);

        final String newFirstName = randomAlphabetic(10);
        sessionPersistedPerson.setFirstName(newFirstName);

        dataManagerService.getPersonInDb().stream()
                .findFirst()
                .ifPresent(person -> {
                    if (person.getFirstName().equals(newFirstName)) {
                        System.out.println("Cool we updated the db object implicitly");
                    } else {
                        System.out.println("Ouch");
                    }
                });
    }

    private Person getNewPerson() {
        return new Person(randomAlphabetic(10), randomAlphabetic(10));
    }

}
