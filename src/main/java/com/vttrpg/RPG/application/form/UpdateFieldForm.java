package com.vttrpg.RPG.application.form;

import java.util.List;
import java.util.Map;

public record UpdateFieldForm(
        String name,
        List<Column> columns,
        List<Map<String, Object>> data,
        Operation operation
) implements FieldForm {
    public record Column(
            String name,
            String type
    ) {}
}

