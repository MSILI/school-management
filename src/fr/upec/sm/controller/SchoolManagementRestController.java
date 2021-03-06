package fr.upec.sm.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.upec.sm.db.DBManagement;
import fr.upec.sm.model.Assistant;
import fr.upec.sm.model.Module;
import fr.upec.sm.model.Professeur;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SchoolManagementRestController {

	private DBManagement dBManagement;

	public SchoolManagementRestController() {
		dBManagement = new DBManagement();
	}

	@GET
	@Path("/professeurs")
	public Response findAllProffesors() {
		return Response.status(Response.Status.OK).entity(dBManagement.listerProfesseurs()).build();
	}

	@GET
	@Path("/assistants")
	public Response findAllAssistants() {
		return Response.status(Response.Status.OK).entity(dBManagement.listerAssistants()).build();
	}

	@GET
	@Path("/modules")
	public Response findAllModules() {
		return Response.status(Response.Status.OK).entity(dBManagement.listerModules()).build();
	}

	@POST
	@Path("/professeurs")
	public Response saveProfesseur(Professeur professeur) {
		return Response.status(Response.Status.CREATED).entity(dBManagement.ajouterProfesseur(professeur)).build();
	}

	@POST
	@Path("/assistants")
	public Response saveAssistant(Assistant assistant) {
		return Response.status(Response.Status.CREATED).entity(dBManagement.ajouterAssistant(assistant)).build();
	}

	@POST
	@Path("/modules")
	public Response saveModule(Module module) {
		return Response.status(Response.Status.CREATED).entity(dBManagement.ajouterModule(module)).build();
	}

}
