package codemerger.jpa.test;

import codemerger.jpa.test.entities.Person;
import codemerger.jpa.test.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=validate"
})
class TestApplicationTests {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void whenSaved_thenFindsByName() {
        personRepository.save(new Person(
                "test@test.de",
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


}
