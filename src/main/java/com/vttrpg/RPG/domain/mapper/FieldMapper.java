package com.vttrpg.RPG.domain.mapper;

import com.vttrpg.RPG.application.form.FieldDataForm;
import com.vttrpg.RPG.application.form.FieldForm;
import com.vttrpg.RPG.domain.model.Field;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface FieldMapper {

    @Mapping(target = "data", expression = "java(resolveData(fieldForm))")
    Field toDomain(FieldForm fieldForm);

    @Mapping(target = "columns", ignore = true)
    Field toDomain(FieldDataForm fieldDataForm);

    default List<Map<String, Object>> resolveData(final FieldForm fieldForm) {
        return new ArrayList<>();
    }

}


