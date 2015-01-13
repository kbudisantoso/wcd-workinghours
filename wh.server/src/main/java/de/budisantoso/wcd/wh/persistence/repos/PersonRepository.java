package de.budisantoso.wcd.wh.persistence.repos;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import de.budisantoso.wcd.wh.persistence.model.Club;
import de.budisantoso.wcd.wh.persistence.model.Person;

public interface PersonRepository extends MongoRepository<Person, String> {

	Person findByName(String name);

	List<Person> findByClubs(Club club);

}
