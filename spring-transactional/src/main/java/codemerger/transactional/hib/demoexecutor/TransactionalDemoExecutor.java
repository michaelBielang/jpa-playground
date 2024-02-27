package codemerger.transactional.hib.demoexecutor;

import codemerger.transactional.hib.events.TriggerTransactionalDemoEvent;
import codemerger.transactional.hib.service.DataManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

/**
 * Organisation: Codemerger Ldt.
 * Project: transactions
 * Package: codemerger.transactions.event
 * Date: 12.12.2020
 *

 * @version: java version "14" 2020-03-17
 */

@Component
public class TransactionalDemoExecutor implements ApplicationListener<TriggerTransactionalDemoEvent> {

    @Autowired
    private DataManagerService dataManagerService;

    @Override
    public void onApplicationEvent(TriggerTransactionalDemoEvent event) {
        try {
            // HAS NO ENTRIES
            dataManagerService.insertPersonExceptionInTransactional();
        } catch (NoSuchElementException noSuchElementException) {
            printAndReset(dataManagerService);
        }

        // This sequence will insert two persons one in insertParentPerson and another in insertChildPerson
        // A rollback will be caused in insertChildPerson but not in the caller as a result we have ONE ENTRY
        dataManagerService.insertParentPerson();
        printAndReset(dataManagerService);

        try {
            // NO ROLLBACK - HAS ENTRIES
            dataManagerService.insertPersonNoTransactional();
        } catch (NoSuchElementException noSuchElementException) {
            printAndReset(dataManagerService);
        }
        dataManagerService.deleteAllPersons();
    }

    private void printAmountOfPersons(DataManagerService dataManagerService) {
        System.out.println("Number of Persons: " + dataManagerService.findAllPersons().size());
        System.out.println("");
    }

    private void deletePersonsFromDb(DataManagerService dataManagerService) {
        dataManagerService.deleteAllPersons();
    }

    private void printAndReset(DataManagerService dataManagerService) {
        printAmountOfPersons(dataManagerService);
        deletePersonsFromDb(dataManagerService);
    }
}
