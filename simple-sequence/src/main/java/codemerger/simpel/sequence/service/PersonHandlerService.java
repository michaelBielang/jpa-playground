package codemerger.simpel.sequence.service;

import codemerger.simpel.sequence.entities.Person;
import codemerger.simpel.sequence.repository.SequentialPersonRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;

/**
 * Organisation: Codemerger Ldt.
 * Project: sequence
 * Package: codemerger.simpel.sequence.service
 * Date: 13.12.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@Service
public class PersonHandlerService {

    @Autowired
    private SequentialPersonRepository sequentialPersonRepository;

    @Autowired
    private EntityManager entityManager;

    public Person createRandomPerson() {
        final String firstName = getRandomName();
        final String lastName = getRandomName();

        return new Person(firstName, lastName);
    }

    private String getRandomName() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    private Long getIdFromSequence() {
        final Query query = entityManager.createNativeQuery("SELECT SEQUENTIAL_PERSON_SEQUENCE.nextval FROM dual");
        return ((BigInteger) query.getSingleResult()).longValue();
    }

    public Person savePerson(Person person) {
        final long id = getIdFromSequence();
        person.setId(id);

        return sequentialPersonRepository.save(person);
    }
}
