package codemerger.queries.demo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * Organisation: Codemerger Ldt.
 * Project: jpa-practising
 * Package: codemerger.queries.demo.entities
 * Date: 08.01.2021
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@Entity(name = "DEPARTMENT")
@Data
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue
    private long id;
    private String departmentName;
    private String departmentCity;

    // alternatively you can use https://stackoverflow.com/questions/36803306/should-jointable-be-specified-in-both-sides-of-a-manytomany-relationship
    @OneToMany(mappedBy = "department")
    @Column(name = "OFFICER")
    private Set<Person> officers;
}
