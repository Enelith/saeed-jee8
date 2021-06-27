/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package academy.learnprogramming.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import academy.learnprogramming.entity.Todo;
import academy.learnprogramming.entity.User;

/**
 * @author Jonathan Vinh
 */
@Stateless
public class TodoService {

    @Inject
    EntityManager entityManager;

    @Inject
    private QueryService queryService;

    private String email;

    @PostConstruct
    private void init() {
	// TODO
	email = "";
    }

    public Todo createTodo(Todo todo) {
	// Persist into db
	// entityManager.persist(todo);
	User userByEmail = queryService.findUserByEmail(email);
	if (userByEmail != null) {
	    todo.setTodoOwner(userByEmail);
	    entityManager.persist(todo);
	}

	return todo;
    }

    public Todo updateTodo(Todo todo) {
	entityManager.merge(todo);
	return todo;
    }

    public Todo findToDoById(Long id) {
	return entityManager.find(Todo.class, id);
    }

    public List<Todo> getTodos() {
	return entityManager.createQuery("SELECT t FROM Todo t", Todo.class).getResultList();
    }
}
