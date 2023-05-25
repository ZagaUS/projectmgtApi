package com.zaga.serviceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

import com.zaga.model.entity.Quote;
import com.zaga.model.entity.QuoteLimitedDto;
import com.zaga.repository.QuotesRepository;
import com.zaga.repository.SequenceRepository;
import com.zaga.service.QuotesService;

// import io.quarkus.arc.lookup.LookupIfProperty.List;

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

    @Override
    public List<Quote> getQuotesByProjectId(String projectId) {
        List<Quote> details = repo.getQuotesByProjectId(projectId);
        return details;
       
    }

    @Override
    public List<QuoteLimitedDto> getQuoteDetails(String projectId) {

         List<Quote> quotes = repo.getQuotesByProjectId(projectId);

         if (quotes.isEmpty()) {

            return null;
        }

        else{
                               List<QuoteLimitedDto> quotedLimitdto  = quotes.stream()
                               .map(quotee->{
                                QuoteLimitedDto dto = new QuoteLimitedDto();
                                System.out.println("------Quote Stream------"+ quotee);
                                dto.setProjectName(quotee.getProjectName());
                               dto.setQuoteNumber(quotee.getQuoteId());
                                dto.setTotalManDays(quotee.getTotalManDays());
                                dto.setTotalAmount(quotee.getTotalAmount());
                                dto.setValidDate(quotee.getValidDate());
                                dto.setPdfStatus(quotee.getPdfStatus());
                                return dto;
                                 })

                       .collect(Collectors.toList());

                       return quotedLimitdto;
                                }
    }

    @Override
    public void deleteQuote(String quoteId) {
        repo.deleteQuotesById(quoteId);
    }



    

    

    
    

}
