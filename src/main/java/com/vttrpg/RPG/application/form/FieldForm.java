package com.vttrpg.RPG.application.form;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record FieldForm(
        @NotBlank(message = "Name is mandatory")
        String name,

        @NotEmpty(message = "Columns must have at least one column")
        @Valid
        List<Column> columns
) {
    public record Column(
            @NotBlank(message = "column::name is mandatory")
            String name,

            @NotBlank(message = "column::type is mandatory")
            String type
    ) {}
}