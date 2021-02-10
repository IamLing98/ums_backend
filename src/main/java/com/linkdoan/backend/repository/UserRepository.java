package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query(value=
            "SELECT user.userId FROM User user")
    List<Long> getUserIds();

}