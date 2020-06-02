package com.epam.lab.repository.impl;

import com.epam.lab.exception.InsertException;
import com.epam.lab.exception.RemoveException;
import com.epam.lab.exception.UpdateException;
import com.epam.lab.model.impl.Tag;
import com.epam.lab.repository.TagRepository;
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
public class TagRepositoryImpl implements TagRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Tag add(Tag tag) {
        entityManager.persist(tag);
        if (Objects.isNull(tag.getId())) {
            throw new InsertException("Tag wasn't inserted");
        }
        return tag;
    }

    @Override
    @Transactional
    public Tag update(Tag tag) {
        Tag tagToUpdate = entityManager.find(Tag.class, tag.getId());
        if (Objects.isNull(tagToUpdate)) {
            throw new UpdateException("No such tag to update");
        }
        return entityManager.merge(tag);
    }

    @Override
    @Transactional
    public Boolean remove(Tag tag) {
        Tag tagToRemove = entityManager.find(Tag.class , tag.getId());
        if (Objects.isNull(tagToRemove)) {
            throw new RemoveException("No such tag to remove");
        }
        entityManager.remove(tagToRemove);
        return true;
    }

    @Override
    @Transactional
    public List<Tag> query(EntitySpecification<Tag> specification) {
        return specification.specified(entityManager).getResultList();
    }

}
