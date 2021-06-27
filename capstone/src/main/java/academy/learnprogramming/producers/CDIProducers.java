/**
 * 
 */
package academy.learnprogramming.producers;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Jonathan Vinh
 *
 */
public class CDIProducers {

    @Produces
    @PersistenceContext
    EntityManager entityManager;
}
