package de.budisantoso.wcd.wh.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.budisantoso.wcd.wh.dto.ClubDTO;
import de.budisantoso.wcd.wh.persistence.ClubService;
import de.budisantoso.wcd.wh.persistence.exception.ClubNotFoundException;
import de.budisantoso.wcd.wh.persistence.model.Club;
import de.budisantoso.wcd.wh.persistence.repos.ClubRepository;

@Service
public class ClubMongoDBService implements ClubService {

	@Autowired
	private ClubRepository repository;

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

	private ClubDTO convertToDTO(Club club) {
		ClubDTO clubDTO = new ClubDTO();
		clubDTO.setId(club.getId());
		clubDTO.setName(club.getName());

		return clubDTO;
	}

	@Override
	public ClubDTO findById(String id) {
		return convertToDTO(findClubById(id));
	}

	private Club findClubById(String id) {
		Club result = repository.findOne(id);

		if (null != result) {
			return result;
		} else {
			throw new ClubNotFoundException("id", id);
		}
	}

	@Override
	public ClubDTO findByName(String name) {
		Club result = repository.findByName(name);

		if (null != result) {
			return convertToDTO(result);
		} else {
			throw new ClubNotFoundException("name", name);
		}
	}

	@Override
	public ClubDTO create(ClubDTO club) {
		return convertToDTO(repository.save(new Club(club.getName())));
	}

	@Override
	public ClubDTO delete(String id) {
		Club deleted = findClubById(id);
		repository.delete(deleted);
		return convertToDTO(deleted);
	}

	@Override
	public ClubDTO update(ClubDTO club) {
		Club updated = findClubById(club.getId());
		updated.update(club.getName());
		updated = repository.save(updated);
		return convertToDTO(updated);
	}
}
