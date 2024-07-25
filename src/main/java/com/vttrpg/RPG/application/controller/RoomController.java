package com.vttrpg.RPG.application.controller;

import com.vttrpg.RPG.domain.model.Room;
import com.vttrpg.RPG.domain.services.RoomService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/rooms")
public class RoomController {

    private RoomService roomService;

    @PostMapping
    public Room createRoom(@Valid @RequestBody Room room) {
        return roomService.createRoom(room);
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

}
