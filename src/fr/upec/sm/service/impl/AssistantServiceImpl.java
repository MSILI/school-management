package fr.upec.sm.service.impl;

import java.util.List;

import fr.upec.sm.dao.AssistantDao;
import fr.upec.sm.dao.impl.AssistantDaoImpl;
import fr.upec.sm.model.Assistant;
import fr.upec.sm.service.AssistantService;

public class AssistantServiceImpl implements AssistantService {
	
	private AssistantDao assistantDao;
	
	public AssistantServiceImpl() {
		this.assistantDao = new AssistantDaoImpl();
	}

	@Override
	public Assistant save(Assistant assistant) {
		return this.assistantDao.save(assistant);
	}

	@Override
	public List<Assistant> findAll() {
		return this.assistantDao.findAll();
	}

}
