package com.vttrpg.RPG.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.vttrpg.RPG.domain.validation.Validatable;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "campaigns")
@Data
public class Campaign implements Validatable {
    @JsonProperty(access = Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "campaign_id")
    private Long id;

    @JsonProperty(access = Access.READ_ONLY)
    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private Date createdDate;

    @JsonProperty(access = Access.READ_ONLY)
    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 64)
    @Column(name = "name", length = 64, nullable = false)
    private String name;

//    Store fields in another table to access from QueryDSL
//    @JsonProperty(access = Access.READ_ONLY)
//    @OneToMany(cascade = CascadeType.ALL)
//    @Column(name = "fields")
//    private List<Field> fields = new ArrayList<>();

    // Store fields as JSON in a single column
    @JsonProperty(access = Access.READ_ONLY)
    @Type(JsonBinaryType.class)
    @Column(name = "fields", columnDefinition = "jsonb")
    private List<Field> fields = new ArrayList<>();

}
