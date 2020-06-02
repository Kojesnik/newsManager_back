package com.epam.lab.repository.impl;

import com.epam.lab.exception.RemoveException;
import com.epam.lab.exception.UpdateException;
import com.epam.lab.model.impl.News;
import com.epam.lab.model.impl.NewsCriteria;
import com.epam.lab.repository.NewsRepository;
import com.epam.lab.specification.EntitySpecification;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class NewsRepositoryImpl implements NewsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public News add(News news) {
        entityManager.unwrap(Session.class).replicate(news, ReplicationMode.IGNORE);
        return news;
    }

    @Override
    @Transactional
    public News update(News news) {
        News newsToUpdate = entityManager.find(News.class, news.getId());
        if (Objects.isNull(newsToUpdate)) {
            throw new UpdateException("No such news to update");
        }
        entityManager.unwrap(Session.class).replicate(news, ReplicationMode.OVERWRITE);
        return news;
    }

    @Override
    @Transactional
    public Long getCount() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        query.select(builder.count(query.from(News.class)));
        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional
    public long getCriteriaCount(NewsCriteria newsCriteria) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<News> root = query.from(News.class);
        List<Predicate> predicates = new ArrayList<>();
        int tagNumber = 0;
        if (newsCriteria.getTagNames() != null) {
            Expression<String> expression = root.join("tags").get("name");
            predicates.add(expression.in(newsCriteria.getTagNames()));
            tagNumber = newsCriteria.getTagNames().size();
        }
        if (newsCriteria.getAuthor() != null) {
            Predicate predicateAuthorName = builder.equal(root.get("author").get("name"), newsCriteria.getAuthor().getName());
            Predicate predicateAuthorSurName = builder.equal(root.get("author").get("surname"), newsCriteria.getAuthor().getSurname());
            predicates.add(builder.and(predicateAuthorName, predicateAuthorSurName));
        }
        query.where(predicates.toArray(new Predicate[0])).groupBy(root.get("author").get("name"), root.get("author").get("id"), root).having(builder.ge(builder.count(root), tagNumber));
        Expression<Long> count = builder.count(root);
        query.select(count);
        return entityManager.createQuery(query).getResultList().size();
    }

    @Override
    @Transactional
    public Boolean remove(News news) {
        News newsToRemove = entityManager.find(News.class , news.getId());
        if (Objects.isNull(newsToRemove)) {
            throw new RemoveException("No such news to remove");
        }
        entityManager.remove(newsToRemove);
        return true;
    }

    @Override
    @Transactional
    public List<News> query(EntitySpecification<News> specification) {
        return specification.specified(entityManager).getResultList();
    }

}
