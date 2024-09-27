package com.vttrpg.RPG.domain.model.projection;

import com.vttrpg.RPG.domain.model.Campaign;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "campaignNameOnly", types = { Campaign.class })
public interface CampaignNameOnly {
    String getName();
}
