package medovichkovvcalculationservice.repository;

import medovichkovvcalculationservice.entities.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author ivand on 12.07.2020
 */
@Repository
public abstract class AbstractRepository {
    @PersistenceContext
    protected EntityManager entityManager;
}
