package com.zaga.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.zaga.model.entity.QuotePdf;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.mongodb.panache.PanacheQuery;

@ApplicationScoped
public class QuotePdfRepo implements PanacheMongoRepository<QuotePdf> {

    public QuotePdf viewPdfDocumentByDocumentId(String quoteId) {

        PanacheQuery<QuotePdf> pdf = QuotePdf.find("quoteId=?1",  quoteId);
        QuotePdf pd = pdf.firstResult();

        return pd;
    }

    public List<QuotePdf> getQuote(String projectId) {
        List<QuotePdf> details = QuotePdf.list("projectId=?1", projectId);
        return details;
    }

    public void deleteQuotesById (String quoteId){
        QuotePdf.delete("quoteId=?1",quoteId);
      }
      
}
