package fr.upec.sm.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Professeur extends Personnel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String diplome;
	
	@JsonIgnore
	private List<Module> modules = new ArrayList<Module>();

	public Professeur() {
		super();
	}

	public Professeur(long id, String nom, String prenom, LocalDate dateNaissance, String diplome) {
		super(id, nom, prenom, dateNaissance);
		this.diplome = diplome;
	}

	public Professeur(String nom, String prenom, LocalDate dateNaissance, String diplome) {
		super(nom, prenom, dateNaissance);
		this.diplome = diplome;
	}

	public String getDiplome() {
		return diplome;
	}

	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public void addModule(Module module) {
		this.modules.add(module);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((diplome == null) ? 0 : diplome.hashCode());
		result = prime * result + ((modules == null) ? 0 : modules.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professeur other = (Professeur) obj;
		if (diplome == null) {
			if (other.diplome != null)
				return false;
		} else if (!diplome.equals(other.diplome))
			return false;
		if (modules == null) {
			if (other.modules != null)
				return false;
		} else if (!modules.equals(other.modules))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Professeur [id=" + this.getId() + ", nom=" + this.getNom() + ", prenom=" + this.getPrenom()
				+ ", dateNaissance=" + this.getDateNaissance() + ", diplome=" + diplome + "]";
	}

}
