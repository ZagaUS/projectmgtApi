package com.zaga.model.entity;

import java.time.LocalDate;

import org.bson.types.Binary;
import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({ "id" })
@MongoEntity(collection = "PO", database = "ProjectManagement")
public class PO extends PanacheMongoEntity{
    public ObjectId id;
    public String projectId;
    public String projectName;
    public String poId;
    public Binary data;
    public LocalDate startDate;
    public LocalDate endDate;

}
