package com.zaga.repository;

import javax.enterprise.context.ApplicationScoped;

import com.zaga.model.entity.CurrencyList;

import io.quarkus.mongodb.panache.PanacheMongoRepository;

@ApplicationScoped
public class CurrencyRepo implements PanacheMongoRepository<CurrencyList> {

}
