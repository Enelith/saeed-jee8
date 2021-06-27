/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package academy.learnprogramming.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import academy.learnprogramming.annotations.Authz;
import academy.learnprogramming.entity.Todo;
import academy.learnprogramming.service.QueryService;
import academy.learnprogramming.service.TodoService;

/**
 * @author Jonathan Vinh
 */
@Path("todo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authz // <= Bcs of this annotation, for any attempts to access the ressources on this
       // class, you need to be LOGGED IN (otherwise, the SecurityFilter will run and
       // return the adequate errors / exceptions)
public class TodoRest {

    @Inject
    TodoService todoService;

    @Inject
    QueryService queryService;

    @Path("new")
    @POST
    public Response createTodo(Todo todo) {
	todoService.createTodo(todo);

	return Response.ok(todo).build();
    }

    /*
     * @Path("update")
     * @PUT public Response updateTodo(Todo todo) { todoService.updateTodo(todo);
     * return Response.ok(todo).build(); }
     */

    @Path("{id}")
    @GET
    public Todo getTodo(@PathParam("id") Long id) {
	return queryService.findTodoById(id);
    }

    @Path("list")
    @GET
    public List<Todo> getTodos() {
	return queryService.getAllTodos();
    }

    @Path("status")
    @POST
    public Response markAsComplete(@QueryParam("id") Long id) {
	Todo todo = queryService.findTodoById(id);
	todo.setIsCompleted(true);
	todoService.updateTodo(todo);

	return Response.ok(todo).build();

    }

}
