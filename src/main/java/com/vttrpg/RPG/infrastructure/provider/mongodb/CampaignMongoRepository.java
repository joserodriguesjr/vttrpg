package com.vttrpg.RPG.infrastructure.provider.mongodb;

import com.vttrpg.RPG.domain.model.Campaign;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CampaignMongoRepository extends MongoRepository<Campaign, String> {
}
