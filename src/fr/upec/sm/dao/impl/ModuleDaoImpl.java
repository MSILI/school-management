package fr.upec.sm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.upec.sm.dao.ModuleDao;
import fr.upec.sm.dao.ProfesseurDao;
import fr.upec.sm.model.Module;
import fr.upec.sm.model.Professeur;
import fr.upec.sm.utils.DBUtils;

public class ModuleDaoImpl implements ModuleDao {
	
	private static final String SQL_SAVE_MODULE = "insert into modules (titre, nb_heures_cours, nb_heures_td, nb_heures_tp, responsable_id) values (?, ?, ?, ?, ?)";
	private static final String SQL_FIND_MODULES = "select * from modules";

	private Connection connection;
	private ProfesseurDao professeurDao;

	public ModuleDaoImpl() {
		this.connection = DBUtils.getConnection();
		this.professeurDao = new ProfesseurDaoImpl();
	}

	@Override
	public Module save(Module module) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_MODULE,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, module.getTitre());
			preparedStatement.setInt(2, module.getNbHeuresCours());
			preparedStatement.setInt(3, module.getNbHeuresTd());
			preparedStatement.setInt(4, module.getNbHeuresTp());
			preparedStatement.setLong(5, module.getResponsable().getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erreur au niveau de la base de données");
		}

		return module;
	}

	@Override
	public List<Module> findAll() {
		List<Module> modules = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SQL_FIND_MODULES);
			while (resultSet.next()) {
				Professeur responsable = this.professeurDao.findById(resultSet.getLong("responsable_id"));
				modules.add(new Module(resultSet.getString("titre"), resultSet.getInt("nb_heures_cours"),
						resultSet.getInt("nb_heures_td"), resultSet.getInt("nb_heures_tp"), responsable));
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erreur au niveau de la base de données");
		}
		return modules;
	}

}
