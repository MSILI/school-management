package fr.upec.sm.model;

import java.time.LocalDate;

public class Assistant extends Personnel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String diplome;
	private Personnel responsable;

	public Assistant() {
		super();
	}

	public Assistant(long id, String nom, String prenom, LocalDate dateNaissance, String diplome,
			Personnel responsable) {
		super(id, nom, prenom, dateNaissance);
		this.diplome = diplome;
		this.responsable = responsable;
	}

	public String getDiplome() {
		return diplome;
	}

	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}

	public Personnel getResponsable() {
		return responsable;
	}

	public void setResponsable(Personnel responsable) {
		this.responsable = responsable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((diplome == null) ? 0 : diplome.hashCode());
		result = prime * result + ((responsable == null) ? 0 : responsable.hashCode());
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
		Assistant other = (Assistant) obj;
		if (diplome == null) {
			if (other.diplome != null)
				return false;
		} else if (!diplome.equals(other.diplome))
			return false;
		if (responsable == null) {
			if (other.responsable != null)
				return false;
		} else if (!responsable.equals(other.responsable))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Assistant [id=" + this.getId() + ", nom=" + this.getNom() + ", prenom=" + this.getPrenom()
				+ ", diplome=" + diplome + ", responsable=" + responsable + "]";
	}

}
