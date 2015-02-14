package jersey.server.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import jersey.server.object.Object;
import jersey.server.java.ToDo;

@Path("/todos")
public class Resources {
	@Context UriInfo uriInfo;
	@Context Request request;

	/** 
	 * Return all of the current ToDos
	 */
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<ToDo> getTodosBrowser() 
	{
		List<ToDo> listOfToDos = new ArrayList<ToDo>();
		listOfToDos.addAll(Object.instance.getModel().values());
		return listOfToDos;
	}
	
	/**
	 * Return list of ToDos for Java Applications
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<ToDo> getTodos() 
	{
		List<ToDo> listOfToDos = new ArrayList<ToDo>();
		listOfToDos.addAll(Object.instance.getModel().values());
		return listOfToDos;
	}
	
	/**
	 * Return a simple count of ToDos
	 * @return
	 */
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() 
	{
		int count = Object.instance.getModel().size();
		return String.valueOf(count);
	}

	/**
	 * Post Functionality
	 * @param id
	 * @param title
	 * @param description
	 * @param servletResponse
	 * @throws IOException
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newTodo(@FormParam("id") String id,
			@FormParam("title") String title,
			@FormParam("description") String description,
			@Context HttpServletResponse servletResponse) throws IOException {
		
		ToDo postExample = new ToDo(id, title);
		if (description != null) postExample.setDescription(description);
		Object.instance.getModel().put(id, postExample);

		servletResponse.sendRedirect("../newtodo.html");
	}

	/**
	 * Provide functionality to search by id parameter
	 * @param id
	 * @return
	 */
	@Path("{todo}")
	public Resource getTodo(@PathParam("todo") String id) {
		return new Resource(uriInfo, request, id);
	}

}