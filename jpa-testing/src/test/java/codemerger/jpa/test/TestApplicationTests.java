package codemerger.jpa.test;

import codemerger.jpa.test.entities.Person;
import codemerger.jpa.test.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

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

}
