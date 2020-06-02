package com.epam.lab.specification.impl.news;

import com.epam.lab.model.impl.Author;
import com.epam.lab.model.impl.News;
import com.epam.lab.specification.EntitySpecification;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

public class NewsAuthorIdSpecification implements EntitySpecification<News> {

    private static final String AUTHOR_FIELD = "author";
    private static final String ID_FIELD = "id";

    private Long id;

    public NewsAuthorIdSpecification(Long id) {
        this.id = id;
    }

    @Override
    public TypedQuery<News> specified(EntityManager entityManager) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<News> query = builder.createQuery(News.class);
        Root<News> root = query.from(News.class);
        Join<News, Author> authorJoin = root.join(AUTHOR_FIELD);
        query.select(root).where(builder.equal(authorJoin.get(ID_FIELD), id));
        return entityManager.createQuery(query);
    }
}
