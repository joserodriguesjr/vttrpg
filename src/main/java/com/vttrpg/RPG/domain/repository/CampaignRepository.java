package com.vttrpg.RPG.domain.repository;

import com.vttrpg.RPG.domain.model.CampaignEntity;

import java.util.List;
import java.util.Optional;

public interface CampaignRepository {
    CampaignEntity save(CampaignEntity campaign);

    Optional<CampaignEntity> findById(Long id);

    List<CampaignEntity> findAll();

    boolean deleteById(Long id);
}