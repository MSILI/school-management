package fr.upec.sm.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.upec.sm.model.Assistant;
import fr.upec.sm.model.Module;
import fr.upec.sm.model.Personnel;
import fr.upec.sm.model.Professeur;
import fr.upec.sm.utils.DBUtils;

public class DBManagement {

	private static final String SQL_SAVE_PERSONNEL = "insert into personnels (nom, prenom, date_naissance) values (?, ?, ?)";
	private static final String SQL_SAVE_PROFFESSEUR = "insert into professeurs (id, diplome) values (?, ?)";
	private static final String SQL_SAVE_ASSISTANT = "insert into assistants (id, diplome, responsable_id) values (?, ?,?)";
	private static final String SQL_SAVE_MODULE = "insert into modules (titre, nb_heures_cours, nb_heures_td, nb_heures_tp, responsable_id) values (?, ?, ?, ?, ?)";
	private static final String SQL_FIND_ASSISTANTS = "select p.id, p.nom, p.prenom, p.date_naissance, a.diplome, a.responsable_id from personnels as p INNER JOIN assistants as a ON p.id = a.id";
	private static final String SQL_FIND_PROFESSEURS = "select p.id, p.nom, p.prenom, p.date_naissance, pf.diplome from personnels as p INNER JOIN professeurs as pf ON p.id = pf.id";
	private static final String SQL_FIND_MODULES = "select * from modules";
	private static final String SQL_FIND_PERSONNEL_BY_ID = "select * from personnels where id=?";
	private static final String SQL_FIND_PROFESSEUR_BY_ID = "select p.id, p.nom, p.prenom, p.date_naissance, pf.diplome from personnels as p INNER JOIN professeurs as pf ON p.id = pf.id where pf.id=?";
	private Connection databaseConnection;

	public DBManagement() {
		this.databaseConnection = DBUtils.getConnection();

	}

	public Professeur ajouterProfesseur(Professeur professeur) {
		ResultSet valeursAutoGenerees = null;
		Personnel personnel = this.ajouterPersonnel(professeur);
		try {
			PreparedStatement preparedStatement = databaseConnection.prepareStatement(SQL_SAVE_PROFFESSEUR,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setLong(1, personnel.getId());
			preparedStatement.setString(2, professeur.getDiplome());
			preparedStatement.executeUpdate();

			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if (valeursAutoGenerees.next()) {
				professeur.setId(valeursAutoGenerees.getInt(1));
			}
			valeursAutoGenerees.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return professeur;
	}

	public Assistant ajouterAssistant(Assistant assistant) {
		ResultSet valeursAutoGenerees = null;
		Personnel personnel = this.ajouterPersonnel(assistant);
		try {
			PreparedStatement preparedStatement = databaseConnection.prepareStatement(SQL_SAVE_ASSISTANT,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setLong(1, personnel.getId());
			preparedStatement.setString(2, assistant.getDiplome());
			preparedStatement.setLong(3, assistant.getResponsable().getId());
			preparedStatement.executeUpdate();

			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if (valeursAutoGenerees.next()) {
				assistant.setId(valeursAutoGenerees.getLong(1));
			}
			valeursAutoGenerees.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return assistant;
	}

	public Module ajouterModule(Module module) {
		try {
			PreparedStatement preparedStatement = databaseConnection.prepareStatement(SQL_SAVE_MODULE,
					Statement.RETURN_GENERATED_KEYS);
			module.setResponsable(chercherProfesseurParId(module.getResponsable().getId()));
			preparedStatement.setString(1, module.getTitre());
			preparedStatement.setInt(2, module.getNbHeuresCours());
			preparedStatement.setInt(3, module.getNbHeuresTd());
			preparedStatement.setInt(4, module.getNbHeuresTp());
			preparedStatement.setLong(5, module.getResponsable().getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return module;
	}

	public List<Assistant> listerAssistants() {
		List<Assistant> assistants = new ArrayList<>();
		try {
			Statement statement = databaseConnection.createStatement();
			ResultSet resultSet = statement.executeQuery(SQL_FIND_ASSISTANTS);
			while (resultSet.next()) {
				Personnel responsable = this.chercherPersonnelParId(resultSet.getLong("responsable_id"));
				assistants.add(new Assistant(resultSet.getLong("id"), resultSet.getString("nom"),
						resultSet.getString("prenom"), resultSet.getDate("date_naissance").toLocalDate(),
						resultSet.getString("diplome"), responsable));
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return assistants;
	}

	public List<Professeur> listerProfesseurs() {
		List<Professeur> professeurs = new ArrayList<>();
		try {
			Statement statement = databaseConnection.createStatement();
			ResultSet resultSet = statement.executeQuery(SQL_FIND_PROFESSEURS);
			while (resultSet.next()) {
				professeurs.add(new Professeur(resultSet.getLong("id"), resultSet.getString("nom"),
						resultSet.getString("prenom"), resultSet.getDate("date_naissance").toLocalDate(),
						resultSet.getString("diplome")));
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return professeurs;
	}

	public List<Module> listerModules() {
		List<Module> modules = new ArrayList<>();
		try {
			Statement statement = databaseConnection.createStatement();
			ResultSet resultSet = statement.executeQuery(SQL_FIND_MODULES);
			while (resultSet.next()) {
				Professeur responsable = chercherProfesseurParId(resultSet.getLong("responsable_id"));
				modules.add(new Module(resultSet.getString("titre"), resultSet.getInt("nb_heures_cours"),
						resultSet.getInt("nb_heures_td"), resultSet.getInt("nb_heures_tp"), responsable));
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modules;
	}

	private Personnel ajouterPersonnel(Personnel personnel) {
		ResultSet valeursAutoGenerees = null;
		try {
			PreparedStatement preparedStatement = databaseConnection.prepareStatement(SQL_SAVE_PERSONNEL,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, personnel.getNom());
			preparedStatement.setString(2, personnel.getPrenom());
			preparedStatement.setObject(3, Date.valueOf(personnel.getDateNaissance()));
			preparedStatement.executeUpdate();

			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if (valeursAutoGenerees.next()) {
				personnel.setId(valeursAutoGenerees.getLong(1));
			}
			valeursAutoGenerees.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return personnel;
	}

	private Personnel chercherPersonnelParId(Long id) {
		Personnel personnel = null;
		try {
			PreparedStatement preparedStatement = databaseConnection.prepareStatement(SQL_FIND_PERSONNEL_BY_ID);
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				personnel = new Personnel(resultSet.getLong("id"), resultSet.getString("nom"),
						resultSet.getString("prenom"), resultSet.getDate("date_naissance").toLocalDate());
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personnel;
	}

	private Professeur chercherProfesseurParId(Long id) {
		Professeur professeur = null;
		try {
			PreparedStatement preparedStatement = databaseConnection.prepareStatement(SQL_FIND_PROFESSEUR_BY_ID);
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				professeur = new Professeur(resultSet.getLong("id"), resultSet.getString("nom"),
						resultSet.getString("prenom"), resultSet.getDate("date_naissance").toLocalDate(),
						resultSet.getString("diplome"));
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return professeur;
	}
}
