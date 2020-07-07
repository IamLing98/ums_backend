package com.linkdoan.backend.repository;
import com.linkdoan.backend.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    Student findByStudentId(String studentId);
    Page findAllByStudentId(Pageable pageable,String studentId);
}
