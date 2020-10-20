package fr.upec.sm.service;

import java.util.List;

import fr.upec.sm.model.Module;

public interface ModuleService {

	Module save(Module module);

	List<Module> findAll();

}
