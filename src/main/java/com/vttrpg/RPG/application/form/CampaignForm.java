package com.vttrpg.RPG.application.form;

import jakarta.validation.constraints.NotBlank;

public record CampaignForm(
        @NotBlank(message = "Name is mandatory")
        String name
) {}
