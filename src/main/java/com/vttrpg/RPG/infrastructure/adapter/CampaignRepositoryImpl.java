package com.vttrpg.RPG.infrastructure.adapter;

import com.vttrpg.RPG.domain.mapper.CampaignMapper;
import com.vttrpg.RPG.domain.model.Campaign;
import com.vttrpg.RPG.domain.repository.CampaignRepository;
import com.vttrpg.RPG.infrastructure.provider.mongodb.CampaignMongoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CampaignRepositoryImpl implements CampaignRepository {

    private final CampaignMongoRepository campaignMongoRepository;
    private final CampaignMapper campaignMapper;

    @Override
    public Campaign save(Campaign campaign) {
        return campaignMapper.toDomain(campaignMongoRepository.save(campaignMapper.toInfrastructure(campaign)));
    }

    @Override
    public Optional<Campaign> findById(String id) {
        return campaignMongoRepository.findById(id).map(campaignMapper::toDomain);
    }

    @Override
    public List<Campaign> findAll() {
        return campaignMongoRepository.findAll().stream().map(campaignMapper::toDomain).toList();
    }

    @Override
    public boolean deleteById(String id) {
        if (campaignMongoRepository.existsById(id)) {
            campaignMongoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
