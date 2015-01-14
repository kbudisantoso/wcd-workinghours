package de.budisantoso.wcd.wh.persistence;

import java.util.List;

import de.budisantoso.wcd.wh.dto.WorkingEventDTO;

public interface WorkingEventService {

	WorkingEventDTO create(WorkingEventDTO workingEventEntry);

	WorkingEventDTO delete(String id);

	WorkingEventDTO findById(String id);

	List<WorkingEventDTO> findByClubId(String clubId);

	WorkingEventDTO update(String id, WorkingEventDTO workingEventEntry);

	List<WorkingEventDTO> findAll();

}
