package com.linkdoan.backend.dto;

import com.linkdoan.backend.base.anotation.AdjHistory;
import com.linkdoan.backend.base.dto.SystemDTO;
import com.linkdoan.backend.model.Class;
import com.linkdoan.backend.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DepartmentDTO extends SystemDTO {
    private String departmentId;

    @AdjHistory(field = "department_name")
    private String departmentName;

    @AdjHistory(field = "department_type")
    private Integer departmentType;

    @AdjHistory(field = "cheif_id")
    private Employee employee; //1 - 1 relationship

}
