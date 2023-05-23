package com.zaga.repository;

import javax.enterprise.context.ApplicationScoped;

import com.zaga.model.entity.Quote;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.mongodb.panache.PanacheQuery;

@ApplicationScoped
public class QuotesRepository implements PanacheMongoRepository<Quote> {
   
    public Quote getQuoteById (String quoteId) {
        PanacheQuery<Quote> details = Quote.find("quoteId=?1", quoteId);
        Quote quot = details.firstResult();
        return quot;
    }




}
