package codemerger.transactional.hib.demoexecutor;

import codemerger.transactional.hib.entities.Person;
import codemerger.transactional.hib.events.TriggerStateDemoEvent;
import codemerger.transactional.hib.service.DataManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

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
@Transactional // it is crucial to have the annotation here so that the AOP Proxy is in control of the Person obj
public class StateDemoExecutor implements ApplicationListener<TriggerStateDemoEvent> {

    @Autowired
    private DataManagerService dataManagerService;

    @Override
    public void onApplicationEvent(TriggerStateDemoEvent event) {
        final Person newPerson = getNewPerson();
        final Person sessionPersistedPerson = dataManagerService.save(newPerson);

        final String newFirstName = randomAlphabetic(10);
        sessionPersistedPerson.setFirstName(newFirstName);

        dataManagerService.findAllPersons().stream()
                .findFirst()
                .ifPresent(person -> {
                    if (person.getFirstName().equals(newFirstName)) {
                        System.out.println("Cool we updated the db object implicitly");
                    } else {
                        System.out.println("Ouch");
                    }
                });

        dataManagerService.deleteAllPersons();
    }

    private Person getNewPerson() {
        return new Person(randomAlphabetic(10), randomAlphabetic(10));
    }
}
