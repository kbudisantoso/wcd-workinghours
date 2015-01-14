package de.budisantoso.wcd.wh.rest;

import java.util.List;

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

import de.budisantoso.wcd.wh.dto.PersonDTO;
import de.budisantoso.wcd.wh.exception.ClubNotFoundException;
import de.budisantoso.wcd.wh.exception.PersonNotFoundException;
import de.budisantoso.wcd.wh.persistence.PersonService;

@RestController
@RequestMapping("/wh/person")
public class PersonController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonService service;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public PersonDTO create(@RequestBody @Valid PersonDTO personEntry) {
		return service.create(personEntry);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public PersonDTO delete(@PathVariable("id") String id) {
		return service.delete(id);
	}

	@RequestMapping(value = "findByClub", method = RequestMethod.GET)
	public List<PersonDTO> findByClubIdOrName(@RequestParam(value = "idOrName") String idOrName) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Find all persons that belong to club '{}'.", idOrName);
		}
		return service.findByClubIdOrName(idOrName);
	}

	@RequestMapping(value = "{personId}/joinClub/{clubId}", method = RequestMethod.PUT)
	public PersonDTO joinClub(@PathVariable("personId") String personId, @PathVariable(value = "clubId") String clubId) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Add person with id '{}' to club with id '{}'.", personId, clubId);
		}
		return service.joinClub(personId, clubId);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public PersonDTO findById(@PathVariable("id") String id) {
		return service.findById(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public PersonDTO findByName(@RequestParam(value = "name") String name) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Find person with name '{}'.", name);
		}
		return service.findByName(name);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public PersonDTO update(@PathVariable("id") String id, @RequestBody @Valid PersonDTO personEntry) {
		personEntry.setId(id);
		return service.update(personEntry);
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public List<PersonDTO> findAll() {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Find all persons.");
		}
		return service.findAll();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@SuppressWarnings("PMD")
	public void handlePersonNotFound(PersonNotFoundException ex) {
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	@SuppressWarnings("PMD")
	public void handleClubNotFound(ClubNotFoundException ex) {
	}

}
