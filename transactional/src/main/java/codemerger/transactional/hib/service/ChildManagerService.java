package codemerger.transactional.hib.service;

import codemerger.transactional.hib.entities.Person;
import codemerger.transactional.hib.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

/**
 * Organisation: Codemerger Ldt.
 * Project: JPA-Practising
 * Package: codemerger.transactional.hib.service.db
 * Date: 18.12.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@Service
public class ChildManagerService {

    @Autowired
    private PersonRepository personRepository;

    /**
     * This transaction lives in the same physical transaction (PT1) as its parent but has an own logical transaction LT2
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertChildPerson(Person person) {

        save(person);

        throw new NoSuchElementException("Random Exception");
    }

    private void save(Person person) {
        personRepository.save(person);
    }

}
