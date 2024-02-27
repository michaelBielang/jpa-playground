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

 * @version: java version "14" 2020-03-17
 */

@Service
public class DataManagerService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ChildPersonManagerService childPersonManagerService;

    private String getCurrentMethod() {
        return Thread.currentThread()
                .getStackTrace()[1 + 1]
                .getMethodName();
    }

    public void insertPersonNoTransactional() {
        System.out.println(getCurrentMethod());

        final Person person = getNewPerson();

        saveForTransactionalDemo(person);

        throw new NoSuchElementException("Random Exception");
    }

    /**
     * This will create one physical transaction PT1, and within a logical transaction LT1 (LT2 lives within insertChildPerson)
     */
    @Transactional
    public void insertParentPerson() {
        System.out.println(getCurrentMethod());

        final Person person = getNewPerson();

        saveForTransactionalDemo(person);

        try {
            childPersonManagerService.insertChildPerson(getNewPerson());
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println("Catching child exception");
        }
    }

    @Transactional
    public void insertPersonExceptionInTransactional() {
        System.out.println(getCurrentMethod());

        final Person person = getNewPerson();

        saveForTransactionalDemo(person);

        throw new NoSuchElementException("Random Exception");
    }

    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }

    public Person getNewPerson() {
        return new Person(randomAlphabetic(5), randomAlphabetic(5));
    }

    public void saveForTransactionalDemo(Person person) {
        personRepository.save(person);
    }

    /**
     * This method suspends transaction A and creates a new one C in an own EntityManager
     * We use requires_new to ensure an object is stored in the DB when leaving this method.
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveForIsolation(Person person) {
        personRepository.save(person); // this command executes a commit and by leaving this method closes its EntityManager
    }

    public Person saveForStateDemo(Person person) {
        return personRepository.save(person);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteAllPersons() {
        personRepository.deleteAll();
    }
}
