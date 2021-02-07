package com.linkdoan.backend.model;

import com.linkdoan.backend.model.primaryKey.BranchSelectionPoolPK;
import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Table(name = "branch_selection_pool")
@Entity

public class BranchSelectionPool {
    @EmbeddedId
    private BranchSelectionPoolPK id;

}
