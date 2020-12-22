package codemerger.transactional.hib.components;

import codemerger.transactional.hib.entities.Person;
import codemerger.transactional.hib.events.ComponentTwoEvent;
import codemerger.transactional.hib.service.DataManagerService;
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
@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public class ComponentTwo implements ApplicationListener<ComponentTwoEvent> {

    @Autowired
    private DataManagerService dataManagerService;

    public void startTransactionTwo() {
        new Thread(() -> {
            final Person person = dataManagerService.findAllPersons().get(0);
            System.out.println(person);
            try {
                sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(person);
        }).start();
    }

    @Override
    @Async
    public void onApplicationEvent(ComponentTwoEvent event) {
        final Person person = dataManagerService.findAllPersons().get(0);
        System.out.println("ComponentTwo - 1: " + person);
        try {
            sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ComponentTwo - 2: " + person);
    }
}
