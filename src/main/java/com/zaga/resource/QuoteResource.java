package com.zaga.resource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.types.Binary;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.models.media.Schema.SchemaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.zaga.client.PdfService;
import com.zaga.model.entity.Quote;
import com.zaga.model.entity.QuoteLimitedDto;
import com.zaga.model.entity.QuotePdf;
import com.zaga.repository.QuotePdfRepo;
import com.zaga.repository.QuotesRepository;
import com.zaga.service.QuotesService;

@Tag(name = "Quotes", description = "crud operation for Quote")
@Path("/Quotes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuoteResource {

  @Inject
  @RestClient
  PdfService pdfService;

  @Inject
  QuotesRepository repo;

  @Inject
  QuotesService service;

  @Inject
  QuotePdfRepo pdfrepo;

  @POST
  @Path("/generateQuote")
  public Response generatePdf(Quote quote) throws IOException {

    Response response = pdfService.generatePdf(quote);

    byte[] pdfBytes = response.readEntity(byte[].class);
    InputStream inputStream = new ByteArrayInputStream(pdfBytes);
    QuotePdf pdf = new QuotePdf();
    pdf.setData(new Binary(inputStream.readAllBytes()));
    pdf.setProjectId(quote.getProjectId());
    pdf.setProjectName(quote.getProjectName());
    pdf.setQuoteId(quote.getQuoteId());

    // persist the pdf document
    pdfrepo.persist(pdf);

    return Response.ok(pdf).build();

  }

  @GET
  @Path("/getQuotePdf")
  public Response viewQuotePdf(@QueryParam("projectId") String projectId, @QueryParam("quoteId") String quoteId) {
    QuotePdf pdf = pdfrepo.viewPdfDocumentByDocumentId(projectId, quoteId);
    String str = Base64.getEncoder().encodeToString(pdf.getData().getData());
    return Response.ok(str).build();
  }

  @POST
  @Path("/createQuotes")
  public Quote createQuotes(Quote quote) {
    System.out.println(quote);
    return service.createQuotes(quote);
  }

  @GET
  @Path("/getQuotesByProjectId/{projectId}")
  public Response getQuotesByProjectId(String projectId) {
    try {
      List<Quote> quotes = repo.getQuotesByProjectId(projectId);
      return Response.ok(quotes).build();

    } catch (WebApplicationException e) {
      return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
    }
  }

  @PUT
  @Path("/modifiyQuotes")
  public Response updateQuotes(Quote quote) {
    service.updateQuote(quote);
    return Response.ok(quote).build();
  }

  @GET
  @Path("/getQuotes/{quoteId}")
  public Response getQuoteId(@PathParam("quoteId") String quoteId) {
    Quote quotedetails = service.getQuoteById(quoteId);
    return Response.ok(quotedetails).build();

  }

  @GET
  @Path("/viewQuoteDetails/{projectId}")
  public Response getQuoteDetails(@PathParam("projectId") String projectId){
      List<QuoteLimitedDto> quoteDetails = service.getQuoteDetails(projectId);
      return Response.ok(quoteDetails).build();
  }


  @DELETE
  @Path("/deleteQuote/{quoteId}")
  public void deleteQuotes(@PathParam ("quoteId") String quoteId){
    service.deleteQuote(quoteId);
  }
 
  

}
