package jersey.server.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import jersey.server.object.Object;
import jersey.server.java.ToDo;

public class Resource {
	@Context UriInfo uriInfo;
	@Context Request request;
	String id;
	
	public Resource(UriInfo uriInfo, Request request, String id) 
	{
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}
	
	/**
	 * Get Integration for Applications	
	 * @return
	 */
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public ToDo getTodo() 
	{
		ToDo getExample = Object.instance.getModel().get(id);
		if(getExample==null) throw new RuntimeException("GET Failed, Id='" + id + "' not found");
		return getExample;
	}
	
	/**
	 * Get Integration for Browsers
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_XML)
	public ToDo getTodoHTML() 
	{
		ToDo getExample = Object.instance.getModel().get(id);
		if(getExample==null) throw new RuntimeException("GET Failed, Id='" + id + "' not found");
		return getExample;
	}
	
	/**
	 * Put Functionality
	 * @param todo
	 * @return
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putTodo(JAXBElement<ToDo> todo) 
	{
		ToDo putExample = todo.getValue();
		return putAndGetResponse(putExample);
	}
	
	/**
	 * Delete Functionality
	 */
	@DELETE
	public void deleteTodo() 
	{
		ToDo deleteExample = Object.instance.getModel().remove(id);
		if(deleteExample==null) throw new RuntimeException("DELETE Failed, Id='" + id + "' not found");
	}
	
	/**
	 * Create the Responses
	 * @param todo
	 * @return
	 */
	private Response putAndGetResponse(ToDo toDoItem) {
		Response res;
		
		if(Object.instance.getModel().containsKey(toDoItem.getId())) 
		{
			res = Response.noContent().build();
		} 
		else 
		{
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		
		Object.instance.getModel().put(toDoItem.getId(), toDoItem);
		return res;
	}
	
	

}
