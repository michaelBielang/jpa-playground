package util;

import codemerger.queries.demo.entities.Person;
import org.springframework.data.repository.CrudRepository;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

/**
 * Organisation: Codemerger Ldt.
 * Project: jpa-practising
 * Package: util
 * Date: 08.01.2021
 *

 * @version: java version "14" 2020-03-17
 */
public class InsertTestData {


    public static void insertTargetPerson(CrudRepository<Person, Long> crudRepository, final int targetZip) {
        final String firstName = getRandomName();
        final String lastName = getRandomName();

        final Person person = new Person(firstName, lastName, targetZip);

        crudRepository.save(person);
    }

    public static void insertRandomPerson(CrudRepository<Person, Long> crudRepository) {
        final int zipCode = 5000;
        final String firstName = getRandomName();
        final String lastName = getRandomName();

        final Person person = new Person(firstName, lastName, zipCode);

        crudRepository.save(person);
    }

    public static String getRandomName() {
        return randomAlphabetic(10);
    }
}
