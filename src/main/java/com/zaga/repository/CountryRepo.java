package com.zaga.repository;

import javax.enterprise.context.ApplicationScoped;

import com.zaga.model.entity.CountryList;

import io.quarkus.mongodb.panache.PanacheMongoRepository;

@ApplicationScoped
public class CountryRepo implements PanacheMongoRepository<CountryList> {

}
