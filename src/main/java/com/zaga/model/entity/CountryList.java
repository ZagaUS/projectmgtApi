package com.zaga.model.entity;

import java.util.List;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MongoEntity(collection = "CountryList", database = "ProjectManagement")
public class CountryList {
    public List<String> country;
}
