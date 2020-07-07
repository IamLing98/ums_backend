package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

}