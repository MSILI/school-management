package fr.upec.sm.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.upec.sm.model.Professeur;
import fr.upec.sm.service.ProfesseurService;
import fr.upec.sm.service.impl.ProfesseurServiceImpl;

@Path("/professeurs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfesseurResource {
	
	private ProfesseurService professeurService;
	
	public ProfesseurResource() {
		this.professeurService = new ProfesseurServiceImpl();
	}
	
	@GET
	public Response findAll() {
		return Response.status(Response.Status.OK)
				.entity(this.professeurService.findAll())
				.build();
	}
	
	@POST
	public Response save(Professeur professeur) {
		return Response.status(Response.Status.CREATED)
				.entity(this.professeurService.save(professeur))
				.build();
	}
}
