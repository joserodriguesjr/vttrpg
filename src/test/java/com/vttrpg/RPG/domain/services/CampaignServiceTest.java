package com.vttrpg.RPG.domain.services;

import com.vttrpg.RPG.application.exception.CustomException;
import com.vttrpg.RPG.domain.model.Campaign;
import com.vttrpg.RPG.domain.model.Field;
import com.vttrpg.RPG.domain.repository.CampaignRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CampaignServiceTest {

    @Mock
    private CampaignRepository campaignRepository;

    @InjectMocks
    private CampaignService campaignService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCampaign() {
        Campaign campaign = new Campaign();
        campaign.setName("Test Campaign");
        when(campaignRepository.save(any(Campaign.class))).thenReturn(campaign);

        Campaign createdCampaign = campaignService.createCampaign(campaign);

        assertNotNull(createdCampaign);
        assertEquals("Test Campaign", createdCampaign.getName());
        verify(campaignRepository, times(1)).save(campaign);
    }

    @Test
    public void testGetAllCampaigns() {
        List<Campaign> campaigns = new ArrayList<>();
        campaigns.add(new Campaign());
        when(campaignRepository.findAll()).thenReturn(campaigns);

        List<Campaign> result = campaignService.getAllCampaigns();

        assertEquals(1, result.size());
        verify(campaignRepository, times(1)).findAll();
    }

    @Test
    public void testGetCampaignByID_Found() {
        Campaign campaign = new Campaign();
        campaign.setName("Test Campaign");
        when(campaignRepository.findById(1L)).thenReturn(Optional.of(campaign));

        Campaign result = campaignService.getCampaignByID(1L);

        assertNotNull(result);
        assertEquals("Test Campaign", result.getName());
    }

    @Test
    public void testGetCampaignByID_NotFound() {
        when(campaignRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> campaignService.getCampaignByID(1L));
    }

//    @Test
//    public void testDeleteCampaignByID() {
//        when(campaignRepository.deleteById(1L)).thenReturn(true);
//
//        boolean result = campaignService.deleteCampaignByID(1L);
//
//        assertTrue(result);
//        verify(campaignRepository, times(1)).deleteById(1L);
//    }

    @Test
    public void testCreateField_Success() {
        Campaign campaign = new Campaign();
        campaign.setId(1L);
        campaign.setFields(new ArrayList<>());

        Field newField = new Field();
        newField.setName("Field 1");

        when(campaignRepository.findById(1L)).thenReturn(Optional.of(campaign));
        when(campaignRepository.save(any(Campaign.class))).thenReturn(campaign);

        Campaign updatedCampaign = campaignService.createField(1L, newField);

        assertNotNull(updatedCampaign);
        assertEquals(1, updatedCampaign.getFields().size());
        assertEquals("Field 1", updatedCampaign.getFields().getFirst().getName());
    }

    @Test
    public void testCreateField_FieldExists() {
        Campaign campaign = new Campaign();
        campaign.setId(1L);
        Field existingField = new Field();
        existingField.setName("Field 1");
        campaign.setFields(List.of(existingField));

        Field newField = new Field();
        newField.setName("Field 1");

        when(campaignRepository.findById(1L)).thenReturn(Optional.of(campaign));

        assertThrows(CustomException.class, () -> campaignService.createField(1L, newField));
    }

    @Test
    public void testUpdateFieldColumns_Success() {
        Campaign campaign = new Campaign();
        campaign.setId(1L);

        Field existingField = new Field();
        existingField.setName("Field 1");
        existingField.setColumns(new ArrayList<>());

        campaign.setFields(List.of(existingField));

        Field updatedField = new Field();
        Field.Column column1 = new Field.Column();
        Field.Column column2 = new Field.Column();

        updatedField.setName("Field 1");
        updatedField.setColumns(List.of(column1, column2));

        when(campaignRepository.findById(1L)).thenReturn(Optional.of(campaign));
        when(campaignRepository.save(any(Campaign.class))).thenReturn(campaign);

        Campaign updatedCampaign = campaignService.updateFieldColumns(1L, updatedField);

        assertNotNull(updatedCampaign);
        assertEquals(2, updatedCampaign.getFields().getFirst().getColumns().size());
    }

    @Test
    public void testUpdateFieldColumns_FieldNotFound() {
        Campaign campaign = new Campaign();
        campaign.setId(1L);
        campaign.setFields(new ArrayList<>());

        Field updatedField = new Field();
        updatedField.setName("Field 1");

        when(campaignRepository.findById(1L)).thenReturn(Optional.of(campaign));

        assertThrows(CustomException.class, () -> campaignService.updateFieldColumns(1L, updatedField));
    }

    @Test
    public void testUpdateFieldData_ReplaceTrue() {
        Campaign campaign = new Campaign();
        campaign.setId(1L);

        Field existingField = new Field();
        existingField.setName("Field 1");
        existingField.setData(new ArrayList<>());

        campaign.setFields(List.of(existingField));

        Field updatedField = new Field();
        updatedField.setName("Field 1");
        updatedField.setData(List.of(Map.of("Key1", "Value1")));

        when(campaignRepository.findById(1L)).thenReturn(Optional.of(campaign));
        when(campaignRepository.save(any(Campaign.class))).thenReturn(campaign);

        Campaign updatedCampaign = campaignService.updateFieldData(1L, true, updatedField);

        assertNotNull(updatedCampaign);
        assertEquals(1, updatedCampaign.getFields().getFirst().getData().size());
    }

    @Test
    public void testUpdateFieldData_ReplaceFalse() {
        Campaign campaign = new Campaign();
        campaign.setId(1L);

        Field existingField = new Field();
        existingField.setName("Field 1");
        existingField.setData(new ArrayList<>());

        campaign.setFields(List.of(existingField));

        Field updatedField = new Field();
        updatedField.setName("Field 1");
        updatedField.setData(List.of(Map.of("Key1", "Value1")));

        when(campaignRepository.findById(1L)).thenReturn(Optional.of(campaign));
        when(campaignRepository.save(any(Campaign.class))).thenReturn(campaign);

        Campaign updatedCampaign = campaignService.updateFieldData(1L, false, updatedField);

        assertNotNull(updatedCampaign);
        assertEquals(1, updatedCampaign.getFields().getFirst().getData().size());
    }

    @Test
    public void testUpdateFieldData_FieldNotFound() {
        Campaign campaign = new Campaign();
        campaign.setId(1L);
        campaign.setFields(new ArrayList<>());

        Field updatedField = new Field();
        updatedField.setName("Field 1");

        when(campaignRepository.findById(1L)).thenReturn(Optional.of(campaign));

        assertThrows(CustomException.class, () -> campaignService.updateFieldData(1L, true, updatedField));
    }

}
