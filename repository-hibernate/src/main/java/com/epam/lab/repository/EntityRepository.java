package com.epam.lab.repository;

import com.epam.lab.model.AbstractEntity;
import com.epam.lab.specification.EntitySpecification;
import java.util.List;

public interface EntityRepository<T extends AbstractEntity> {

    T add(T entity);
    T update(T entity);
    Boolean remove(T entity);
    List<T> query(EntitySpecification<T> specification);

}
