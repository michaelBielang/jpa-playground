package codemerger.transactional.hib.service.db;

import codemerger.transactional.hib.entities.Person;
import codemerger.transactional.hib.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Organisation: Codemerger Ldt.
 * Project: transactions
 * Package: codemerger.transactions.service
 * Date: 08.11.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@Service
public class TransactionalDatabaseService {

    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public void save(Person person) {
        personRepository.save(person);
    }

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public void deleteAllPersons() {
        personRepository.deleteAll();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveRequiresNew(Person person) {
        personRepository.save(person);
    }
}
