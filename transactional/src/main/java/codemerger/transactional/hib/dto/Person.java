package codemerger.transactional.hib.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Organisation: Codemerger Ldt.
 * Project: sequence
 * Package: codemerger.transactional.hib.dto
 * Date: 13.12.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@Data
@NoArgsConstructor
@Entity
public class Person {

    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
