package com.kenstudy.basic_authentication.repository;

import com.kenstudy.basic_authentication.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByName(String username);
}
