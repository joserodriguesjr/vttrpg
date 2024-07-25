package com.vttrpg.RPG.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Field {
    private String name;
    private List<Column> columns;
    private List<Map<String, Object>> data;

    @Getter
    @Setter
    public static class Column {
        private String name;
        private String type;
    }

}