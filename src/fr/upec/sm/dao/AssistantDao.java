package fr.upec.sm.dao;

import java.util.List;

import fr.upec.sm.model.Assistant;

public interface AssistantDao {

	Assistant save(Assistant assistant);

	List<Assistant> findAll();

}