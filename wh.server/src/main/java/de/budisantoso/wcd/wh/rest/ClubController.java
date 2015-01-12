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

import de.budisantoso.wcd.wh.dto.ClubDTO;
import de.budisantoso.wcd.wh.persistence.ClubService;
import de.budisantoso.wcd.wh.persistence.exception.ClubNotFoundException;

@RestController
@RequestMapping("/wh/club")
public class ClubController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClubController.class);

	@Autowired
	private ClubService service;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	ClubDTO create(@RequestBody @Valid ClubDTO clubEntry) {
		return service.create(clubEntry);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	ClubDTO delete(@PathVariable("id") String id) {
		return service.delete(id);
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public List<ClubDTO> findAll() {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Find all clubs.");
		}
		return service.findAll();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	ClubDTO findById(@PathVariable("id") String id) {
		return service.findById(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ClubDTO findByName(@RequestParam(value = "name") String name) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Find club with name '{}'.", name);
		}
		return service.findByName(name);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	ClubDTO update(@PathVariable("id") String id, @RequestBody @Valid ClubDTO clubEntry) {
		clubEntry.setId(id);
		return service.update(clubEntry);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@SuppressWarnings("PMD")
	public void handleTodoNotFound(ClubNotFoundException ex) {
	}

}
