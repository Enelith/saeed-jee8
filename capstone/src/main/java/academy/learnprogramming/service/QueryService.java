/**
 * 
 */
package academy.learnprogramming.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import academy.learnprogramming.entity.User;

/**
 * @author Jonathan Vinh
 */
@Stateless
public class QueryService {

    @Inject
    EntityManager entityManager;

    public User findUserById(Long id) {
	return entityManager.find(User.class, id);
    }

    public User findUserByEmail(String email) {
	return entityManager.createNamedQuery(User.FIND_BY_EMAIL, User.class)
		    .setParameter("email", email)
		    .getResultList()
		    .get(0);
    }

    @SuppressWarnings("rawtypes")
    public List countUserByEmail(String email) {
	return entityManager.createNativeQuery(
		    "SELECT COUNT(id) FROM TodoUser WHERE EXISTS(SELECT id FROM TodoUser WHERE email = ?)")
		    .setParameter(1, email)
		    .getResultList();
    }
}
