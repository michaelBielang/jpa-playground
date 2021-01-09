package codemerger.queries.demo.query;

import codemerger.queries.demo.entities.Person;
import codemerger.queries.demo.query.annotation.QueryAnnotationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import util.InsertTestData;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static util.InsertTestData.insertTargetPerson;

/**
 * Organisation: Codemerger Ldt.
 * Project: jpa-practising
 * Package: codemerger.queries.demo.query
 * Date: 08.01.2021
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@SpringBootTest
class TypedQueryDemoTest {

    @Autowired
    private QueryAnnotationRepository queryAnnotationRepository;

    @Autowired
    private TypedQueryDemo typedQueryDemo;

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 5; i++) {
            InsertTestData.insertRandomPerson(queryAnnotationRepository);
        }

        insertTargetPerson(queryAnnotationRepository, 1000);
        insertTargetPerson(queryAnnotationRepository, 1000);
    }


    @Test
    void getPersons() {
        // assumption
        final int numberOfPersonsInTargetZipCode = 2;

        // exec
        final List<Person> personsInTargetZipCode = typedQueryDemo.findPersonsInZipRange(999, 1001);

        // verify
        assertThat(personsInTargetZipCode.size()).isEqualTo(numberOfPersonsInTargetZipCode);
    }
}
