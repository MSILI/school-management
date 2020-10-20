package fr.upec.sm.dao;

import fr.upec.sm.model.Personnel;

public interface PersonnelDao {
	
	Personnel save(Personnel personnel);
	
	Personnel findPersonnelById(Long id);
	
}
