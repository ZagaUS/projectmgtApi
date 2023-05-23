package com.zaga.repository;

import javax.enterprise.context.ApplicationScoped;

import com.zaga.model.entity.Quote;

import io.quarkus.mongodb.panache.PanacheMongoRepository;

@ApplicationScoped
public class QuotesRepository implements PanacheMongoRepository<Quote> {
   
}
