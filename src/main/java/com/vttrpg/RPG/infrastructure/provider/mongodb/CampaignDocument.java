package com.vttrpg.RPG.infrastructure.provider.mongodb;

import com.vttrpg.RPG.domain.model.Field;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Document(collection = "campaigns")
public class CampaignDocument implements Persistable<String> {
    @Id
    private String id;
    private String name;
    private List<Field> fields = new ArrayList<>();

    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date lastModifiedDate;

    @Transient
    private boolean persisted;

    @Override
    @Transient
    public boolean isNew() {
        return !persisted;
    }

}