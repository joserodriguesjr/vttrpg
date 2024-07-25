package com.vttrpg.RPG.domain.model;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "campaigns")
public class Campaign {
    @Id
    private String id;
    private String name;
    private List<Field> fields;
}