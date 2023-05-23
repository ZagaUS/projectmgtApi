package com.zaga.model.entity;

import java.util.List;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MongoEntity(collection = "CurrencyList", database = "ProjectManagement")
public class CurrencyList {
    public List<String> currency;
}
