/**
 * 
 */
package academy.learnprogramming.service;

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
}
