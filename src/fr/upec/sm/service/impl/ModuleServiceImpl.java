package fr.upec.sm.service.impl;

import java.util.List;

import fr.upec.sm.dao.ModuleDao;
import fr.upec.sm.dao.impl.ModuleDaoImpl;
import fr.upec.sm.model.Module;
import fr.upec.sm.service.ModuleService;

public class ModuleServiceImpl implements ModuleService {

	private ModuleDao moduleDao;

	public ModuleServiceImpl() {
		this.moduleDao = new ModuleDaoImpl();
	}

	@Override
	public Module save(Module module) {
		return this.moduleDao.save(module);
	}

	@Override
	public List<Module> findAll() {
		return this.moduleDao.findAll();
	}

}
