package fr.upec.sm.service;

import java.util.List;

import fr.upec.sm.model.Professeur;

public interface ProfesseurService {

	Professeur save(Professeur professeur);

	List<Professeur> findAll();

}
