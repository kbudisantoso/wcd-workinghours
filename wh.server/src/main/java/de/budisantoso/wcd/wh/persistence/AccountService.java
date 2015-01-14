package de.budisantoso.wcd.wh.persistence;

import java.util.List;

import de.budisantoso.wcd.wh.dto.AccountDTO;

public interface AccountService {

	AccountDTO create(AccountDTO accountEntry);

	AccountDTO delete(String id);

	AccountDTO findById(String id);

	AccountDTO findByUsername(String username);

	AccountDTO updatePassword(String id, String password);

	List<AccountDTO> findAll();

}
