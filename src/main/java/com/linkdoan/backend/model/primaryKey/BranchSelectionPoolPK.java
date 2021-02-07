package com.linkdoan.backend.model.primaryKey;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BranchSelectionPoolPK implements Serializable {
    @Column(name = "branch_id", columnDefinition = "CHAR(10)")
    private String branchId;

    @Column(name = "selection_pool_id", columnDefinition = "CHAR(3)")
    private String selectionPoolId;
}
