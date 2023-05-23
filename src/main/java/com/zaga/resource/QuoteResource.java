package com.zaga.resource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.models.media.Schema.SchemaType;

import com.zaga.model.entity.Quote;
import com.zaga.repository.QuotesRepository;
import com.zaga.service.QuotesService;

@Tag(name = "Quotes", description = "crud operation for Quote")
@Path("/Quotes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuoteResource {

   @Inject
   QuotesRepository repo;

   @Inject
   QuotesService service;

   @POST
   @Path("/createQuotes")
   public Quote createQuotes(Quote quote) {
      System.out.println(quote);
      return service.createQuotes(quote);
   }
}
