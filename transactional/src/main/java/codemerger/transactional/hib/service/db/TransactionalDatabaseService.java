package codemerger.transactional.hib.service.db;

import codemerger.transactional.hib.dto.Person;
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
    private PersonRepository seqPersonRepository;

    @Transactional
    public void save(Person sequentialPerson) {
        seqPersonRepository.save(sequentialPerson);
    }

    public List<Person> getPersons() {
        return seqPersonRepository.findAll();
    }

    public void deleteAllPersons() {
        seqPersonRepository.deleteAll();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveRequiresNew(Person sequentialPerson) {
        seqPersonRepository.save(sequentialPerson);
    }
}