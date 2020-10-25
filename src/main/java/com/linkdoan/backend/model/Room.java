package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="room")
@Data
public class Room {

    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @Id
    @Column (name="room_id", columnDefinition = "CHAR(10)")
    private String roomId;

    @Column (name="number_of_seats", columnDefinition = "INT")
    private  Integer numberOfSeats;

    @Column (columnDefinition = "INT")
    private  Integer isLab;

}
