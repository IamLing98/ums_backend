package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name="subject_equivalent")
@Entity
public class SubjectEquivalent {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject1_id")
    private Subject subject1;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject2_id")
    private Subject subject2;
}
