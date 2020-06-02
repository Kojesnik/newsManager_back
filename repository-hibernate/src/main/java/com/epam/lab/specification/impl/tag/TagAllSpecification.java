package com.epam.lab.specification.impl.tag;

import com.epam.lab.model.impl.Tag;
import com.epam.lab.specification.EntitySpecification;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class TagAllSpecification implements EntitySpecification<Tag> {

    @Override
    public TypedQuery<Tag> specified(EntityManager entityManager) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> query = builder.createQuery(Tag.class);
        Root<Tag> root = query.from(Tag.class);
        query.select(root);
        return entityManager.createQuery(query);
    }

}
