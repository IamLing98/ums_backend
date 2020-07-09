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
    @AdjHistory(field = "employee_id")
    private String employeeId;

    @AdjHistory(field = "first_name")
    private String firstName;

    @AdjHistory(field = "last_name")
    private String lastName;

    @AdjHistory(field = "sex")
    private int sex;

    @AdjHistory(field = "date_birth")
    private Date dateBirth;

    @AdjHistory(field = "home_address")
    private String homeAddress;

    @AdjHistory(field = "current_address")
    private String currentAddress;

    @AdjHistory(field = "degree")
    private String degree;

    @AdjHistory(field = "phone")
    private String phone;

    @AdjHistory(field = "home_phone")
    private String homePhone;

    @AdjHistory(field = "email")
    private String email;

    @AdjHistory(field = "start_work")
    private Date startWork;

    @AdjHistory(field = "avatar")
    private String avatar;
    public Employee toModel(){
        Employee employee = new Employee();

        employee.setEmployeeId(this.employeeId);
        employee.setFirstName(this.firstName);
        employee.setLastName(this.lastName);
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
        return employee;
    }
}
