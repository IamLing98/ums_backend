package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository("user")
public interface UserRepository extends CrudRepository<User, String> {
    User findByUsername(String username);

}