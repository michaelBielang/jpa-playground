package codemerger.jpa.test.evenhandler;

import codemerger.jpa.test.entities.Person;
import codemerger.jpa.test.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.Thread.sleep;

/**
 * Organisation: Codemerger Ldt.
 * Project: jpa
 * Package: codemerger.jpa.test.evenhandler
 * Date: 20.12.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@Component
@Transactional
public class ApplicationEventHandler {

    @Autowired
    private PersonRepository personRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void testSQL() throws InterruptedException {
        final Person randomPerson = new Person("1", "2", "3");

        personRepository.save(randomPerson);

        final Person resultPerson = personRepository.findByEmail("1");
        System.out.println(resultPerson);
        sleep(500000000);
    }
}
