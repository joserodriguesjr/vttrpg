package com.vttrpg.RPG.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vttrpg.RPG.application.form.CampaignForm;
import com.vttrpg.RPG.domain.mapper.CampaignMapper;
import com.vttrpg.RPG.domain.mapper.FieldMapper;
import com.vttrpg.RPG.domain.model.Campaign;
import com.vttrpg.RPG.domain.services.CampaignService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CampaignController.class)
public class CampaignControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CampaignService campaignService;

    @MockBean
    private CampaignMapper campaignMapper;

    @MockBean
    private FieldMapper fieldMapper;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCreateCampaign() throws Exception {
        // Prepare the expected Campaign object
        Campaign campaign = new Campaign();
        campaign.setId("1"); // Mocked ID
        campaign.setName("testing");

        // Mock the behavior of campaignMapper and campaignService
        Mockito.when(campaignMapper.toDomain(any(CampaignForm.class))).thenReturn(campaign);
        Mockito.when(campaignService.createCampaign(any(Campaign.class))).thenReturn(campaign);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/campaigns")
                        .content(asJsonString(new CampaignForm("testing")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("testing"));
    }

//    @Test
//    public void testGetAllCampaigns() throws Exception {
//        Campaign campaign = new Campaign();
//        List<Campaign> campaigns = Collections.singletonList(campaign);
//
//        Mockito.when(campaignService.getAllCampaigns()).thenReturn(campaigns);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/campaigns"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].name").value("Test Campaign"));
//    }
//
//    @Test
//    public void testGetCampaignByID() throws Exception {
//        Campaign campaign = new Campaign();
//
//        Mockito.when(campaignService.getCampaignByID(anyString())).thenReturn(campaign);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/campaigns/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("Test Campaign"));
//    }
//
//    @Test
//    public void testDeleteCampaignByID() throws Exception {
//        Mockito.when(campaignService.deleteCampaignByID(anyString())).thenReturn(true);
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/campaigns/1"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testCreateField() throws Exception {
//        Field field = new Field();
//        Campaign campaign = new Campaign();
//
//        Mockito.when(fieldMapper.toDomain(any(FieldForm.class))).thenReturn(field);
//        Mockito.when(campaignService.createField(anyString(), any(Field.class))).thenReturn(campaign);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/campaigns/1/fields")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"fieldName\":\"Test Field\"}"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testUpdateField() throws Exception {
//        Field field = new Field();
//        Campaign campaign = new Campaign();
//
//        Mockito.when(fieldMapper.toDomain(any(FieldForm.class))).thenReturn(field);
//        Mockito.when(campaignService.updateFieldColumns(anyString(), any(Field.class))).thenReturn(campaign);
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/campaigns/1/fields")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"fieldName\":\"Updated Field\"}"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testAddDataToField() throws Exception {
////        Field field = new Field();
////        Campaign campaign = new Campaign();
////
////        Mockito.when(fieldMapper.toDomain(any(FieldDataForm.class))).thenReturn(field);
////        Mockito.when(campaignService.updateFieldData(anyString(), anyBoolean(), any(Field.class))).thenReturn(campaign);
//
//        mockMvc.perform(MockMvcRequestBuilders.patch("/api/campaigns/1/fields")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"data\":\"New Data\"}"))
//                .andExpect(status().isOk());
//    }
}
