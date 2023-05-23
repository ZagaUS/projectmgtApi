package com.zaga.serviceImplementation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import com.zaga.model.entity.Quote;
import com.zaga.repository.QuotesRepository;
import com.zaga.repository.SequenceRepository;
import com.zaga.service.QuotesService;

@ApplicationScoped
public class QuotesServiceImpl implements QuotesService{

       @Inject
       QuotesRepository repo;
       
       @Inject
       SequenceRepository seqRepo;

    @Override
    public Quote createQuotes(Quote quote) {
        // String seqNo = sequenceRepository.getSequenceCounter("Quotes");
        String seqNo = seqRepo.getSequenceCounter("Quotes");
        quote.setQuoteId(seqNo);
        repo.persist(quote);
        return quote;
    }

    @Override
    public Quote updateQuote(Quote quote) {
        Quote details = repo.getQuoteById(quote.getQuoteId());
        Quote quot   = quote;
        quot.setId(details.getId());
        Quote.update(quot);
        return quot;

    }

    @Override
    public Quote getQuoteById(String quoteId) {
        Quote  detail = repo.getQuoteById(quoteId);
        return detail;
                }

    
    

}
