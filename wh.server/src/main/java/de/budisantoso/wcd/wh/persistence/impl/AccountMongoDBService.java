package de.budisantoso.wcd.wh.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.budisantoso.wcd.wh.dto.AccountDTO;
import de.budisantoso.wcd.wh.exception.AccountNotFoundException;
import de.budisantoso.wcd.wh.exception.PersonNotFoundException;
import de.budisantoso.wcd.wh.persistence.AccountService;
import de.budisantoso.wcd.wh.persistence.model.Account;
import de.budisantoso.wcd.wh.persistence.model.Person;
import de.budisantoso.wcd.wh.persistence.repos.AccountRepository;

@Service
public class AccountMongoDBService implements AccountService {

	@Autowired
	private AccountRepository repository;

	@Autowired
	private PersonMongoDBService personService;

	@Override
	public AccountDTO create(AccountDTO accountEntry) {
		Person person;
		try {
			person = personService.findPersonByName(accountEntry.getName());
		} catch (PersonNotFoundException e) {
			person = personService.createPerson(accountEntry.getPerson());
		}

		return convertToDTO(repository.save(new Account(accountEntry.getUsername(), accountEntry.getPassword())
				.assignPerson(person)));
	}

	@Override
	public AccountDTO delete(String id) {
		Account deleted = findAccountById(id);
		repository.delete(deleted);
		return convertToDTO(deleted);
	}

	@Override
	public AccountDTO findById(String id) {
		return convertToDTO(findAccountById(id));
	}

	private AccountDTO convertToDTO(Account account) {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId(account.getId());
		accountDTO.setUserame(account.getUsername());
		accountDTO.setPerson(personService.convertToDTO(account.getPerson()));
		return accountDTO;
	}

	private List<AccountDTO> convertToDTOs(List<Account> accounts) {
		List<AccountDTO> accountDTOs = new ArrayList<AccountDTO>(accounts.size());
		for (Account account : accounts) {
			accountDTOs.add(convertToDTO(account));
		}
		return accountDTOs;
	}

	private Account findAccountById(String id) {
		Account result = repository.findOne(id);

		if (null != result) {
			return result;
		} else {
			throw new AccountNotFoundException("id", id);
		}
	}

	@Override
	public AccountDTO findByUsername(String username) {
		Account result = repository.findByUsername(username);

		if (null != result) {
			return convertToDTO(result);
		} else {
			throw new AccountNotFoundException("username", username);
		}
	}

	@Override
	public AccountDTO updatePassword(String id, String password) {
		Account updated = findAccountById(id);
		updated.updatePassword(password);
		updated = repository.save(updated);
		return convertToDTO(updated);
	}

	@Override
	public List<AccountDTO> findAll() {
		return convertToDTOs(repository.findAll());
	}

}
