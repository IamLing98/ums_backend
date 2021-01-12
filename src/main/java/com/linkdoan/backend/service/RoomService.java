package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.RoomDTO;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@ComponentScan(basePackages = {"com.linkdoan.backend.*"})
public interface RoomService {
    List<RoomDTO> getAll();

    int create(RoomDTO roomDTO);

    int update(String roomId, RoomDTO roomDTO);

    int delete(String roomId);
}
