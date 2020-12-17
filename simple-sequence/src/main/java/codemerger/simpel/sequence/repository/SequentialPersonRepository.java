package codemerger.simpel.sequence.repository;

import codemerger.simpel.sequence.entities.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Organisation: Codemerger Ldt.
 * Project: sequence
 * Package: codemerger.simpel.sequence.repository
 * Date: 13.12.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */
public interface SequentialPersonRepository extends CrudRepository<Person, Long> {

    @Override
    List<Person> findAll();
}
