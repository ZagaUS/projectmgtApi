package com.zaga.repository;

import javax.enterprise.context.ApplicationScoped;

import com.zaga.model.entity.QuotePdf;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.mongodb.panache.PanacheQuery;

@ApplicationScoped
public class QuotePdfRepo implements PanacheMongoRepository<QuotePdf> {

    public QuotePdf viewPdfDocumentByDocumentId(String projectId, String quoteId) {

        PanacheQuery<QuotePdf> pdf = QuotePdf.find("projectId=?1 and quoteId=?2", projectId, quoteId);
        QuotePdf pd = pdf.firstResult();

        return pd;
    }
}
