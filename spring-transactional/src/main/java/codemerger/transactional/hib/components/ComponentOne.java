package codemerger.transactional.hib.components;

import codemerger.transactional.hib.entities.Person;
import codemerger.transactional.hib.events.ComponentOneEvent;
import codemerger.transactional.hib.service.DataManagerService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static java.lang.Thread.sleep;

/**
 * Organisation: Codemerger Ldt.
 * Project: jpa
 * Package: codemerger.transactional.hib.components
 * Date: 22.12.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@EnableAsync
@Component
@Transactional(propagation = Propagation.REQUIRED)
public class ComponentOne implements ApplicationListener<ComponentOneEvent> {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private DataManagerService dataManagerService;

    /**
     * Here lives transaction A in its own EntityManager
     */
    @SneakyThrows
    @Override
    @Async
    public void onApplicationEvent(ComponentOneEvent event) {
        // we are in transaction a .save will create an own transaction c
        saveNewPerson();

        // now we resume the suspended transaction A
        final Person person = dataManagerService.findAllPersons().get(0); //this participates in the existing transaction

        // we update the name and, important!, flush
        setNameAndFlush(person);

        System.out.println("Sleeping in ComponentONE");
        sleep(2000); // by leaving this method with commit to the transaction and close the EM/*Data*/
    }

    private void setNameAndFlush(Person person) {
        System.out.println("SetName in ComponentONE");
        person.setFirstName("codemerger.com");
        entityManager.flush();
    }

    private void saveNewPerson() {
        System.out.println("Saving a new Person in ComponentONE");
        dataManagerService.save(dataManagerService.getNewPerson());
        System.out.println("Person saved in ComponentONE");
    }
}
