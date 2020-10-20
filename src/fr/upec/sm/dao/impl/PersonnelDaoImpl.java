package fr.upec.sm.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.upec.sm.dao.PersonnelDao;
import fr.upec.sm.model.Personnel;
import fr.upec.sm.utils.DBUtils;

public class PersonnelDaoImpl implements PersonnelDao {

	private static final String SQL_SAVE_PERSONNEL = "insert into personnels (nom, prenom, date_naissance) values (?, ?, ?)";
	private static final String SQL_FIND_PERSONNEL_BY_ID = "select * from personnels where id=?";

	private Connection connection;

	public PersonnelDaoImpl() {
		this.connection = DBUtils.getConnection();
	}

	@Override
	public Personnel save(Personnel personnel) {
		ResultSet valeursAutoGenerees = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_PERSONNEL,
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
			throw new RuntimeException(e.getMessage());
		}

		return personnel;
	}

	@Override
	public Personnel findPersonnelById(Long id) {
		Personnel personnel = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_PERSONNEL_BY_ID);
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				personnel = new Personnel(resultSet.getLong("id"), resultSet.getString("nom"),
						resultSet.getString("prenom"), resultSet.getDate("date_naissance").toLocalDate());
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return personnel;
	}

}
