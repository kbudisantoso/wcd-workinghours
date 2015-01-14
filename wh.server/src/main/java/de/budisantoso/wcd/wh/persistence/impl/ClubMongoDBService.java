package de.budisantoso.wcd.wh.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.budisantoso.wcd.wh.dto.ClubDTO;
import de.budisantoso.wcd.wh.dto.PersonDTO;
import de.budisantoso.wcd.wh.exception.ClubNotFoundException;
import de.budisantoso.wcd.wh.exception.ReferencedClubDeleteException;
import de.budisantoso.wcd.wh.persistence.ClubService;
import de.budisantoso.wcd.wh.persistence.model.Club;
import de.budisantoso.wcd.wh.persistence.repos.ClubRepository;

@Service
public class ClubMongoDBService implements ClubService {

	@Autowired
	private ClubRepository repository;

	@Autowired
	private WorkingEventMongoDBService workingEventService;

	@Autowired
	private PersonMongoDBService personService;

	@Override
	public List<ClubDTO> findAll() {
		return convertToDTOs(repository.findAll());
	}

	private List<ClubDTO> convertToDTOs(List<Club> clubs) {
		List<ClubDTO> clubDTOs = new ArrayList<ClubDTO>(clubs.size());
		for (Club club : clubs) {
			clubDTOs.add(convertToDTO(club));
		}
		return clubDTOs;
	}

	protected ClubDTO convertToDTO(Club club) {
		ClubDTO clubDTO = new ClubDTO();
		clubDTO.setId(club.getId());
		clubDTO.setName(club.getName());
		return clubDTO;
	}

	@Override
	public ClubDTO findById(String id) {
		return convertToDTO(findClubById(id));
	}

	protected Club findClubById(String id) {
		Club result = repository.findOne(id);

		if (null != result) {
			return result;
		} else {
			throw new ClubNotFoundException("id", id);
		}
	}

	@Override
	public ClubDTO findByName(String name) {
		return convertToDTO(findClubByName(name));
	}

	protected Club findClubByName(String name) {
		Club result = repository.findByName(name);

		if (null != result) {
			return result;
		} else {
			throw new ClubNotFoundException("name", name);
		}
	}

	protected Club findByIdOrName(String idOrName) {
		Club result = repository.findOne(idOrName);
		if (null != result) {
			return result;
		} else {
			result = repository.findByName(idOrName);
			if (null != result) {
				return result;
			} else {
				throw new ClubNotFoundException("idOrName", idOrName);
			}
		}
	}

	@Override
	public ClubDTO create(ClubDTO club) {
		return convertToDTO(repository.save(new Club(club)));
	}

	@Override
	public ClubDTO delete(String id) {
		if (workingEventService.findByClubId(id).isEmpty()) {
			Club deleted = findClubById(id);
			removeClubFromPersons(deleted);
			repository.delete(deleted);
			return convertToDTO(deleted);
		} else {
			throw new ReferencedClubDeleteException("id", id);
		}
	}

	private void removeClubFromPersons(Club deleted) {
		List<PersonDTO> persons = personService.findByClubIdOrName(deleted.getId());
		for (PersonDTO person : persons) {
			person.getClubs().remove(deleted);
			personService.update(person);
		}
	}

	@Override
	public ClubDTO update(ClubDTO clubDTO) {
		Club updated = findClubById(clubDTO.getId());
		updated.update(clubDTO.getName());
		updated = repository.save(updated);
		return convertToDTO(updated);
	}

}
