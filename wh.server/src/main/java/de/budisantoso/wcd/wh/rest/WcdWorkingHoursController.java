package de.budisantoso.wcd.wh.rest;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.budisantoso.wcd.wh.model.Status;

@RestController
public class WcdWorkingHoursController {

	private static final Logger LOGGER = LoggerFactory.getLogger(WcdWorkingHoursController.class);

	private final AtomicLong counter = new AtomicLong();

	@RequestMapping(method = RequestMethod.GET, value = "/{status}")
	public Status status(@RequestParam(value = "message", defaultValue = "green") String message) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Processing request for status with message '{}'.", message);
		}
		return new Status(counter.incrementAndGet(), message);
	}

}
