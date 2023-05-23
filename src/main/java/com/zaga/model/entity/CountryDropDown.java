package com.zaga.model.entity;

import java.util.List;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MongoEntity(collection = "CountryDropDown", database = "ProjectManagement")
public class CountryDropDown {
    public String country;
    public String currency;
    public List<String> timezones;
}
