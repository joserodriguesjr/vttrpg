package com.vttrpg.RPG.application.controller;

import com.vttrpg.RPG.application.exception.CustomException;
import com.vttrpg.RPG.application.form.CreateFieldForm;
import com.vttrpg.RPG.application.form.Operation;
import com.vttrpg.RPG.application.form.UpdateFieldForm;
import com.vttrpg.RPG.application.validation.SchemaValidation;
import com.vttrpg.RPG.domain.mapper.FieldMapper;
import com.vttrpg.RPG.domain.model.Campaign;
import com.vttrpg.RPG.domain.model.Field;
import com.vttrpg.RPG.domain.services.CampaignService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.everit.json.schema.ValidationException;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@AllArgsConstructor
@RepositoryRestController("/campaigns")
@Tag(name = "Campaigns Controller", description = "Campaign Management Endpoints")
public class CampaignController {

    private CampaignService campaignService;
    private final FieldMapper fieldMapper;
    private SchemaValidation schemaValidation;

    @PostMapping(path= "/{id}/fields", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Campaign> createField(
            @PathVariable(value = "id") Long id,
            @RequestBody CreateFieldForm fieldForm) throws ValidationException {
        schemaValidation.validate(fieldForm);
        Field newField = fieldMapper.toDomain(fieldForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(campaignService.createField(id, newField));
    }

    @DeleteMapping(path= "/{id}/fields/{fieldName}")
    public ResponseEntity<?> deleteField(
            @PathVariable(value = "id") Long id,
            @PathVariable(value = "fieldName") String fieldName) {
        campaignService.deleteField(id, fieldName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

//  TODO: Map each enum value
    @PutMapping(path= "/{id}/fields/{fieldName}")
    public ResponseEntity<Campaign> updateField(
            @PathVariable(value = "id") Long id,
            @PathVariable(value = "fieldName") String fieldName,
            @RequestBody UpdateFieldForm fieldForm) throws ValidationException {
        schemaValidation.validate(fieldForm);
        Field newField = fieldMapper.toDomain(fieldForm);

        Campaign updatedCampaign;
        switch (fieldForm.operation()) {
            case RENAME_COLUMN:
                updatedCampaign = campaignService.updateField(id, fieldName, newField);
                break;
            case ADD_COLUMN:
                updatedCampaign = campaignService.addColumn(id, fieldName, newField);
                break;
            case REMOVE_COLUMN:
                updatedCampaign = campaignService.updateField(id, fieldName, newField);
                break;
            case ADD_DATA:
                updatedCampaign = campaignService.updateField(id, fieldName, newField);
                break;
            case REMOVE_DATA:
                updatedCampaign = campaignService.updateField(id, fieldName, newField);
                break;
            case CLEAN_DATA:
                updatedCampaign = campaignService.updateField(id, fieldName, newField);
                break;
            case null:
                throw new CustomException("You need to provide one of the supported 'operation' for this route: " + Arrays.toString(Operation.values()));
            default:
                throw new CustomException("Unsupported operation: " + fieldForm.operation());
        }

        return ResponseEntity.status(HttpStatus.OK).body(updatedCampaign);
    }

//    public Campaign addFieldToCampaign(Long campaignId, FieldDTO fieldDTO) {
//        Campaign campaign = campaignRepository.findById(campaignId)
//                .orElseThrow(() -> new RuntimeException("Campaign not found"));
//
//        // Convert fieldDTO to JSON string
//        String fieldsJson = campaign.getFields();
//        JsonNode fieldsNode = objectMapper.readTree(fieldsJson);
//
//        // Validate new field data
//        validateFieldData(fieldsNode, fieldDTO);
//
//        // Append the new field data
//        fieldsNode.path("fields").add(objectMapper.convertValue(fieldDTO, JsonNode.class));
//        campaign.setFields(fieldsNode.toString());
//
//        return campaignRepository.save(campaign);
//    }
//
//    @PutMapping("/{id}/fields")
//    public Campaign updateField(@PathVariable(value = "id") Long id, @Valid @RequestBody FieldForm fieldForm) {
//        Field updatedField = fieldMapper.toDomain(fieldForm);
//        return campaignService.updateFieldColumns(id, updatedField);
//    }
//
//    @PatchMapping("/{id}/fields")
//    public Campaign addDataToField(@PathVariable(value = "id") Long id, @Valid @RequestBody FieldDataForm fieldDataForm) {
//        Field updatedField = fieldMapper.toDomain(fieldDataForm);
//        boolean replace = fieldDataForm.replace();
//        return campaignService.updateFieldData(id, replace, updatedField);
//    }

//    @Autowired
//    private CampaignService campaignService;
//
//    @PostMapping("/{id}/fields")
//    public ResponseEntity<CampaignEntity> createField(@PathVariable Long id, @RequestBody Field newField) {
//        CampaignEntity campaign = campaignService.createField(id, newField);
//        return ResponseEntity.ok(campaign);
//    }
//
//    @PutMapping("/{id}/fields/columns")
//    public ResponseEntity<CampaignEntity> updateFieldColumns(@PathVariable Long id, @RequestBody Field updatedField) {
//        CampaignEntity campaign = campaignService.updateFieldColumns(id, updatedField);
//        return ResponseEntity.ok(campaign);
//    }
//
//    @PutMapping("/{id}/fields/data")
//    public ResponseEntity<CampaignEntity> updateFieldData(@PathVariable Long id, @RequestParam boolean replace, @RequestBody Field updatedField) {
//        CampaignEntity campaign = campaignService.updateFieldData(id, replace, updatedField);
//        return ResponseEntity.ok(campaign);
//    }

}
