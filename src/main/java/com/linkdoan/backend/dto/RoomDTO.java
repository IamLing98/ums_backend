package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.Room;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class RoomDTO {
    private String roomId;

    private  Integer numberOfSeats;

    private  Integer isLab;

    public Room toModel(){
        Room room = new Room();
        room.setIsLab(this.isLab);
        room.setNumberOfSeats(this.numberOfSeats);
        room.setRoomId(this.roomId);
        return room;
    }
}
