package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.Employee;
import com.linkdoan.backend.model.Specialized;
import com.linkdoan.backend.model.YearClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DepartmentDTO {

    private String departmentId;

    private String departmentName;

    private Integer departmentType;

    private Integer startYear; //date type

    private List<YearClass> children;

    private List<Employee> employeeList;

    private List<Specialized> specializedList;

    private List<BranchDTO> branchList;

}
