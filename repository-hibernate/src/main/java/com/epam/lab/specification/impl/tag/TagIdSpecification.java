package com.epam.lab.specification.impl.tag;

import com.epam.lab.model.impl.Tag;
import com.epam.lab.specification.EntitySpecification;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class TagIdSpecification implements EntitySpecification<Tag> {

    private static final String ID_FIELD = "id";

    private Long id;

    public TagIdSpecification(Long id) {
        this.id = id;
    }

    @Override
    public TypedQuery<Tag> specified(EntityManager entityManager) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> query = builder.createQuery(Tag.class);
        Root<Tag> root = query.from(Tag.class);
        query.select(root).where(builder.equal(root.get(ID_FIELD), id));
        return entityManager.createQuery(query);
    }
}
