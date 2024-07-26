package com.vttrpg.RPG.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Campaign {
    private String id;
    private String name;
    private List<Field> fields = new ArrayList<>();
    private Date createdDate;
    private Date lastModifiedDate;
}