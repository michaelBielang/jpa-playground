package codemerger.jpa.test.repository;

import codemerger.jpa.test.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Organisation: Codemerger Ldt.
 * Project: jpa
 * Package: codemerger.jpa.test.repository
 * Date: 19.12.2020
 *

 * @version: java version "14" 2020-03-17
 */
public interface PersonRepository extends CrudRepository<Person, String> {

    @Override
    List<Person> findAll();

    Person findByEmail(String email);

    Person findByFirstName(String firstName);

    Person findByLastName(String lastName);

    @Query(value = "select * from Person where EMAIL = :email and FIRST_NAME = :firstName and LAST_NAME = :lastName", nativeQuery = true)
    Person findByAllFields(@Param("email") String email, @Param("firstName") String firstName, @Param("lastName") String lastName);
}
