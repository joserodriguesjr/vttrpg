package com.vttrpg.RPG.application.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class FieldDataForm {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotEmpty(message = "Data is mandatory")
    private List<Map<String, Object>> data;

}
