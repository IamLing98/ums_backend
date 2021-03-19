package com.linkdoan.backend.repository;

import com.linkdoan.backend.dto.UserDTO;
import com.linkdoan.backend.model.Student;
import com.linkdoan.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

    @Query(value =
            "SELECT user.username FROM User user")
    List<String> getUserIds();

    @Query(value =
            "SELECT new com.linkdoan.backend.dto.UserDTO(user.username, user.password," +
                    "user.createdAt, user.updatedAt, user.email, user.isActive, user.isEmailVerified, user.ownerId, role)" +
                    "FROM User user INNER JOIN Role role ON user.roleId = role.roleId " +
                    "WHERE role.roleName = :roleName")
    List<UserDTO> getAllByRoleName(@Param("roleName") String roleName);

    @Query(value =
            "SELECT student " +
                    "FROM User user INNER JOIN Student student ON user.username = student.studentId "
    )
    List<Student> getAllStudentHasAccount();

    @Query(value =
            "SELECT student " +
                    "FROM Student student  " +
                    "WHERE student.studentId NOT IN (:studentIdList)"
    )
    List<Student> getAllNotHasAccountStudentList(@Param("studentIdList") List<String> studentIdList);
}