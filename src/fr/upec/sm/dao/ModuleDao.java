package fr.upec.sm.dao;

import java.util.List;
import fr.upec.sm.model.Module;

public interface ModuleDao {

	Module save(Module module);
	
	List<Module> findAll();
}