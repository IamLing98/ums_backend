package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.RoomDTO;
import com.linkdoan.backend.model.Room;
import com.linkdoan.backend.repository.RoomRepository;
import com.linkdoan.backend.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Override
    public List<RoomDTO> getAll() {
        List<Room> roomList = new ArrayList<>();
        roomList = roomRepository.findAll();
        List<RoomDTO> roomDTOList = new ArrayList<>();
        for(Room room : roomList){
            roomDTOList.add(room.toDTO());
        }
        return roomDTOList;
    }

    @Override
    public int create(RoomDTO roomDTO) {
        Optional<Room> roomOptional = roomRepository.findFirstByRoomId(roomDTO.getRoomId());
        if(roomOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Đã tồn tại");
        }else roomRepository.save(roomDTO.toModel());
        return 1;
    }

    @Override
    public int update(String roomId, RoomDTO roomDTO) {
        Optional<Room> roomOptional = roomRepository.findFirstByRoomId(roomDTO.getRoomId());
        if(!roomOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Không tồn tại");
        }else roomRepository.save(roomDTO.toModel());
        return 1;
    }

    @Override
    public int delete(String roomId) {
        Optional<Room> roomOptional = roomRepository.findFirstByRoomId(roomId);
        if(!roomOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Không tồn tại");
        }else roomRepository.deleteById(roomId);
        return 1;
    }
}
