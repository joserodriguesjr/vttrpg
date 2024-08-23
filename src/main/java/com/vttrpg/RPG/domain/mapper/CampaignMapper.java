package com.vttrpg.RPG.domain.mapper;

import com.vttrpg.RPG.application.form.CampaignForm;
import com.vttrpg.RPG.domain.model.CampaignEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CampaignMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fields", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    CampaignEntity toDomain(CampaignForm campaignForm);

}
