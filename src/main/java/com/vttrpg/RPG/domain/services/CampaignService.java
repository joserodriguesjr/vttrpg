package com.vttrpg.RPG.domain.services;

import com.vttrpg.RPG.domain.model.Campaign;
import com.vttrpg.RPG.domain.model.Field;
import com.vttrpg.RPG.domain.repository.CampaignRepository;
import com.vttrpg.RPG.application.exception.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CampaignService {

    private CampaignRepository campaignRepository;

    public Campaign createCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    public Campaign getCampaignByID(String id) {
        return campaignRepository.findById(id).orElseThrow(() -> new CustomException("Campaign not found"));
    }

    public boolean deleteCampaignByID(String id) {
        return campaignRepository.deleteById(id);
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

    public Campaign updateFieldData(String id, boolean replace, Field updatedField) {
        Campaign campaign = this.getCampaignByID(id);

        Optional<Field> existingFieldOpt = campaign.getFields().stream()
                .filter(field -> field.getName().equals(updatedField.getName()))
                .findFirst();

        if (existingFieldOpt.isEmpty()) {
            throw new CustomException("Field not found");
        }

        Field existingField = existingFieldOpt.get();

        // if 'replace' true -> just overwrite all data
        // else -> iterate over both datas (existing and new)
        // | if key already exists -> updates its values
        // | else -> create new data
        if (replace) {
            existingField.setData(updatedField.getData());
        } else {
            List<Map<String, Object>> existingData = existingField.getData();
            List<Map<String, Object>> newData = updatedField.getData();

            if (existingData != null && newData != null) {
                for (Map<String, Object> newElement : newData) {
                    boolean found = false;
                    for (int i = 0; i < existingData.size(); i++) {
                        if (found) {
                            break;
                        }

                        Map<String, Object> existingElement = existingData.get(i);
                        String newElementKey = newElement.keySet().stream().toList().getFirst();
                        if (existingElement.containsKey(newElementKey)) {
                            existingData.set(i, newElement);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        existingData.add(newElement);
                    }
                }
            } else if (existingData == null) {
                existingField.setData(newData);
            } // If newData is null, do nothing
        }

        return campaignRepository.save(campaign);
    }

}


