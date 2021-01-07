package codemerger.queries.demo.query;

import codemerger.queries.demo.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Organisation: Codemerger Ldt.
 * Project: jpa-practising
 * Package: codemerger.queries.demo.query
 * Date: 07.01.2021
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@Service
public class TypedQueryDemo {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public List<Person> getPersons(final int zipFrom, final int zipTo) {
        return null;
    }
}
