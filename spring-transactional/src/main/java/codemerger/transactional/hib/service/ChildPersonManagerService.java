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

 * @version: java version "14" 2020-03-17
 */

@Service
public class ChildPersonManagerService {

    @Autowired
    private PersonRepository personRepository;

    /**
     * This transaction lives in the same physical transaction (PT1) as its parent but has an own logical transaction LT2
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertChildPerson(Person person) {

        save(person);

        throw new NoSuchElementException("Random Exception"); // this causes a rollback on LT1
    }

    private void save(Person person) {
        personRepository.save(person);
    }
}
