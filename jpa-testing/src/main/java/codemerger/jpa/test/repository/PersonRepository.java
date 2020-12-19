package codemerger.jpa.test.repository;

import codemerger.jpa.test.entities.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Organisation: Codemerger Ldt.
 * Project: jpa
 * Package: codemerger.jpa.test.repository
 * Date: 19.12.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */
public interface PersonRepository extends CrudRepository<Person, String> {

    @Override
    List<Person> findAll();
}
