package com.epam.lab.specification.impl.author;

import com.epam.lab.model.impl.Author;
import com.epam.lab.specification.EntitySpecification;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AuthorNameSurnameSpecification implements EntitySpecification<Author> {

    private static final String NAME_FIELD = "name";
    private static final String SURNAME_FIELD = "surname";

    private String name;
    private String surname;

    public AuthorNameSurnameSpecification(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public TypedQuery<Author> specified(EntityManager entityManager) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> query = builder.createQuery(Author.class);
        Root<Author> root = query.from(Author.class);
        Predicate predicateName = builder.equal(root.get(NAME_FIELD), name);
        Predicate predicateSurname = builder.equal(root.get(SURNAME_FIELD), surname);
        Predicate predicate = builder.and(predicateName, predicateSurname);
        query.select(root).where(predicate);
        return entityManager.createQuery(query);
    }

}
