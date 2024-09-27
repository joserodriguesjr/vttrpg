package com.vttrpg.RPG.domain.mapper;

import com.vttrpg.RPG.application.form.CreateFieldForm;
import com.vttrpg.RPG.application.form.UpdateFieldForm;
import com.vttrpg.RPG.domain.model.Field;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FieldMapper {

    Field toDomain(CreateFieldForm fieldForm);

    Field toDomain(UpdateFieldForm fieldForm);

}
