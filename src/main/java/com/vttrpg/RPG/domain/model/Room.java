package com.vttrpg.RPG.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name may not be null")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @Column(name = "players_id")
    private List<String> playersId;

}
