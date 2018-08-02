package com.wedonegood.common.model.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wedonegood.common.user.api.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByUserEmail(String email);
}
