package com.tutorial.jwt.token.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tutorial.jwt.token.dto.UserEntity;

import java.util.Optional;

/*
 * Repository to get User data.
 */

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);
}
