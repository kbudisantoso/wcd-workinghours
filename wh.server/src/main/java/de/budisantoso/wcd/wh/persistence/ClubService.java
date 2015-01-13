package de.budisantoso.wcd.wh.persistence;

import java.util.List;

import de.budisantoso.wcd.wh.dto.ClubDTO;

public interface ClubService {

	List<ClubDTO> findAll();

	ClubDTO findById(String id);

	ClubDTO findByName(String name);

	ClubDTO create(ClubDTO clubDTO);

	ClubDTO delete(String id);

	ClubDTO update(ClubDTO clubDTO);

}