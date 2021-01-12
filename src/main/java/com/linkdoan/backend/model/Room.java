package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.RoomDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="room")
@Data
public class Room {
    @Id
    @Column (name="room_id", columnDefinition = "CHAR(10)")
    private String roomId;

    @Column (name="number_of_seats", columnDefinition = "INT")
    private  Integer numberOfSeats;

    @Column (columnDefinition = "INT")
    private  Integer isLab;

    public RoomDTO toDTO(){
        RoomDTO room = new RoomDTO();
        room.setIsLab(this.isLab);
        room.setNumberOfSeats(this.numberOfSeats);
        room.setRoomId(this.roomId);
        return room;
    }
}
