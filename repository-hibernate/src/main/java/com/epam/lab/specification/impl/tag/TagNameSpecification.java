package com.epam.lab.specification.impl.tag;

import com.epam.lab.model.impl.Tag;
import com.epam.lab.specification.EntitySpecification;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class TagNameSpecification implements EntitySpecification<Tag> {

    private static final String NAME_FIELD = "name";

    private String name;

    public TagNameSpecification(String name) {
        this.name = name;
    }

    @Override
    public TypedQuery<Tag> specified(EntityManager entityManager) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> query = builder.createQuery(Tag.class);
        Root<Tag> root = query.from(Tag.class);
        query.select(root).where(builder.equal(root.get(NAME_FIELD), name));
        return entityManager.createQuery(query);
    }
}
