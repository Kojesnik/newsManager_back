package com.epam.lab.repository.impl;

import com.epam.lab.exception.RemoveException;
import com.epam.lab.model.impl.Tag;
import com.epam.lab.model.impl.User;
import com.epam.lab.repository.UserRepository;
import com.epam.lab.specification.EntitySpecification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public User add(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    @Transactional
    public User update(User user) {
        return null;
    }

    @Override
    @Transactional
    public Boolean remove(User user) {
        User userToRemove = entityManager.find(User.class , user.getId());
        if (Objects.isNull(userToRemove)) {
            throw new RemoveException("No such user to remove");
        }
        entityManager.remove(userToRemove);
        return true;
    }

    @Override
    @Transactional
    public List<User> query(EntitySpecification<User> specification) {
        return specification.specified(entityManager).getResultList();
    }
}
