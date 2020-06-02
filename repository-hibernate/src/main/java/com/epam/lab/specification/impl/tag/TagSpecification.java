package com.epam.lab.specification.impl.tag;

import com.epam.lab.model.impl.Tag;
import com.epam.lab.specification.EntitySpecification;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TagSpecification implements EntitySpecification<Tag> {

    private static final String NAME_FIELD = "name";
    private static final String ID_FIELD = "id";

    private String name;
    private Long id;

    public TagSpecification(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public TypedQuery<Tag> specified(EntityManager entityManager) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> query = builder.createQuery(Tag.class);
        Root<Tag> root = query.from(Tag.class);
        Predicate predicateId = builder.equal(root.get(ID_FIELD), id);
        Predicate predicateName = builder.equal(root.get(NAME_FIELD), name);
        Predicate predicate = builder.and(predicateId, predicateName);
        query.select(root).where(predicate);
        return entityManager.createQuery(query);
    }

}
