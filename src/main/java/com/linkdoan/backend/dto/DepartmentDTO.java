package com.linkdoan.backend.dto;

import com.linkdoan.backend.base.anotation.AdjHistory;
import com.linkdoan.backend.base.dto.SystemDTO;
import com.linkdoan.backend.model.Class;
import com.linkdoan.backend.model.Employee;
import com.linkdoan.backend.model.Specialized;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DepartmentDTO  {
    private String departmentId;

    @AdjHistory(field = "departmentName")
    private String departmentName;

    @AdjHistory(field = "departmentType")
    private String departmentType;

    @AdjHistory(field = "startYear")
    private String startYear;

    private List<Class> children;

    private List<Employee> employeeList;

    private List<Specialized> specializedList;

}
