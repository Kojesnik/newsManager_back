package com.epam.lab.specification.impl.news;

import com.epam.lab.model.impl.News;
import com.epam.lab.model.impl.NewsCriteria;
import com.epam.lab.specification.EntitySpecification;
import com.epam.lab.type.SortType;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NewsCriteriaSpecification implements EntitySpecification<News> {

    private static final String ID_FIELD = "id";
    private static final String AUTHOR_FIELD = "author";
    private static final String TAGS_FIELD = "tags";
    private static final String NAME_FIELD = "name";
    private static final String SURNAME_FIELD = "surname";
    private static final String CREATION_DATE_FIELD = "creationDate";

    private NewsCriteria newsCriteria;

    private Root<News> root;
    private List<Order> orders;
    private List<Predicate> predicates;
    private int tagNumber;

    public NewsCriteriaSpecification(NewsCriteria newsCriteria) {
        this.newsCriteria = newsCriteria;
        orders = new ArrayList<>();
        predicates = new ArrayList<>();
    }

    @Override
    public TypedQuery<News> specified(EntityManager entityManager) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<News> query = builder.createQuery(News.class);
        root = query.from(News.class);
        tagQuery();
        authorQuery(builder);
        query.select(root).where(predicates.toArray(new Predicate[0])).groupBy(root.get(AUTHOR_FIELD).get(NAME_FIELD), root.get(AUTHOR_FIELD).get(ID_FIELD), root).having(builder.ge(builder.count(root), tagNumber));
        sortQuery(query, builder);
        TypedQuery<News> typedQuery = entityManager.createQuery(query);
        paginationQuery(typedQuery);
        return typedQuery;
    }

    private void paginationQuery(TypedQuery<News> typedQuery) {
        if (newsCriteria.getPageSize() != 0 && newsCriteria.getPageNumber() != 0) {
            typedQuery.setFirstResult((int) ((newsCriteria.getPageNumber() - 1) * newsCriteria.getPageSize()));
            typedQuery.setMaxResults((int) newsCriteria.getPageSize());
        }
    }

    private void tagQuery() {
        if (newsCriteria.getTagNames() != null) {
            Expression<String> expression = root.join(TAGS_FIELD).get(NAME_FIELD);
            predicates.add(expression.in(newsCriteria.getTagNames()));
            tagNumber = newsCriteria.getTagNames().size();
        }
    }

    private void authorQuery(CriteriaBuilder builder) {
        if (newsCriteria.getAuthor() != null) {
            Predicate predicateAuthorName = builder.equal(root.get(AUTHOR_FIELD).get(NAME_FIELD), newsCriteria.getAuthor().getName());
            Predicate predicateAuthorSurName = builder.equal(root.get(AUTHOR_FIELD).get(SURNAME_FIELD), newsCriteria.getAuthor().getSurname());
            predicates.add(builder.and(predicateAuthorName, predicateAuthorSurName));
        }
    }

    private void sortQuery(CriteriaQuery<News> query, CriteriaBuilder builder) {
        if (Objects.nonNull(newsCriteria.getSortType())) {
            if (newsCriteria.getSortType().equals(SortType.AUTHOR)) {
                orders.add(builder.asc(root.get(AUTHOR_FIELD).get(NAME_FIELD)));
            } else if (newsCriteria.getSortType().equals(SortType.DATE)) {
                orders.add(builder.desc(root.get(CREATION_DATE_FIELD)));
            }
            query.orderBy(orders);
        }
    }

}
