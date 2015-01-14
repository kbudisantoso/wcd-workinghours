package de.budisantoso.wcd.wh.rest;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.budisantoso.wcd.wh.dto.AccountDTO;
import de.budisantoso.wcd.wh.persistence.AccountService;

@RestController
@RequestMapping("/wh/account")
public class AccountController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService service;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public AccountDTO create(@RequestBody @Valid AccountDTO accountEntry) {
		return service.create(accountEntry);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public AccountDTO delete(@PathVariable("id") String id) {
		return service.delete(id);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public AccountDTO findById(@PathVariable("id") String id) {
		return service.findById(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public AccountDTO findByUsername(@RequestParam(value = "username") String username) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Find account with username '{}'.", username);
		}
		return service.findByUsername(username);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public AccountDTO update(@PathVariable("id") String id, @RequestBody @Valid String newPassword) {
		return service.updatePassword(id, newPassword);
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public List<AccountDTO> findAll() {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Find all accounts.");
		}
		return service.findAll();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@SuppressWarnings("PMD")
	public void handleAccountNotFound(AccountNotFoundException ex) {
	}

}
