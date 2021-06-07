/**
 * 
 */
package academy.learnprogramming.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import academy.learnprogramming.entity.Todo;

/**
 * @author Jonathan Vinh
 */
@Transactional // Transform TodoService in a Service
public class TodoService {

    @PersistenceContext
    EntityManager entityManager;

    public Todo createTodo(Todo todo) {
	// Persist into DB
	entityManager.persist(todo);
	return todo;
    }

    public Todo updateTodo(Todo todo) {
	entityManager.merge(todo);
	return todo;
    }

    public Todo findTodoById(Long id) {
	return entityManager.find(Todo.class, id);
    }

    public List<Todo> getTodos() {
	return entityManager.createQuery("SELECT t FROM Todo t", Todo.class)
		    .getResultList();
    }
}
