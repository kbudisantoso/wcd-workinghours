package de.budisantoso.wcd.wh.persistence.repos;

import org.springframework.data.mongodb.repository.MongoRepository;

import de.budisantoso.wcd.wh.persistence.model.Account;

public interface AccountRepository extends MongoRepository<Account, String> {

	Account findByUsername(String username);

}
