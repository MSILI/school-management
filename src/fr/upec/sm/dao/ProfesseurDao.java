package fr.upec.sm.dao;

import java.util.List;

import fr.upec.sm.model.Professeur;

public interface ProfesseurDao {

	Professeur save(Professeur professeur);
	
	List<Professeur> findAll();
	
	Professeur findById(Long id);
}
