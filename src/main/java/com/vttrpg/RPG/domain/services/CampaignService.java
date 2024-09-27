package com.vttrpg.RPG.domain.services;

import com.vttrpg.RPG.application.exception.CustomException;
import com.vttrpg.RPG.domain.model.Campaign;
import com.vttrpg.RPG.domain.model.Field;
import com.vttrpg.RPG.domain.repository.CampaignRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CampaignService {

    private CampaignRepository campaignRepository;

//    private void validateFieldData(JsonNode existingFields, FieldDTO newField) {
//        // Convert existing fields to a JSON string and then to a JSONObject
//        String existingFieldsJson = existingFields.toString();
//        JSONObject jsonSchemaObject = new JSONObject();
//
//        // Construct the schema based on existing field columns
//        jsonSchemaObject.put("$schema", "http://json-schema.org/draft-07/schema#");
//        jsonSchemaObject.put("type", "object");
//
//        JSONObject properties = new JSONObject();
//        for (ColumnDTO column : newField.getColumns()) {
//            JSONObject columnSchema = new JSONObject();
//            if ("STRING".equalsIgnoreCase(column.getType())) {
//                columnSchema.put("type", "string");
//            } else if ("FLOAT".equalsIgnoreCase(column.getType())) {
//                columnSchema.put("type", "number");
//            } else if ("INTEGER".equalsIgnoreCase(column.getType())) {
//                columnSchema.put("type", "integer");
//            }
//            properties.put(column.getName(), columnSchema);
//        }
//        jsonSchemaObject.put("properties", properties);
//
//        // Add required fields
//        JSONObject required = new JSONObject();
//        for (ColumnDTO column : newField.getColumns()) {
//            required.put(column.getName(), column.getName());
//        }
//        jsonSchemaObject.put("required", required.toList());
//
//        // Create the schema
//        Schema schema = SchemaLoader.load(jsonSchemaObject);
//
//        // Convert new field data to JSON
//        JSONObject newFieldDataJson = new JSONObject();
//        for (FieldDataDTO data : newField.getData()) {
//            for (String key : data.getData().keySet()) {
//                newFieldDataJson.put(key, data.getData().get(key));
//            }
//        }
//
//        // Validate the new field data against the schema
//        try {
//            schema.validate(newFieldDataJson);
//        } catch (ValidationException e) {
//            // Handle validation exception
//            throw new RuntimeException("Invalid field data: " + e.getMessage());
//        }
//    }

    public Campaign createField(Long id, Field newField) {
        Optional<Campaign> campaignOptional = campaignRepository.findById(id);
        if (campaignOptional.isEmpty()) {
            throw new CustomException("Campaign with id " + id + " does not exist");
        }
        Campaign campaign = campaignOptional.get();

        boolean fieldExists = campaign.getFields().stream()
                .anyMatch(field -> field.getName().equalsIgnoreCase(newField.getName()));
        if (fieldExists) {
            throw new CustomException("Field already exists.");
        }

        campaign.getFields().add(newField);
        return campaignRepository.save(campaign);
    }

    public void deleteField(Long id, String fieldName) {
        Optional<Campaign> campaignOptional = campaignRepository.findById(id);
        if (campaignOptional.isEmpty()) {
            throw new CustomException("Campaign with id " + id + " does not exist");
        }
        Campaign campaign = campaignOptional.get();

        boolean fieldExists = campaign.getFields().stream()
                .anyMatch(field -> field.getName().equalsIgnoreCase(fieldName));
        if (!fieldExists) {
            throw new CustomException("Field does not exist.");
        }

        campaign.setFields(campaign.getFields().stream().filter((field -> !field.getName().equalsIgnoreCase(fieldName))).toList());
        campaignRepository.save(campaign);
    }

    public Campaign updateField(Long id, String fieldName, Field newField) {
        Optional<Campaign> campaignOptional = campaignRepository.findById(id);
        if (campaignOptional.isEmpty()) {
            throw new CustomException("Campaign with id " + id + " does not exist");
        }
        Campaign campaign = campaignOptional.get();

//        boolean fieldExists = campaign.getFields().stream()
//                .anyMatch(field -> field.getName().equalsIgnoreCase(fieldName));
//        if (!fieldExists) {
//            throw new CustomException("Field does not exist.");
//        }
//        List<Field> updatedFields = campaign.getFields().stream()
//                .filter(field -> !field.getName().equalsIgnoreCase(fieldName))
//                .collect(Collectors.toCollection(ArrayList::new));
//        updatedFields.add(newField);
//        campaign.setFields(updatedFields);

        Optional<Field> existingFieldOpt = campaign.getFields().stream()
                .filter(field -> field.getName().equals(fieldName))
                .findFirst();
        if (existingFieldOpt.isEmpty()) {
            throw new CustomException("Field does not exist.");
        }
        Field existingField = existingFieldOpt.get();

        existingField.setColumns(newField.getColumns());
        return campaignRepository.save(campaign);
    }

    public Campaign addColumn(Long id, String fieldName, Field newField) {
        Optional<Campaign> campaignOptional = campaignRepository.findById(id);
        if (campaignOptional.isEmpty()) {
            throw new CustomException("Campaign with id " + id + " does not exist");
        }
        Campaign campaign = campaignOptional.get();

        Optional<Field> existingFieldOpt = campaign.getFields().stream()
                .filter(field -> field.getName().equals(fieldName))
                .findFirst();
        if (existingFieldOpt.isEmpty()) {
            throw new CustomException("Field does not exist.");
        }
        Field existingField = existingFieldOpt.get();

        // For each new column, check if it already exists in the existing field
        newField.getColumns().forEach(newColumn -> {
            boolean columnExists = existingField.getColumns().stream()
                    .anyMatch(existingColumn -> existingColumn.getName().equals(newColumn.getName()));

            if (columnExists) {
                throw new CustomException("Column '" + newColumn.getName() + "' already exists in the field.");
            } else {
                // If the column doesn't exist, add it
                existingField.getColumns().add(newColumn);
            }
        });

        return campaignRepository.save(campaign);
    }

//
//    public Campaign updateFieldData(Long id, boolean replace, Field updatedField) {
//        Campaign campaign = this.getCampaignByID(id);
//
//        Optional<Field> existingFieldOpt = campaign.getFields().stream()
//                .filter(field -> field.getName().equals(updatedField.getName()))
//                .findFirst();
//
//        if (existingFieldOpt.isEmpty()) {
//            throw new CustomException("Field not found");
//        }
//
//        Field existingField = existingFieldOpt.get();
//
//        // if 'replace' true -> just overwrite all data
//        // else -> iterate over both datas (existing and new)
//        // | if key already exists -> updates its values
//        // | else -> create new data
//        if (replace) {
//            existingField.setData(updatedField.getData());
//        } else {
//            List<Map<String, Object>> existingData = existingField.getData();
//            List<Map<String, Object>> newData = updatedField.getData();
//
//            if (existingData != null && newData != null) {
//                for (Map<String, Object> newElement : newData) {
//                    boolean found = false;
//                    for (int i = 0; i < existingData.size(); i++) {
//                        if (found) {
//                            break;
//                        }
//
//                        Map<String, Object> existingElement = existingData.get(i);
//                        String newElementKey = newElement.keySet().stream().toList().getFirst();
//                        if (existingElement.containsKey(newElementKey)) {
//                            existingData.set(i, newElement);
//                            found = true;
//                            break;
//                        }
//                    }
//                    if (!found) {
//                        existingData.add(newElement);
//                    }
//                }
//            } else if (existingData == null) {
//                existingField.setData(newData);
//            } // If newData is null, do nothing
//        }
//
//        return campaignRepository.save(campaign);
//    }

}


