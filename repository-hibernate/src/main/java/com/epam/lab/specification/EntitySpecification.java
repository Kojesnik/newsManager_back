package com.epam.lab.specification;

import com.epam.lab.model.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public interface EntitySpecification<E extends AbstractEntity> {

    TypedQuery<E> specified(EntityManager entityManager);

}
