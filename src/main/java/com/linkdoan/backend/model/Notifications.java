package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="notifications")
@Data
public class Notifications {

    @Id
    @Column(columnDefinition = "INT", name="id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="data")
    private String data;
}
