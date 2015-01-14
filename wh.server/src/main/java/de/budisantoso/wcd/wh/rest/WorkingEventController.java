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

import de.budisantoso.wcd.wh.dto.WorkingEventDTO;
import de.budisantoso.wcd.wh.exception.WorkingEventNotFoundException;
import de.budisantoso.wcd.wh.persistence.WorkingEventService;

@RestController
@RequestMapping("/wh/workingevent")
public class WorkingEventController {

	private static final Logger LOGGER = LoggerFactory.getLogger(WorkingEventController.class);

	@Autowired
	private WorkingEventService service;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public WorkingEventDTO create(@RequestBody @Valid WorkingEventDTO workingEventEntry) {
		return service.create(workingEventEntry);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public WorkingEventDTO delete(@PathVariable("id") String id) {
		return service.delete(id);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public WorkingEventDTO findById(@PathVariable("id") String id) {
		return service.findById(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<WorkingEventDTO> findByClub(@RequestParam(value = "clubId") String clubId) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Find WorkingEvent with clubId '{}'.", clubId);
		}
		return service.findByClubId(clubId);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public WorkingEventDTO update(@PathVariable("id") String id, @RequestBody @Valid WorkingEventDTO workingEventEntry) {
		return service.update(id, workingEventEntry);
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public List<WorkingEventDTO> findAll() {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Find all WorkingEvents.");
		}
		return service.findAll();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@SuppressWarnings("PMD")
	public void handleWorkingEventNotFound(WorkingEventNotFoundException ex) {
	}

}
