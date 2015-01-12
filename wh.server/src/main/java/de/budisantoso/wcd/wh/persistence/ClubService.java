package de.budisantoso.wcd.wh.persistence;

import java.util.List;

import de.budisantoso.wcd.wh.dto.ClubDTO;

public interface ClubService {

	public abstract List<ClubDTO> findAll();

	public abstract ClubDTO findById(String id);

	public abstract ClubDTO findByName(String name);

	public abstract ClubDTO create(ClubDTO club);

	public abstract ClubDTO delete(String id);

	public abstract ClubDTO update(ClubDTO club);

}