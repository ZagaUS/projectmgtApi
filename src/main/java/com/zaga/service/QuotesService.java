package com.zaga.service;

import com.zaga.model.entity.Quote;

public interface QuotesService {
    public Quote createQuotes(Quote quote);

    public Quote getQuoteById(String quoteId);

    public Quote updateQuote(Quote quote);
    
}
