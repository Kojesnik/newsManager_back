package com.epam.lab.specification.impl.author;

import com.epam.lab.model.impl.Author;
import com.epam.lab.specification.EntitySpecification;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class AuthorIdSpecification implements EntitySpecification<Author>{

    private static final String ID_FIELD = "id";

    private Long id;

    public AuthorIdSpecification(Long id) {
        this.id = id;
    }

    @Override
    public TypedQuery<Author> specified(EntityManager entityManager) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> query = builder.createQuery(Author.class);
        Root<Author> root = query.from(Author.class);
        query.select(root).where(builder.equal(root.get(ID_FIELD), id));
        return entityManager.createQuery(query);
    }
}
