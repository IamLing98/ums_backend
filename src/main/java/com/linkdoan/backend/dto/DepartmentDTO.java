package com.linkdoan.backend.dto;

import com.linkdoan.backend.base.anotation.AdjHistory;
import com.linkdoan.backend.model.Employee;
import com.linkdoan.backend.model.Specialized;
import com.linkdoan.backend.model.YearClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DepartmentDTO {
    private String departmentId;

    @AdjHistory(field = "departmentName")
    private String departmentName;

    @AdjHistory(field = "departmentType")
    private String departmentType;

    @AdjHistory(field = "startYear")
    private String startYear;

    private List<YearClass> children;

    private List<Employee> employeeList;

    private List<Specialized> specializedList;

    private List<BranchDTO> branchList;

}
