package com.epam.lab.repository.impl;

import com.epam.lab.exception.InsertException;
import com.epam.lab.exception.RemoveException;
import com.epam.lab.exception.UpdateException;
import com.epam.lab.model.impl.Author;
import com.epam.lab.repository.AuthorRepository;
import com.epam.lab.specification.EntitySpecification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Objects;

@Component
public class AuthorRepositoryImpl implements AuthorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Author add(Author author) {
        entityManager.persist(author);
        if (Objects.isNull(author.getId())) {
            throw new InsertException("Author wasn't inserted");
        }
        return author;
    }

    @Override
    @Transactional
    public Author update(Author author) {
        Author authorToUpdate = entityManager.find(Author.class, author.getId());
        if (Objects.isNull(authorToUpdate)) {
            throw new UpdateException("No such author to update");
        }
        return entityManager.merge(author);
    }

    @Override
    @Transactional
    public Boolean remove(Author author) {
        Author authorToRemove = entityManager.find(Author.class , author.getId());
        if (Objects.isNull(authorToRemove)) {
            throw new RemoveException("No such author to remove");
        }
        entityManager.remove(authorToRemove);
        return true;
    }

    @Override
    @Transactional
    public List<Author> query(EntitySpecification<Author> specification) {
        return specification.specified(entityManager).getResultList();
    }

}
