package com.epam.lab.specification.impl.news;

import com.epam.lab.model.impl.News;
import com.epam.lab.specification.EntitySpecification;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class NewsIdSpecification implements EntitySpecification<News> {

    private static final String ID_FIELD = "id";

    private Long id;

    public NewsIdSpecification(Long id) {
        this.id = id;
    }

    @Override
    public TypedQuery<News> specified(EntityManager entityManager) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<News> query = builder.createQuery(News.class);
        Root<News> root = query.from(News.class);
        query.select(root).where(builder.equal(root.get(ID_FIELD), id));
        return entityManager.createQuery(query);
    }
}
