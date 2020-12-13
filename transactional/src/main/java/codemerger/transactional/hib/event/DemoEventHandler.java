package codemerger.transactional.hib.event;

import codemerger.transactional.hib.service.DataManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

/**
 * Organisation: Codemerger Ldt.
 * Project: transactions
 * Package: codemerger.transactions.event
 * Date: 12.12.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@Component
public class DemoEventHandler {

    @Autowired
    private DataManagerService dataManagerService;

    /**
     * Could be a CommandLineRunner or PostConstruct be too
     */
    @EventListener(ApplicationReadyEvent.class)
    public void executeDemoCode() {

        try {
            // HAS ENTRIES
            dataManagerService.createPersonExceptionAfterTransactional();
        } catch (NoSuchElementException noSuchElementException) {
            printAndReset(dataManagerService);
        }

        try {
            // HAS ENTRIES
            dataManagerService.createPersonWithExceptionRequiresNew();
        } catch (NoSuchElementException noSuchElementException) {
            printAndReset(dataManagerService);
        }

        try {
            // HAS NO ENTRIES
            dataManagerService.createPersonWithException();
        } catch (NoSuchElementException noSuchElementException) {
            printAndReset(dataManagerService);
        }
    }

    private void printAmountOfPersons(DataManagerService dataManagerService) {
        System.out.println("Number of Persons: " + dataManagerService.getPersonInDb().size());
        System.out.println("########### \n");
    }

    private void deletePersonsFromDb(DataManagerService dataManagerService) {
        dataManagerService.deleteAllPersons();
    }

    private void printAndReset(DataManagerService dataManagerService) {
        printAmountOfPersons(dataManagerService);
        deletePersonsFromDb(dataManagerService);
    }
}
