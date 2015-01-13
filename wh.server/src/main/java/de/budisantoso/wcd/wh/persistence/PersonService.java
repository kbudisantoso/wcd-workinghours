package de.budisantoso.wcd.wh.persistence;

import java.util.List;

import de.budisantoso.wcd.wh.dto.PersonDTO;

public interface PersonService {

	PersonDTO findById(String id);

	PersonDTO findByName(String name);

	PersonDTO create(PersonDTO personDTO);

	PersonDTO delete(String id);

	PersonDTO update(PersonDTO personDTO);

	List<PersonDTO> findByClubIdOrName(String clubIdOrName);

	PersonDTO joinClub(String personId, String clubId);
}