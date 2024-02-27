package codemerger.queries.demo.query.annotation;

import codemerger.queries.demo.entities.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Organisation: Codemerger Ldt.
 * Project: jpa-practising
 * Package: codemerger.queries.demo.queryannotation
 * Date: 06.01.2021
 *

 * @version: java version "14" 2020-03-17
 */

@Repository
public interface QueryAnnotationRepository extends PagingAndSortingRepository<Person, Long> {

    //native query
    @Query(value = "select * from PERSON where POST_CODE between :zipFrom and :zipTo ORDER BY POST_CODE", nativeQuery = true)
    List<Person> findPersonsInZipCodeRangeWithNativeQuery(final int zipFrom, final int zipTo);

    //JPQL query with Sort
    @Query(value = "SELECT p FROM PERSON p WHERE p.postCode BETWEEN :zipFrom AND :zipTo")
    List<Person> findPersonsInZipCodeRangeWithJPQ(final int zipFrom, final int zipTo, Sort sort);

    //JPQL query with Pageable
    @Query(value = "SELECT p FROM PERSON p WHERE p.postCode BETWEEN :zipFrom AND :zipTo")
    Page<Person> findPersonsInZipRangeWithPagination(final int zipFrom, final int zipTo, final Pageable pageable);

    //JPQL query with a Collection of names that returns a list with persons having the same firstName as names
    @Query(value = "SELECT p FROM PERSON p WHERE p.firstName IN :names")
    List<Person> findPersonByNameList(@Param("names") Collection<String> names);
}
