package com.linkdoan.backend.dto;

import com.linkdoan.backend.base.anotation.AdjHistory;
import com.linkdoan.backend.base.dto.SystemDTO;
import com.linkdoan.backend.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDTO extends SystemDTO {
    @AdjHistory(field = "employeeId")
    private String employeeId;

    @AdjHistory(field = "fullName")
    private String fullName;

    @AdjHistory(field = "sex")
    private int sex;

    @AdjHistory(field = "dateBirth")
    private Date dateBirth;

    @AdjHistory(field = "homeAddress")
    private String homeAddress;

    @AdjHistory(field = "currentAddress")
    private String currentAddress;

    @AdjHistory(field = "degree")
    private String degree;

    @AdjHistory(field = "phone")
    private String phone;

    @AdjHistory(field = "homePhone")
    private String homePhone;

    @AdjHistory(field = "email")
    private String email;

    @AdjHistory(field = "startWork")
    private Date startWork;

    @AdjHistory(field = "avatar")
    private String avatar;

    private String departmentId;

    public Employee toModel(){
        Employee employee = new Employee();
        employee.setEmployeeId(this.employeeId);
        employee.setFullName(this.fullName);
        employee.setSex(this.sex);
        employee.setDateBirth(this.dateBirth);
        employee.setHomeAddress(this.homeAddress);
        employee.setCurrentAddress(this.currentAddress);
        employee.setDegree(this.degree);
        employee.setPhone(this.phone);
        employee.setHomePhone(this.homePhone);
        employee.setEmail(this.email);
        employee.setStartWork(this.startWork);
        employee.setAvatar(this.avatar);
       // employee.set(this.departmentId);
        return employee;
    }
}
