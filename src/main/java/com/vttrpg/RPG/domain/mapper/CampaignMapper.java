package com.vttrpg.RPG.domain.mapper;

import com.vttrpg.RPG.application.form.CampaignForm;
import com.vttrpg.RPG.domain.model.Campaign;
import com.vttrpg.RPG.domain.model.Field;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;


@Mapper(componentModel = "spring")
public interface CampaignMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fields", expression = "java(resolveFields(campaignForm))")
    Campaign toDomain(CampaignForm campaignForm);

    default List<Field> resolveFields(final CampaignForm campaignForm) {
        return new ArrayList<>();
    }

}

