package codemerger.queries.demo.queryannotation;

import codemerger.queries.demo.entities.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Organisation: Codemerger Ldt.
 * Project: jpa-practising
 * Package: codemerger.queries.demo.queryannotation
 * Date: 06.01.2021
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@DataJpaTest
class QueryAnnotationRepositoryTest {

    private static final int TARGET_ZIP = 1000;

    @Autowired
    private QueryAnnotationRepository queryAnnotationRepository;

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 5; i++) {
            insertRandomPerson();
        }

        insertTargetPerson();
        insertTargetPerson();
    }

    @Test
    void findPersonsInZipCodeRange() {
        // assumption
        final int numberOfPersonsInTargetZipCode = 2;

        // exec
        final List<Person> personsInTargetZipCode = queryAnnotationRepository.findPersonsInZipCodeRange(999, 1001);

        // verify
        assertThat(personsInTargetZipCode.size()).isEqualTo(numberOfPersonsInTargetZipCode);
    }


    private void insertTargetPerson() {
        final String firstName = getRandomName();
        final String lastName = getRandomName();

        final Person person = new Person(firstName, lastName, TARGET_ZIP);

        queryAnnotationRepository.save(person);
    }

    private void insertRandomPerson() {
        final int zipCode = 5000;
        final String firstName = getRandomName();
        final String lastName = getRandomName();

        final Person person = new Person(firstName, lastName, zipCode);

        queryAnnotationRepository.save(person);
    }

    private String getRandomName() {
        return randomAlphabetic(10);
    }
}
