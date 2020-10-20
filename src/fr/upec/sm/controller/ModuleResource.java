package fr.upec.sm.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.upec.sm.service.ModuleService;
import fr.upec.sm.service.impl.ModuleServiceImpl;
import fr.upec.sm.model.Module;

@Path("/modules")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ModuleResource {
	
	private ModuleService moduleService;
	
	public ModuleResource() {
		this.moduleService = new ModuleServiceImpl();
	}
	
	@GET
	public Response findAll() {
		return Response.status(Response.Status.OK)
				.entity(this.moduleService.findAll())
				.build();
	}
	
	@POST
	public Response save(Module module) {
		return Response.status(Response.Status.CREATED)
				.entity(this.moduleService.save(module))
				.build();
	}
}
