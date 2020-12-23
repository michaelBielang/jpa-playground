package codemerger.transactional.hib.components;

import codemerger.transactional.hib.entities.Person;
import codemerger.transactional.hib.events.ComponentTwoEvent;
import codemerger.transactional.hib.service.DataManagerService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional(isolation = Isolation.READ_UNCOMMITTED) // TODO this can and should be switch to COMMITED/UNCOMMITED
public class ComponentTwo implements ApplicationListener<ComponentTwoEvent> {

    @Autowired
    private DataManagerService dataManagerService;

    /**
     * Here lives Transaction B in its own EntityManager
     */
    @SneakyThrows
    @Override
    @Async
    public void onApplicationEvent(ComponentTwoEvent event) {
        sleep(250); // ensuring that transaction A+C create and persist a new person

        final Person person = dataManagerService.findAllPersons().get(0);
        System.out.println("ComponentTWO: " + person);

        sleep(2500); // Transaction B will commit and close the EM
    }
}
