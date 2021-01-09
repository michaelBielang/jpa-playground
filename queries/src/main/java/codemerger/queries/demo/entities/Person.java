package codemerger.queries.demo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Organisation: Codemerger Ldt.
 * Project: sequence
 * Package: codemerger.simpel.sequence.dto
 * Date: 13.12.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@Data
@NoArgsConstructor
@Entity(name = "PERSON")
public class Person {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private int postCode;

    // we assume that by deleting an department we also get rid of all employees
    @ManyToOne(cascade = CascadeType.ALL)
    private Department department;

    public Person(String firstName, String lastName, int postCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.postCode = postCode;
    }
}
