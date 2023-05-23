package com.zaga.repository;

import java.util.List;

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

    public List<Quote> getQuotesByProjectId (String projectId) {
        List<Quote> details = Quote.list("projectId=?1", projectId);
        return details;
    }




}
