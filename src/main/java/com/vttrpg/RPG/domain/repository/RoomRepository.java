package com.vttrpg.RPG.domain.repository;

import com.vttrpg.RPG.domain.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
