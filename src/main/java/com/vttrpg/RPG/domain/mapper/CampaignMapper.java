package com.vttrpg.RPG.domain.mapper;

import com.vttrpg.RPG.application.form.CampaignForm;
import com.vttrpg.RPG.domain.model.Campaign;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CampaignMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fields", ignore = true)
    Campaign toDomain(CampaignForm campaignForm);


}

