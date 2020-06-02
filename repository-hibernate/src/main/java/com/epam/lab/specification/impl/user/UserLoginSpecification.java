package com.epam.lab.specification.impl.user;

import com.epam.lab.model.impl.User;
import com.epam.lab.specification.EntitySpecification;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserLoginSpecification implements EntitySpecification<User> {

    private static final String LOGIN_FIELD = "login";

    private String login;

    public UserLoginSpecification(String login) {
        this.login = login;
    }

    @Override
    public TypedQuery<User> specified(EntityManager entityManager) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        Predicate predicateLogin = builder.equal(root.get(LOGIN_FIELD), login);
        Predicate predicate = builder.and(predicateLogin);
        query.select(root).where(predicate);
        return entityManager.createQuery(query);
    }

}
