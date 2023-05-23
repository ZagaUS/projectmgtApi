package com.zaga.service;

import java.util.List;

import com.zaga.model.entity.Quote;

public interface QuotesService {
    public Quote createQuotes(Quote quote);

    public Quote getQuoteById(String quoteId);

    public List<Quote> getQuotesByProjectId(String projectId);

    public Quote updateQuote(Quote quote);
    
}
