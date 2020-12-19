package codemerger.jpa.test.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Organisation: Codemerger Ldt.
 * Project: jpa
 * Package: codemerger.jpa.test.entities
 * Date: 19.12.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @NaturalId
    private String email;

    private String firstName;
    private String lastName;
}
