package codemerger.transactional.hib.service;

import codemerger.transactional.hib.entities.Person;
import codemerger.transactional.hib.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
    private PersonRepository personRepository;

    @Autowired
    private ChildManagerService childManagerService;

    private String getCurrentMethod() {
        return Thread.currentThread()
                .getStackTrace()[1 + 1]
                .getMethodName();
    }

    public void insertPersonNoTransactional() throws NoSuchElementException {
        System.out.println(getCurrentMethod());

        final Person person = getNewPerson();

        save(person);

        throw new NoSuchElementException("Random Exception");
    }

    /**
     * This will create one physical transaction PT1, and within a logical transaction LT1.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertParentPerson() {
        System.out.println(getCurrentMethod());

        final Person person = getNewPerson();

        save(person);

        try {
            childManagerService.insertChildPerson(getNewPerson());
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println("Catching child exception");
        }
    }


    @Transactional
    public void insertPersonExceptionInTransactional() {
        System.out.println(getCurrentMethod());

        final Person person = getNewPerson();

        save(person);

        throw new NoSuchElementException("Random Exception");
    }

    @Transactional
    public void createAndSavePersonWithoutException() {
        System.out.println(getCurrentMethod());

        final Person person = getNewPerson();

        save(person);
    }

    public List<Person> getPersonInDb() {
        return personRepository.findAll();
    }

    public Person getNewPerson() {
        return new Person(randomAlphabetic(5), randomAlphabetic(5));
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public void deleteAllPersons() {
        personRepository.deleteAll();
    }
}
