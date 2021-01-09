## Status

[![Project Status: WIP – Initial development is in progress](https://www.repostatus.org/badges/latest/wip.svg)](https://www.repostatus.org/#wip)

## Module

This module presents different ways of how to interact with the database.

## Features

* [x] Spring Boot and Java 15
* [x] JPA Criteria API
* [x] QueryDSL
* [x] HQL
* [x] Native Queries
* [x] Jooq
* [x] H2 in memory

## Execute

### Main program

Run the ```main``` method in the DemoApplication class.

# Knowledge

## JPA criteria queries

The JPA Criteria API provides an alternative way for defining JPA queries, which is mainly useful for building dynamic
queries whose exact structure is only known at runtime.

## @Query

Check ``QueryAnnotationRepository.java``

### Native

    @Query(value = "select * from PERSON where POST_CODE between :zipFrom and :zipTo ORDER BY POST_CODE", nativeQuery = true)
    List<Person> findPersonsInZipCodeRangeWithNativeQuery(final int zipFrom, final int zipTo);

### JPQL

    @Query(value = "SELECT p FROM PERSON p WHERE p.postCode BETWEEN :zipFrom AND :zipTo")
    List<Person> findPersonsInZipCodeRangeWithJPQ(final int zipFrom, final int zipTo, Sort sort);

### Additional

#### Pagination

[BEAM to documentation](https://docs.spring.io/spring-data/rest/docs/2.0.0.M1/reference/html/paging-chapter.html)

Pagination allows to just return a subset of a whole result in a ``Page``. This is useful when navigating through
several pages of data on a web page.

Another advantage of pagination is that the amount of data sent from server to client is minimized.

    public interface Pageable {
    
    // number of the current page  
    int getPageNumber();

    int getPageSize();

    Sort getSort();
    
    // ... more methods
    }

#### Sort

You can choose between various Sort functions and give this as a parameter.

[click]: https://docs.spring.io/spring-data/rest/docs/2.0.0.M1/reference/html/paging-chapter.html
