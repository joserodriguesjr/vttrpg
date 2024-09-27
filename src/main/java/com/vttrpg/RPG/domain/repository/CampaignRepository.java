package com.vttrpg.RPG.domain.repository;

import com.vttrpg.RPG.domain.model.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "campaigns", path = "campaigns")
@Tag(name = "Campaigns Controller", description = "Campaign Management Endpoints")
public interface CampaignRepository extends
        JpaRepository<Campaign, Long>,
        ListCrudRepository<Campaign, Long>,
        PagingAndSortingRepository<Campaign, Long>,
        QuerydslPredicateExecutor<Campaign>
//        QuerydslBinderCustomizer<CustomQCampaign>
{

//    @Query(
//            value = "SELECT * FROM campaigns c " +
//                    "WHERE EXISTS ( " +
//                    "    SELECT 1 FROM jsonb_array_elements(c.fields) field " +
//                    "    WHERE field->>'name' = :fieldName" +
//                    ")",
//            countQuery = "SELECT count(*) FROM campaigns c " +
//                    "WHERE EXISTS ( " +
//                    "    SELECT 1 FROM jsonb_array_elements(c.fields) field " +
//                    "    WHERE field->>'name' = :fieldName" +
//                    ")",
//            nativeQuery = true
//    )
//    Page<Campaign> findByFieldName(@Param("fieldName") String fieldName, Pageable pageable);

//    @Override
//    default void customize(QuerydslBindings bindings, QCampaign campaign) {
//        bindings.bind(campaign.fields.any().name).first(StringExpression::equalsIgnoreCase);
//    }
}
