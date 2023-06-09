package com.zaga.model.entity;

import java.time.LocalDate;

import javax.enterprise.context.ApplicationScoped;

import org.bson.types.Binary;
import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@ApplicationScoped
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({ "id" })
@MongoEntity(collection = "Quotepdfs", database = "ProjectManagement")
public class QuotePdf extends PanacheMongoEntity {
    public ObjectId id;
    public String projectId;
    public String quoteId;
    public String projectName;
    public Binary data;
    public String quoteName;
    public LocalDate startDate;
    public LocalDate endDate;

}
