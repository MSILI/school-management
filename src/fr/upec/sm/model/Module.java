package fr.upec.sm.model;

import java.io.Serializable;

public class Module implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String titre;
	private int nbHeuresCours;
	private int nbHeuresTd;
	private int nbHeuresTp;
	private Professeur responsable;

	public Module() {
	}

	public Module(String titre, int nbHeuresCours, int nbHeuresTd, int nbHeuresTp, Professeur responsable) {
		this.titre = titre;
		this.nbHeuresCours = nbHeuresCours;
		this.nbHeuresTd = nbHeuresTd;
		this.nbHeuresTp = nbHeuresTp;
		this.responsable = responsable;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getNbHeuresCours() {
		return nbHeuresCours;
	}

	public void setNbHeuresCours(int nbHeuresCours) {
		this.nbHeuresCours = nbHeuresCours;
	}

	public int getNbHeuresTd() {
		return nbHeuresTd;
	}

	public void setNbHeuresTd(int nbHeuresTd) {
		this.nbHeuresTd = nbHeuresTd;
	}

	public int getNbHeuresTp() {
		return nbHeuresTp;
	}

	public void setNbHeuresTp(int nbHeuresTp) {
		this.nbHeuresTp = nbHeuresTp;
	}

	public Professeur getResponsable() {
		return responsable;
	}

	public void setResponsable(Professeur responsable) {
		this.responsable = responsable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((titre == null) ? 0 : titre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Module other = (Module) obj;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Module [titre=" + titre + ", nbHeuresCours=" + nbHeuresCours + ", nbHeuresTd=" + nbHeuresTd
				+ ", nbHeuresTp=" + nbHeuresTp + ", responsable=" + responsable + "]";
	}

}
