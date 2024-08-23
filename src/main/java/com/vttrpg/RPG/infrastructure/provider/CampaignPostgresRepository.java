package com.vttrpg.RPG.infrastructure.provider;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignPostgresRepository extends JpaRepository<CampaignEntity, String> {
}
