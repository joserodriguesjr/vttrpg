package com.vttrpg.RPG.infrastructure.provider.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CampaignMongoRepository extends MongoRepository<CampaignDocument, String> {
}
