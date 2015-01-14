package de.budisantoso.wcd.wh.persistence.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.budisantoso.wcd.wh.dto.ClubDTO;
import de.budisantoso.wcd.wh.dto.PersonDTO;
import de.budisantoso.wcd.wh.exception.PersonNotFoundException;
import de.budisantoso.wcd.wh.persistence.PersonService;
import de.budisantoso.wcd.wh.persistence.model.Club;
import de.budisantoso.wcd.wh.persistence.model.Person;
import de.budisantoso.wcd.wh.persistence.repos.PersonRepository;

@Service
public class PersonMongoDBService implements PersonService {

	@Autowired
	private PersonRepository repository;

	@Autowired
	private ClubMongoDBService clubService;

	private Set<ClubDTO> convertClubsToDTOs(Set<Club> clubs) {
		Set<ClubDTO> clubDTOs = new HashSet<ClubDTO>(clubs.size());
		for (Club club : clubs) {
			clubDTOs.add(clubService.convertToDTO(club));
		}
		return clubDTOs;
	}

	private List<PersonDTO> convertToDTOs(List<Person> persons) {
		List<PersonDTO> personDTOs = new ArrayList<PersonDTO>(persons.size());
		for (Person person : persons) {
			personDTOs.add(convertToDTO(person));
		}
		return personDTOs;
	}

	protected PersonDTO convertToDTO(Person person) {
		PersonDTO personDTO = new PersonDTO();
		personDTO.setId(person.getId());
		personDTO.setName(person.getName());
		personDTO.setClubs(convertClubsToDTOs(person.getClubs()));
		return personDTO;
	}

	private Set<Club> findClubsByDTOs(Set<ClubDTO> clubDTOs) {
		Set<Club> clubs = new HashSet<Club>(clubDTOs.size());
		for (ClubDTO clubDTO : clubDTOs) {
			clubs.add(clubService.findClubById(clubDTO.getId()));
		}
		return clubs;
	}

	@Override
	public PersonDTO findById(String id) {
		return convertToDTO(findPersonById(id));
	}

	private Person findPersonById(String id) {
		Person result = repository.findOne(id);

		if (null != result) {
			return result;
		} else {
			throw new PersonNotFoundException("id", id);
		}
	}

	@Override
	public PersonDTO findByName(String name) {
		return convertToDTO(findPersonByName(name));
	}

	protected Person findPersonByName(String name) {
		Person result = repository.findByName(name);

		if (null != result) {
			return result;
		} else {
			throw new PersonNotFoundException("name", name);
		}
	}

	@Override
	public PersonDTO create(PersonDTO personDTO) {
		return convertToDTO(createPerson(personDTO));
	}

	public Person createPerson(PersonDTO personDTO) {
		return repository.save(new Person(personDTO.getName(), findClubsByDTOs(personDTO.getClubs())));
	}

	@Override
	public PersonDTO delete(String id) {
		Person deleted = findPersonById(id);
		repository.delete(deleted);
		return convertToDTO(deleted);
	}

	@Override
	public PersonDTO update(PersonDTO personDTO) {
		Person updated = findPersonById(personDTO.getId());
		updated.update(personDTO.getName(), findClubsByDTOs(personDTO.getClubs()));
		updated = repository.save(updated);
		return convertToDTO(updated);
	}

	@Override
	public List<PersonDTO> findByClubIdOrName(String clubIdOrName) {
		return convertToDTOs(repository.findByClubs(clubService.findByIdOrName(clubIdOrName)));
	}

	@Override
	public PersonDTO joinClub(String personId, String clubId) {
		Person updated = findPersonById(personId);
		updated.joinClub(clubService.findClubById(clubId));
		updated = repository.save(updated);
		return convertToDTO(updated);
	}

	@Override
	public List<PersonDTO> findAll() {
		return convertToDTOs(repository.findAll());
	}

}
