package fr.upec.sm.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.upec.sm.model.Assistant;
import fr.upec.sm.service.AssistantService;
import fr.upec.sm.service.impl.AssistantServiceImpl;

@Path("/assistants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AssistantResource {
	
	private AssistantService assistantService;
	
	public AssistantResource() {
		this.assistantService = new AssistantServiceImpl();
	}
	
	@GET
	public Response findAll() {
		return Response.status(Response.Status.OK)
				.entity(this.assistantService.findAll())
				.build();
	}
	
	@POST
	public Response save(Assistant assistant) {
		return Response.status(Response.Status.CREATED)
				.entity(this.assistantService.save(assistant))
				.build();
	}
	

}
