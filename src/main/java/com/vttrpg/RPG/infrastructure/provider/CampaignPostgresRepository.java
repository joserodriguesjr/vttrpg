package com.vttrpg.RPG.infrastructure.provider;

import com.vttrpg.RPG.domain.model.CampaignEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignPostgresRepository extends JpaRepository<CampaignEntity, Long> {
}
