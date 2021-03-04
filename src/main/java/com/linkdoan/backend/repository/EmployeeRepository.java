package com.linkdoan.backend.repository;

import com.linkdoan.backend.dto.EmployeeDTO;
import com.linkdoan.backend.model.Employee;
import com.linkdoan.backend.model.Subject;
import com.linkdoan.backend.model.TeacherEducationTimeLine;
import com.linkdoan.backend.model.TeacherWorkTimeLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    @Query(
            value = "SELECT new com.linkdoan.backend.dto.EmployeeDTO(employee.employeeId, employee.departmentId, employee.employeeDepartmentLevel, " +
                    "employee.officeId, employee.employeeOfficeLevel, employee.fullName, employee.dateBirth, employee.sex, employee.placeBorn, " +
                    "employee.contactAddress, employee.phoneNumber, employee.email, employee.degree, employee.degreeDetails, " +
                    "employee.scientificTitles, employee.scientificTitlesGetYear, employee.startWork, employee.avatar, " +
                    "employee.ethnic, employee.bornPlace, employee.homeTown, employee.permanentResidence, employee.nationality, " +
                    "employee.religion, employee.CPStartDate, employee.identityNumber, employee.identityCreatedDate, " +
                    "employee.identityCreatedPlace, employee.bankNumber, department) " +
                    "FROM Employee employee INNER JOIN Department department ON employee.departmentId = department.departmentId "
    )
    List<EmployeeDTO> findAllTeacher();


    //start get detail
    @Query(
            value = "SELECT  subject " +
                    "FROM Employee employee INNER JOIN TeacherSubject TS ON employee.employeeId = TS.teacherId " +
                    "INNER JOIN Subject subject ON TS.subjectId = subject.subjectId " +
                    "WHERE employee.employeeId = :employeeId "
    )
    List<Subject> getListSubject(@Param("employeeId") String employeeId);

    @Query(
            value = "SELECT  TET " +
                    "FROM Employee employee INNER JOIN TeacherEducationTimeLine TET ON employee.employeeId = TET.teacherId " +
                    "WHERE employee.employeeId = :employeeId "
    )
    List<TeacherEducationTimeLine> getListEducationTimeline(@Param("employeeId") String employeeId);

    @Query(
            value = "SELECT  TWT " +
                    "FROM Employee employee INNER JOIN TeacherWorkTimeLine TWT ON employee.employeeId = TWT.teacherId " +
                    "WHERE employee.employeeId = :employeeId "
    )
    List<TeacherWorkTimeLine> getListWorkTimeline(@Param("employeeId") String employeeId);

    @Query(
            value = "SELECT new com.linkdoan.backend.dto.EmployeeDTO(employee.employeeId, employee.departmentId, employee.employeeDepartmentLevel, " +
                    "employee.officeId, employee.employeeOfficeLevel, employee.fullName, employee.dateBirth, employee.sex, employee.placeBorn, " +
                    "employee.contactAddress, employee.phoneNumber, employee.email, employee.degree, employee.degreeDetails, " +
                    "employee.scientificTitles, employee.scientificTitlesGetYear, employee.startWork, employee.avatar, " +
                    "employee.ethnic, employee.bornPlace, employee.homeTown, employee.permanentResidence, employee.nationality, " +
                    "employee.religion, employee.CPStartDate, employee.identityNumber, employee.identityCreatedDate, " +
                    "employee.identityCreatedPlace, employee.bankNumber, department) " +
                    "FROM Employee employee LEFT JOIN Department department ON employee.departmentId = department.departmentId " +
                    "WHERE employee.employeeId = :employeeId "
    )
    List<EmployeeDTO> getDetail(@Param("employeeId") String employeeId);
    //end get detail


    @Query(
            value = "SELECT new com.linkdoan.backend.dto.EmployeeDTO(employee.employeeId, employee.departmentId, employee.employeeDepartmentLevel, " +
                    "employee.officeId, employee.employeeOfficeLevel, employee.fullName, employee.dateBirth, employee.sex, employee.placeBorn, " +
                    "employee.contactAddress, employee.phoneNumber, employee.email, employee.degree, employee.degreeDetails, " +
                    "employee.scientificTitles, employee.scientificTitlesGetYear, employee.startWork, employee.avatar, " +
                    "employee.ethnic, employee.bornPlace, employee.homeTown, employee.permanentResidence, employee.nationality, " +
                    "employee.religion, employee.CPStartDate, employee.identityNumber, employee.identityCreatedDate, " +
                    "employee.identityCreatedPlace, employee.bankNumber, department) " +
                    "FROM Employee employee LEFT JOIN Department department ON employee.departmentId = department.departmentId "
    )
    List<EmployeeDTO> findAllEmployee();
}
