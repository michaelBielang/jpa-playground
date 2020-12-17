package codemerger.transactional.hib.service;

import codemerger.transactional.hib.entities.Person;
import codemerger.transactional.hib.service.db.DatabaseNoTransactionalService;
import codemerger.transactional.hib.service.db.TransactionalDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

/**
 * Organisation: Codemerger Ldt.
 * Project: transactions
 * Package: codemerger.transactions.service
 * Date: 08.11.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@Service
public class DataManagerService {

    @Autowired
    private DatabaseNoTransactionalService databaseNoTransactionalService;
    @Autowired
    private TransactionalDatabaseService transactionalDatabaseService;

    private String getCurrentMethod() {
        return Thread.currentThread()
                .getStackTrace()[1 + 1]
                .getMethodName();
    }

    @Transactional
    public void createPersonWithException() throws NoSuchElementException {
        System.out.println(getCurrentMethod());

        final Person person = getNewPerson();

        databaseNoTransactionalService.save(person);
        throw new NoSuchElementException("Random Exception");
    }

    @Transactional
    public void createPersonWithExceptionRequiresNew() throws NoSuchElementException {
        System.out.println(getCurrentMethod());

        final Person person = getNewPerson();

        transactionalDatabaseService.saveRequiresNew(person);

        throw new NoSuchElementException("Random Exception");
    }

    public void createPersonExceptionAfterTransactional() throws NoSuchElementException {
        System.out.println(getCurrentMethod());

        final Person person = getNewPerson();

        transactionalDatabaseService.save(person);

        throw new NoSuchElementException("Random Exception");
    }

    public List<Person> getPersonInDb() {
        return transactionalDatabaseService.getPersons();
    }

    public Person getNewPerson() {
        return new Person(randomAlphabetic(5), randomAlphabetic(5));
    }

    public Person save(Person person) {
        return transactionalDatabaseService.save(person);
    }

    public void deleteAllPersons() {
        transactionalDatabaseService.deleteAllPersons();
    }
}
