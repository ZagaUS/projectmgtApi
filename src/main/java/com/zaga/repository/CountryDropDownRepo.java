package com.zaga.repository;

import javax.enterprise.context.ApplicationScoped;

import com.zaga.model.entity.CountryDropDown;

import io.quarkus.mongodb.panache.PanacheMongoRepository;

@ApplicationScoped
public class CountryDropDownRepo implements PanacheMongoRepository<CountryDropDown> {

}
