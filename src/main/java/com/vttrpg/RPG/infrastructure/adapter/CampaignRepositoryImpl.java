package com.vttrpg.RPG.infrastructure.adapter;

import com.vttrpg.RPG.domain.mapper.CampaignMapper;
import com.vttrpg.RPG.domain.model.CampaignEntity;
import com.vttrpg.RPG.domain.repository.CampaignRepository;
import com.vttrpg.RPG.infrastructure.provider.CampaignPostgresRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CampaignRepositoryImpl implements CampaignRepository {

    private final CampaignPostgresRepository campaignRepository;
    private final CampaignMapper campaignMapper;

    @Override
    public CampaignEntity save(CampaignEntity campaign) {
        return campaignRepository.save(campaign);
    }

    @Override
    public Optional<CampaignEntity> findById(Long id) {
        return campaignRepository.findById(id);
    }

    @Override
    public List<CampaignEntity> findAll() {
        return campaignRepository.findAll().stream().toList();
    }

    @Override
    public boolean deleteById(Long id) {
        if (campaignRepository.existsById(id)) {
            campaignRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
