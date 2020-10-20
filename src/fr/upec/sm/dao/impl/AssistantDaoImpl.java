package fr.upec.sm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.upec.sm.dao.AssistantDao;
import fr.upec.sm.dao.PersonnelDao;
import fr.upec.sm.model.Assistant;
import fr.upec.sm.model.Personnel;
import fr.upec.sm.utils.DBUtils;

public class AssistantDaoImpl implements AssistantDao {

	private static final String SQL_SAVE_ASSISTANT = "insert into assistants (id, diplome, responsable_id) values (?, ?,?)";
	private static final String SQL_FIND_ASSISTANTS = "select p.id, p.nom, p.prenom, p.date_naissance, a.diplome, a.responsable_id from personnels as p INNER JOIN assistants as a ON p.id = a.id";

	private Connection connection;
	private PersonnelDao personnelDao;

	public AssistantDaoImpl() {
		this.connection = DBUtils.getConnection();
		this.personnelDao = new PersonnelDaoImpl();
	}

	@Override
	public Assistant save(Assistant assistant) {
		ResultSet valeursAutoGenerees = null;
		Personnel personnel = this.personnelDao.save(assistant);
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_ASSISTANT,
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
			throw new RuntimeException("Erreur au niveau de la base de données");
		}
		return assistant;
	}

	@Override
	public List<Assistant> findAll() {
		List<Assistant> assistants = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SQL_FIND_ASSISTANTS);
			while (resultSet.next()) {
				Personnel responsable = this.personnelDao.findPersonnelById(resultSet.getLong("responsable_id"));
				assistants.add(new Assistant(resultSet.getLong("id"), resultSet.getString("nom"),
						resultSet.getString("prenom"), resultSet.getDate("date_naissance").toLocalDate(),
						resultSet.getString("diplome"), responsable));
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erreur au niveau de la base de données");
		}
		return assistants;
	}

}
