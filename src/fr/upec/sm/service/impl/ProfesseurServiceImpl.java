package fr.upec.sm.service.impl;

import java.util.List;

import fr.upec.sm.dao.ProfesseurDao;
import fr.upec.sm.dao.impl.ProfesseurDaoImpl;
import fr.upec.sm.model.Professeur;
import fr.upec.sm.service.ProfesseurService;

public class ProfesseurServiceImpl implements ProfesseurService {

	private ProfesseurDao professeurDao;

	public ProfesseurServiceImpl() {
		this.professeurDao = new ProfesseurDaoImpl();
	}

	@Override
	public Professeur save(Professeur professeur) {
		return this.professeurDao.save(professeur);
	}

	@Override
	public List<Professeur> findAll() {
		return this.professeurDao.findAll();
	}

}
