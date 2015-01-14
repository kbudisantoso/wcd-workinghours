package de.budisantoso.wcd.wh.persistence.repos;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import de.budisantoso.wcd.wh.persistence.model.WorkingEvent;

public interface WorkingEventRepository extends MongoRepository<WorkingEvent, String> {

	List<WorkingEvent> findByClubId(String id);

}
