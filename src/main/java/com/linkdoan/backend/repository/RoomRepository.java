package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, String> {
    Optional<Room> findFirstByRoomId(@Param("roomId") String roomId);

}
