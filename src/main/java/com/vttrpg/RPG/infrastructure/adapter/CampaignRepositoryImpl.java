package com.vttrpg.RPG.infrastructure.adapter;

import com.vttrpg.RPG.domain.mapper.CampaignMapper;
import com.vttrpg.RPG.domain.model.Campaign;
import com.vttrpg.RPG.domain.repository.CampaignRepository;
import com.vttrpg.RPG.infrastructure.provider.CampaignEntity;
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
    public Campaign save(Campaign campaign) {
        CampaignEntity campaignDocument = campaignMapper.toInfrastructure(campaign);
        CampaignEntity savedCampaignDocument = campaignRepository.save(campaignDocument);
        return campaignMapper.toDomain(savedCampaignDocument);
    }

    @Override
    public Optional<Campaign> findById(String id) {
        return campaignRepository.findById(id).map(campaignMapper::toDomain);
    }

    @Override
    public List<Campaign> findAll() {
        return campaignRepository.findAll().stream().map(campaignMapper::toDomain).toList();
    }

    @Override
    public boolean deleteById(String id) {
        if (campaignRepository.existsById(id)) {
            campaignRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
