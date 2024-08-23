package com.vttrpg.RPG.application.controller;

import com.vttrpg.RPG.application.form.CampaignForm;
import com.vttrpg.RPG.application.form.FieldDataForm;
import com.vttrpg.RPG.application.form.FieldForm;
import com.vttrpg.RPG.domain.mapper.CampaignMapper;
import com.vttrpg.RPG.domain.mapper.FieldMapper;
import com.vttrpg.RPG.domain.model.CampaignEntity;
import com.vttrpg.RPG.domain.model.Field;
import com.vttrpg.RPG.domain.services.CampaignService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/campaigns")
public class CampaignController {

    private CampaignService campaignService;
    private final CampaignMapper campaignMapper;
    private final FieldMapper fieldMapper;

//    @PostMapping(path= "/", consumes = "application/json", produces = "application/json")
    @PostMapping
    public CampaignEntity createCampaign(@Valid @RequestBody CampaignForm campaignForm) {
        CampaignEntity campaign = campaignMapper.toDomain(campaignForm);
        return campaignService.createCampaign(campaign);
    }

    @GetMapping
    public List<CampaignEntity> getAllCampaigns() {
        return campaignService.getAllCampaigns();
    }

    @GetMapping("/{id}")
    public CampaignEntity getCampaignByID(@PathVariable(value = "id") Long id) {
        return campaignService.getCampaignByID(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCampaignByID(@PathVariable(value = "id") Long id) {
        boolean isDeleted = campaignService.deleteCampaignByID(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/fields")
    public CampaignEntity createField(@PathVariable(value = "id") Long id, @Valid @RequestBody FieldForm fieldForm) {
        Field newField = fieldMapper.toDomain(fieldForm);
        return campaignService.createField(id, newField);
    }

    @PutMapping("/{id}/fields")
    public CampaignEntity updateField(@PathVariable(value = "id") Long id, @Valid @RequestBody FieldForm fieldForm) {
        Field updatedField = fieldMapper.toDomain(fieldForm);
        return campaignService.updateFieldColumns(id, updatedField);
    }

    @PatchMapping("/{id}/fields")
    public CampaignEntity addDataToField(@PathVariable(value = "id") Long id, @Valid @RequestBody FieldDataForm fieldDataForm) {
        Field updatedField = fieldMapper.toDomain(fieldDataForm);
        boolean replace = fieldDataForm.replace();
        return campaignService.updateFieldData(id, replace, updatedField);
    }

}
