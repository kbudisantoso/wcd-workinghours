package de.budisantoso.wcd.wh.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.budisantoso.wcd.wh.dto.WorkingEventDTO;
import de.budisantoso.wcd.wh.exception.WorkingEventNotFoundException;
import de.budisantoso.wcd.wh.persistence.WorkingEventService;
import de.budisantoso.wcd.wh.persistence.model.Club;
import de.budisantoso.wcd.wh.persistence.model.WorkingEvent;
import de.budisantoso.wcd.wh.persistence.repos.WorkingEventRepository;

@Service
public class WorkingEventMongoDBService implements WorkingEventService {

	@Autowired
	private WorkingEventRepository repository;

	@Autowired
	private ClubMongoDBService clubService;

	@Override
	public WorkingEventDTO create(WorkingEventDTO workingEventEntry) {
		return convertToDTO(repository.save(new WorkingEvent(workingEventEntry)));
	}

	@Override
	public WorkingEventDTO delete(String id) {
		WorkingEvent deleted = findWorkingEventById(id);
		repository.delete(deleted);
		return convertToDTO(deleted);
	}

	@Override
	public WorkingEventDTO findById(String id) {
		return convertToDTO(findWorkingEventById(id));
	}

	private WorkingEventDTO convertToDTO(WorkingEvent workingEvent) {
		WorkingEventDTO workingEventDTO = new WorkingEventDTO();
		workingEventDTO.setId(workingEvent.getId());
		workingEventDTO.setName(workingEvent.getName());
		workingEventDTO.setClub(clubService.convertToDTO(workingEvent.getClub()));
		workingEventDTO.setDateAndTime(workingEvent.getDateAndTime());
		return workingEventDTO;
	}

	private List<WorkingEventDTO> convertToDTOs(List<WorkingEvent> workingEvents) {
		List<WorkingEventDTO> workingEventDTOs = new ArrayList<WorkingEventDTO>(workingEvents.size());
		for (WorkingEvent workingEvent : workingEvents) {
			workingEventDTOs.add(convertToDTO(workingEvent));
		}
		return workingEventDTOs;
	}

	private WorkingEvent findWorkingEventById(String id) {
		WorkingEvent result = repository.findOne(id);

		if (null != result) {
			return result;
		} else {
			throw new WorkingEventNotFoundException("id", id);
		}
	}

	@Override
	public List<WorkingEventDTO> findByClubId(String clubId) {
		return convertToDTOs(repository.findByClubId(clubId));
	}

	@Override
	public WorkingEventDTO update(String id, WorkingEventDTO workingEvent) {
		WorkingEvent updated = findWorkingEventById(id);
		updated.update(workingEvent.getName(), new Club(workingEvent.getClub()), workingEvent.getDateAndTime());
		updated = repository.save(updated);
		return convertToDTO(updated);
	}

	@Override
	public List<WorkingEventDTO> findAll() {
		return convertToDTOs(repository.findAll());
	}

}
