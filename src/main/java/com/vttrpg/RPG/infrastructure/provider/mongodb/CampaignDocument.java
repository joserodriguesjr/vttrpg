package com.vttrpg.RPG.infrastructure.provider.mongodb;

import com.vttrpg.RPG.domain.model.Field;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Document(collection = "campaigns")
public class CampaignDocument {
    @Id
    private String id;
    private String name;
    private List<Field> fields;

    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date lastModifiedDate;

}