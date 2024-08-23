package com.vttrpg.RPG.domain.services;

import com.vttrpg.RPG.application.exception.CustomException;
import com.vttrpg.RPG.domain.model.CampaignEntity;
import com.vttrpg.RPG.domain.model.Field;
import com.vttrpg.RPG.domain.repository.CampaignRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CampaignService {

    private CampaignRepository campaignRepository;

    public CampaignEntity createCampaign(CampaignEntity campaign) {
        return campaignRepository.save(campaign);
    }

    public List<CampaignEntity> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    public CampaignEntity getCampaignByID(Long id) {
        return campaignRepository.findById(id).orElseThrow(() -> new CustomException("Campaign not found"));
    }

    public boolean deleteCampaignByID(Long id) {
        return campaignRepository.deleteById(id);
    }

    public CampaignEntity createField(Long id, Field newField) {
        CampaignEntity campaign = this.getCampaignByID(id);

        boolean fieldExists = campaign.getFields().stream()
                .anyMatch(field -> field.getName().equalsIgnoreCase(newField.getName()));

        if (fieldExists) {
            throw new CustomException("Field already exists.");
        }

        campaign.getFields().add(newField);

        return campaignRepository.save(campaign);
    }

    public CampaignEntity updateFieldColumns(Long id, Field updatedField) {
        CampaignEntity campaign = this.getCampaignByID(id);

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

    public CampaignEntity updateFieldData(Long id, boolean replace, Field updatedField) {
        CampaignEntity campaign = this.getCampaignByID(id);

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


