package fr.upec.sm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.upec.sm.dao.PersonnelDao;
import fr.upec.sm.dao.ProfesseurDao;
import fr.upec.sm.model.Personnel;
import fr.upec.sm.model.Professeur;
import fr.upec.sm.utils.DBUtils;

public class ProfesseurDaoImpl implements ProfesseurDao {

	private static final String SQL_SAVE_PROFFESSEUR = "insert into professeurs (id, diplome) values (?, ?)";
	private static final String SQL_FIND_PROFESSEURS = "select p.id, p.nom, p.prenom, p.date_naissance, pf.diplome from personnels as p INNER JOIN professeurs as pf ON p.id = pf.id";
	private static final String SQL_FIND_PROFESSEUR_BY_ID = "select p.id, p.nom, p.prenom, p.date_naissance, pf.diplome from personnels as p INNER JOIN professeurs as pf ON p.id = pf.id where pf.id=?";

	private Connection connection;
	private PersonnelDao personnelDao;

	public ProfesseurDaoImpl() {
		this.connection = DBUtils.getConnection();
		this.personnelDao = new PersonnelDaoImpl();
	}

	@Override
	public Professeur save(Professeur professeur) {
		ResultSet valeursAutoGenerees = null;
		Personnel personnel = this.personnelDao.save(professeur);
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_PROFFESSEUR,
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
			throw new RuntimeException("Erreur au niveau de la base de données");
		}
		return professeur;
	}

	@Override
	public List<Professeur> findAll() {
		List<Professeur> professeurs = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SQL_FIND_PROFESSEURS);
			while (resultSet.next()) {
				professeurs.add(new Professeur(resultSet.getLong("id"), resultSet.getString("nom"),
						resultSet.getString("prenom"), resultSet.getDate("date_naissance").toLocalDate(),
						resultSet.getString("diplome")));
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erreur au niveau de la base de données");
		}
		return professeurs;
	}

	@Override
	public Professeur findById(Long id) {
		Professeur professeur = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_PROFESSEUR_BY_ID);
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
			throw new RuntimeException("Erreur au niveau de la base de données");
		}
		return professeur;
	}

}
