package com.vttrpg.RPG.domain.mapper;

import com.vttrpg.RPG.application.form.CampaignForm;
import com.vttrpg.RPG.domain.model.Campaign;
import com.vttrpg.RPG.infrastructure.provider.mongodb.CampaignDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CampaignMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fields", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Campaign toDomain(CampaignForm campaignForm);

    Campaign toDomain(CampaignDocument campaignDocument);

    CampaignDocument toInfrastructure(Campaign campaign);

}

