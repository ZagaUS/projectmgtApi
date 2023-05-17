package com.zaga.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"id"})
@MongoEntity(collection = "counter",database = "ProjectManagement")
public class SequenceCounters extends PanacheMongoEntity{

    public String seqName;
    public int seqNumber;
    
}
