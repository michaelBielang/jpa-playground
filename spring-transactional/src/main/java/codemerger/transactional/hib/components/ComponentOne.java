package codemerger.transactional.hib.components;

import codemerger.transactional.hib.entities.Person;
import codemerger.transactional.hib.events.ComponentOneEvent;
import codemerger.transactional.hib.service.DataManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
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
@Transactional(propagation = Propagation.REQUIRED)
public class ComponentOne implements ApplicationListener<ComponentOneEvent> {

    @Autowired
    private DataManagerService dataManagerService;

    public void startTransactionOne() {
        new Thread(() -> {
            final Person person = dataManagerService.save(dataManagerService.getNewPerson());
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            person.setFirstName("testName");
        }).start();
    }

    @Override
    @Async
    public void onApplicationEvent(ComponentOneEvent event) {
        final Person person = dataManagerService.save(dataManagerService.getNewPerson());
        System.out.println("Person saved in ComponentOne");
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("SetName in ComponentOne");
        person.setFirstName("testName");
    }
}
