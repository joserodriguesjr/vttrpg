package com.vttrpg.RPG.application.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Map;

public record FieldDataForm (
    @NotBlank(message = "Name is mandatory")
    String name,

    @NotNull(message = "replace is mandatory")
    Boolean replace,

    @NotEmpty(message = "Data is mandatory")
    List<Map<String, Object>> data
) {}
