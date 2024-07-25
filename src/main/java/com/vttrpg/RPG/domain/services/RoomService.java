package com.vttrpg.RPG.domain.services;

import com.vttrpg.RPG.domain.model.Room;
import com.vttrpg.RPG.domain.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomService {

    private RoomRepository repository;

    public Room createRoom(Room room) {
        return repository.save(room);
    }

    public List<Room> getAllRooms() {
        return repository.findAll();
    }

}
