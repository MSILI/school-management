package fr.upec.sm.service;

import java.util.List;

import fr.upec.sm.model.Assistant;

public interface AssistantService {

	Assistant save(Assistant assistant);

	List<Assistant> findAll();

}
