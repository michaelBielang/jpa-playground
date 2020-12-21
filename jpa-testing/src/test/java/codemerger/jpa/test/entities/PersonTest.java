package codemerger.jpa.test.entities;

import codemerger.jpa.test.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Organisation: Codemerger Ldt.
 * Project: jpa
 * Package: codemerger.jpa.test.entities
 * Date: 19.12.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=validate"
})
class PersonTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void whenSaved_thenFindsByName() {
        personRepository.save(new Person(
                "test@gmail.com",
                "Zaphod Beeblebrox",
                "zaphod@galaxy.net"));

        assertThat(personRepository.findByFirstName("Zaphod Beeblebrox")).isNotNull();
    }

    @Test
    void whenSaved_thenFindsByAllFields() {
        final String email = randomAlphabetic(10);
        final String firstName = randomAlphabetic(10);
        final String lastName = randomAlphabetic(10);

        personRepository.save(new Person(
                email, firstName, lastName
        ));

        assertThat(personRepository.findByAllFields(email, firstName, lastName)).isNotNull();
    }

    @Test
    @Sql("classpath:setup_person_test.sql")
    void whenInitializedByDbUnit_thenFindsByName() {
        Person user = personRepository.findByEmail("createdPerson@gmail.com");
        assertThat(user).isNotNull();
    }
}
