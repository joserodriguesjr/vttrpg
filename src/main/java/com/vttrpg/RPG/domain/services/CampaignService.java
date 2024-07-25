package com.vttrpg.RPG.domain.services;

import com.vttrpg.RPG.domain.model.Campaign;
import com.vttrpg.RPG.domain.model.Field;
import com.vttrpg.RPG.domain.repository.CampaignRepository;
import com.vttrpg.RPG.application.exception.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CampaignService {

    private CampaignRepository campaignRepository;

    public Campaign createCampaign(Campaign campaign) {
//        todo: isso é logica de negocio?
//        Campaign campaign = new Campaign();
//        campaign.setName(campaignName);
//        campaign.setFields(new ArrayList<>());

        return campaignRepository.save(campaign);
    }

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    public Campaign getCampaignByID(String id) {
        Optional<Campaign> campaign = campaignRepository.findById(id);
        if (campaign.isEmpty()) {
            throw new CustomException("Campaign not found");
        }
        return campaign.get();
    }

    public void deleteCampaignByID(String id) {
        campaignRepository.deleteById(id);
    }

    public Campaign createField(String id, Field newField) {
        Campaign campaign = this.getCampaignByID(id);

        boolean fieldExists = campaign.getFields().stream()
                .anyMatch(field -> field.getName().equalsIgnoreCase(newField.getName()));

        if (fieldExists) {
            throw new CustomException("Field already exists.");
        }

        campaign.getFields().add(newField);

        return campaignRepository.save(campaign);
    }

    public Campaign updateFieldColumns(String id, Field updatedField) {
        Campaign campaign = this.getCampaignByID(id);

        Optional<Field> existingFieldOpt = campaign.getFields().stream()
                .filter(field -> field.getName().equals(updatedField.getName()))
                .findFirst();

        if (existingFieldOpt.isEmpty()) {
            throw new CustomException("Field not found");
        }

        Field existingField = existingFieldOpt.get();
        existingField.setColumns(updatedField.getColumns());

        return campaignRepository.save(campaign);
    }

    public Campaign updateFieldData(String id, Field updatedField) {
        // todo: make incremental
        Campaign campaign = this.getCampaignByID(id);

        Optional<Field> existingFieldOpt = campaign.getFields().stream()
                .filter(field -> field.getName().equals(updatedField.getName()))
                .findFirst();

        if (existingFieldOpt.isEmpty()) {
            throw new CustomException("Field not found");
        }

        Field existingField = existingFieldOpt.get();
        existingField.setData(updatedField.getData());

        return campaignRepository.save(campaign);
    }

}


