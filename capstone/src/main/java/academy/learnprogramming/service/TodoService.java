/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package academy.learnprogramming.service;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import academy.learnprogramming.entity.Todo;
import academy.learnprogramming.entity.User;
import academy.learnprogramming.security.SecurityUtil;

/**
 * @author Jonathan Vinh
 */
@Stateless
public class TodoService {

    @Inject
    EntityManager entityManager;

    @Inject
    private QueryService queryService;

    @Inject
    private SecurityUtil securityUtil;

    private String email;

    @PostConstruct
    private void init() {
	// TODO
	email = "";
    }

    public User saveUser(User user) {
	Long count = (Long) queryService.countUserByEmail(user.getEmail()).get(0);

	if (user.getId() == null
		    && count == 0) {
	    Map<String, String> credentialMap = securityUtil.hashPassword(user.getPassword());

	    user.setPassword(credentialMap.get(SecurityUtil.HASHED_PASSWORD_KEY));
	    user.setSalt(credentialMap.get(SecurityUtil.SALT_KEY));

	    entityManager.persist(user);
	    credentialMap.clear();
	}

	return user;
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
}
