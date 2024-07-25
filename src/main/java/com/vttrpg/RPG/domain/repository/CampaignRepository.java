package com.vttrpg.RPG.domain.repository;

import com.vttrpg.RPG.domain.model.Campaign;

import java.util.List;
import java.util.Optional;

public interface CampaignRepository {
    Campaign save(Campaign campaign);

    Optional<Campaign> findById(String id);

    List<Campaign> findAll();

    void deleteById(String id);
}