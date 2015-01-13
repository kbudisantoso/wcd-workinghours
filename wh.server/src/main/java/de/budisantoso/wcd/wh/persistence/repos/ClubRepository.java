package de.budisantoso.wcd.wh.persistence.repos;

import org.springframework.data.mongodb.repository.MongoRepository;

import de.budisantoso.wcd.wh.persistence.model.Club;

public interface ClubRepository extends MongoRepository<Club, String> {

	Club findByName(String name);

}
