package com.example.demo_db;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final EntityManager entityManager;

    @Transactional
    public UserEntity createUser(String firstName, String lastName, String email) {
        UserEntity user = new UserEntity();
        user.setFirstName(firstName);
        user.setLastName(lastName);

        return entityManager.merge(user);
    }

    @Transactional
    public List<UserEntity> findAllUsers() {
        return entityManager.createQuery("FROM UserEntity", UserEntity.class).getResultList();
    }

    @Transactional
    public List<UserEntity> findSmithUsers() {
        return entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.lastName = :smith", UserEntity.class).setParameter("smith", "Smith").getResultList();

//        Query query = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.lastName = :smith");
//        query.setParameter("smith", "Smith");
//        return query.getResultList();
    }

    @Transactional
    public List<UserEntity> findSpecifiedNameUsers(String nameParam) {
        return entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.firstName LIKE :nameParam OR u.lastName LIKE :nameParam", UserEntity.class).setParameter("nameParam", "%"+nameParam+"%").getResultList();
    }

}

