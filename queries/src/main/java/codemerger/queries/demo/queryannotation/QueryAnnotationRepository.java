package codemerger.queries.demo.queryannotation;

import codemerger.queries.demo.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Organisation: Codemerger Ldt.
 * Project: jpa-practising
 * Package: codemerger.queries.demo.queryannotation
 * Date: 06.01.2021
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@Repository
public interface QueryAnnotationRepository extends CrudRepository<Person, Long> {

    @Query(value = "select * from Person where POST_CODE between :from and :to", nativeQuery = true)
    List<Person> findPersonsInZipCodeRange(final int from, final int to);
}
