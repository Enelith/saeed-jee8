/**
 * 
 */
package academy.learnprogramming.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import academy.learnprogramming.entity.Todo;
import academy.learnprogramming.entity.User;

/**
 * @author Jonathan Vinh
 */
@Stateless
public class QueryService {

    @Inject
    EntityManager entityManager;
    
    @Context
    private SecurityContext securityContext; // This will take the instance generated AFTER the user successfully logged in
    // This securityContext will have a VALID Principal object
    
    public User findUserById(Long id) {
	return entityManager.find(User.class, id);
    }

    public User findUserByEmail(String email) {
	List<User> users= entityManager.createNamedQuery(User.FIND_BY_EMAIL, User.class)
		    .setParameter("email", email)
		    .getResultList();
	
	if(!users.isEmpty()) {
	    return users.get(0);
	}
	return null;
    }

    @SuppressWarnings("rawtypes")
    public List countUserByEmail(String email) {
	return entityManager.createNativeQuery(
		    "SELECT COUNT(id) FROM TodoUser WHERE EXISTS(SELECT id FROM TodoUser WHERE email = ?)")
		    .setParameter(1, email)
		    .getResultList();
    }
    
    /*
     * For the following 3 methods, we want to restrict the access to those, since it shouldn't be accessible without security controls
     */

    /**
     * Find a Todo object with its ID, restricted by user currently in use
     * 
     * @param id
     * @param email
     * @return 
     */
    public Todo findTodoById(Long id) {
	List<Todo> todoList = entityManager.createNamedQuery(Todo.FIND_BY_ID, Todo.class)
		    .setParameter("id", id)
		    .setParameter("email", securityContext.getUserPrincipal().getName())
		    .getResultList();
	return (!todoList.isEmpty()
		    ? todoList.get(0)
		    : null);
    }

    /**
     * Return the list of Todo objects
     * 
     * @param email
     * @return
     */
    public List<Todo> getAllTodos() {
	return entityManager.createNamedQuery(Todo.FIND_ALL_BY_USERS, Todo.class)
		    .setParameter("email", securityContext.getUserPrincipal().getName())
		    .getResultList();
    }

    /**
     * Return a Todo object by its task, restricted by user currently in use
     * 
     * @param taskText
     * @param email
     * @return
     */
    public List<Todo> getTodoByTask(String taskText) {
	return entityManager.createNamedQuery(Todo.FIND_BY_TASK, Todo.class)
		    .setParameter("task", "%" + taskText + "%")
		    .setParameter("email", securityContext.getUserPrincipal().getName())
		    .getResultList();
    }
}
