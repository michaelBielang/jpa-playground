package codemerger.transactional.hib.demoexecutor;

import codemerger.transactional.hib.events.ComponentOneEvent;
import codemerger.transactional.hib.events.ComponentTwoEvent;
import codemerger.transactional.hib.events.TriggerStateDemoEvent;
import codemerger.transactional.hib.events.TriggerTransactionalDemoEvent;
import codemerger.transactional.hib.service.DataManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * Organisation: Codemerger Ldt.
 * Project: JPA-Practising
 * Package: codemerger.transactional.hib.demoexecutor
 * Date: 17.12.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@Component
public class DemoExecutor {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private DataManagerService dataManagerService;

    @EventListener(ApplicationReadyEvent.class)
    public void triggerDemoExecutors() {

        final Scanner scanner = new Scanner(System.in);
        printInputCommand();

        String input = scanner.nextLine();

        while (!input.equalsIgnoreCase("exit")) {
            switch (input) {
                case "state":
                    applicationEventPublisher.publishEvent(new TriggerStateDemoEvent(this));
                    break;
                case "transactional":
                    applicationEventPublisher.publishEvent(new TriggerTransactionalDemoEvent(this));
                    break;
                case "isolation":
                    //applicationEventPublisher.publishEvent(new TriggerIsolationDemoEvent(this));
                    applicationEventPublisher.publishEvent(new ComponentOneEvent(this));
                    applicationEventPublisher.publishEvent(new ComponentTwoEvent(this));
                default:
                    break;
            }
            printInputCommand();
            input = scanner.nextLine();
            dataManagerService.deleteAllPersons();
        }
    }

    private void printInputCommand() {
        System.out.println("Enter 'state', 'transactional' or 'isolation' to run proper demo or exit to leave");
    }
}
