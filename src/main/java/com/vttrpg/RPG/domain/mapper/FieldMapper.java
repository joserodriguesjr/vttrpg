package com.vttrpg.RPG.domain.mapper;

import com.vttrpg.RPG.application.form.FieldDataForm;
import com.vttrpg.RPG.application.form.FieldForm;
import com.vttrpg.RPG.domain.model.Field;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FieldMapper {

    @Mapping(target = "data", ignore = true)
    Field toDomain(FieldForm fieldForm);

    @Mapping(target = "columns", ignore = true)
    Field toDomain(FieldDataForm fieldDataForm);

}


