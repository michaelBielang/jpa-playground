package codemerger.queries.demo.query;

import codemerger.queries.demo.entities.Person;
import codemerger.queries.demo.query.annotation.QueryAnnotationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import util.InsertTestData;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static util.InsertTestData.insertTargetPerson;

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

    public static final int ZIP_FROM = 999;
    public static final int ZIP_TO = 1001;
    private static final int TARGET_ZIP = 1000;
    @Autowired
    private QueryAnnotationRepository queryAnnotationRepository;

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 5; i++) {
            InsertTestData.insertRandomPerson(queryAnnotationRepository);
        }

        insertTargetPerson(queryAnnotationRepository, 1000);
        insertTargetPerson(queryAnnotationRepository, 1000);
    }

    @Test
    void findPersonsInZipCodeRange() {
        // assumption
        final int numberOfPersonsInTargetZipCode = 2;

        // exec
        final List<Person> personsInTargetZipCode = queryAnnotationRepository.findPersonsInZipCodeRangeWithNativeQuery(ZIP_FROM, ZIP_TO);

        // verify
        assertThat(personsInTargetZipCode.size()).isEqualTo(numberOfPersonsInTargetZipCode);
    }

    @Test
    void findPersonsInZipCodeRangeWithJPQ() {
        // assumption
        final int numberOfPersonsInTargetZipCode = 2;

        // exec
        final List<Person> personsInTargetZipCode = queryAnnotationRepository.findPersonsInZipCodeRangeWithJPQ(ZIP_FROM, ZIP_TO, Sort.by("firstName"));
        // verify
        assertThat(personsInTargetZipCode.size()).isEqualTo(numberOfPersonsInTargetZipCode);
    }

    @Test
    void findAllUsersWithPagination() {
        // assumption
        final int pageSize = 5;
        final int numberOfPersonsInTargetZipCode = 2;

        //setup
        final Pageable pageable = PageRequest.of(0, pageSize, Sort.by(
                Sort.Order.asc("firstName"),
                Sort.Order.desc("id")));

        // exec
        final Page<Person> personsInTargetZipCode = queryAnnotationRepository.findPersonsInZipRangeWithPagination(ZIP_FROM, ZIP_TO, pageable);
        // verify

        assertThat(personsInTargetZipCode.getTotalPages()).isEqualTo(1);
        assertThat(personsInTargetZipCode.getTotalElements()).isEqualTo(numberOfPersonsInTargetZipCode);
    }
}
