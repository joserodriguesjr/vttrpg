package com.vttrpg.RPG.domain.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class Field {
    private String name;

    private List<Column> columns = new ArrayList<>();

//    private List<DataItem> data = new ArrayList<>();
    private List<Map<String, Object>> data = new ArrayList<>();

    @Data
    public static class Column {
        private String name;
        private PossibleTypes type;
        public enum PossibleTypes {STRING, INTEGER, FLOAT}
    }

    @Data
    public static class DataItem {
        private String key;
        private String value;
    }
}