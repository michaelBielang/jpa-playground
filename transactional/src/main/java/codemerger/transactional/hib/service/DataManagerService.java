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

    private String getCurrentMethod() {
        return Thread.currentThread()
                .getStackTrace()[1 + 1]
                .getMethodName();
    }

    public void createPersonNoTransactional() throws NoSuchElementException {
        System.out.println(getCurrentMethod());

        final Person person = getNewPerson();

        saveWithoutTransaction(person);

        throw new NoSuchElementException("Random Exception");
    }

    @Transactional
    public void createPersonRequiresNew() throws NoSuchElementException {
        System.out.println(getCurrentMethod());

        final Person person = getNewPerson();

        saveRequiresNew(person);

        throw new NoSuchElementException("Random Exception");
    }

    @Transactional
    public void createPersonExceptionInTransactional() throws NoSuchElementException {
        System.out.println(getCurrentMethod());

        final Person person = getNewPerson();

        save(person);

        throw new NoSuchElementException("Random Exception");
    }

    @Transactional
    public void createAndSavePersonWithoutException() throws NoSuchElementException {
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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveRequiresNew(Person person) {
        personRepository.save(person);
    }

    public void saveWithoutTransaction(Person person) {
        personRepository.save(person);
    }

    public void deleteAllPersons() {
        personRepository.deleteAll();
    }
}
