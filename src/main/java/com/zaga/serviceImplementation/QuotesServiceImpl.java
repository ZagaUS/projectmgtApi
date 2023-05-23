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
    

}
