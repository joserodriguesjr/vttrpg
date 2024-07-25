package com.vttrpg.RPG.application.form;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FieldForm {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotEmpty(message = "Columns must have at least one column")
    @Valid
    private List<Column> columns;

    @Getter
    @Setter
    public static class Column {
        @NotBlank(message = "column::name is mandatory")
        private String name;

        @NotBlank(message = "column::type is mandatory")
        private String type;
    }

}
