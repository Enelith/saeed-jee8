package academy.learnprogramming.producers;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerProducers {

    @Produces
    @PersistenceContext
    EntityManager entityManager;
}
