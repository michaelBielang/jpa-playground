package codemerger.simpel.sequence.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
@Entity(name = "SEQUENTIAL_PERSON")
public class SequentialPerson {

    @Id
    private Long id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;

    public SequentialPerson(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
