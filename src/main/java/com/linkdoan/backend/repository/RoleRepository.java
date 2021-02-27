package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value =
            "SELECT r  FROM User user " +
                    "INNER JOIN Role  r ON user.roleId = r.roleId " +
                    "WHERE user.userId =  :userId "
    )
    List<Role> findAllRoles(@Param("userId") Long userId);
}
