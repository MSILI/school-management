package fr.upec.sm.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.upec.sm.db.DBManagement;
import fr.upec.sm.model.Module;
import fr.upec.sm.model.Professeur;

public class SchoolManagementRestController {

	private DBManagement dBManagement;

	public SchoolManagementRestController() {
		dBManagement = new DBManagement();
	}

	@GET
	@Path("/professeurs")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professeur> findAllProffesors() {
		return dBManagement.listerProfesseurs();
	}

	@POST
	@Path("/modules")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Module saveModule(Module module) {
		return dBManagement.ajouterModule(module);
	}

}
