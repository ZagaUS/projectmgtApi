package com.zaga.resource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

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
import com.zaga.model.entity.ProjectDetails;
import com.zaga.model.entity.Quote;
import com.zaga.model.entity.QuoteLimitedDto;
import com.zaga.model.entity.QuotePdf;
import com.zaga.repository.ProjectDetailsRepository;
import com.zaga.repository.QuotePdfRepo;
import com.zaga.repository.QuotesRepository;
import com.zaga.repository.SequenceRepository;
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
  QuotePdf quotePdf;

  @Inject
  QuotesService service;

  @Inject
  ProjectDetailsRepository projectRepo;
@Inject
SequenceRepository sequenceRepository;

  @Inject
  QuotePdfRepo pdfrepo;

  @POST
  @Path("/generateQuote/{quoteId}")
  public Response generatePdf(@PathParam("quoteId") String quoteId) throws IOException {

    Quote quote = repo.getQuoteById(quoteId);

    System.out.println("quoteId: " + quote.getProjectId());
    QuotePdf pdf = new QuotePdf();
   System.out.println(quote.getQuoteId());
    pdf.setProjectId(quote.getProjectId());
    pdf.setProjectName(quote.getProjectName());
    pdf.setQuoteId(quote.getQuoteId());
    System.out.println(pdf.getProjectId());
    Response response = pdfService.generatePdf(quote);

    byte[] pdfBytes = response.readEntity(byte[].class);
    System.out.println(pdfBytes);
    InputStream inputStream = new ByteArrayInputStream(pdfBytes);
    pdf.setData(new Binary(inputStream.readAllBytes()));
    // String seqNo = sequenceRepository.getSequenceCounter("QuotePdfs");
    // PdfEntity pdfDocument = new PdfEntity();
    // StringBuilder DocId = new StringBuilder();
    // DocId.append(projectName);
    // DocId.append("_");
    // DocId.append(startDate);
    // DocId.append("_");
    // DocId.append(endDate);
    // DocId.append("_");

   
    // DocId.append(seqNo);
    // pdf.setQuoteName(DocId.toString());
  
    System.out.println("----------------------Generating Quote");
    
    quote.setPdfStatus(true);
    System.out.println(pdf);
    ProjectDetails projectDetails = projectRepo.getProjectDetailsById(quote.getProjectId());
    projectDetails.setQuoteId(quote.getQuoteId());
    projectRepo.update(projectDetails);

    repo.persistOrUpdate(quote);
    

  

    // persist the pdf document
    pdfrepo.persist(pdf);
  

    return Response.ok(pdf).build();

  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/getQuotePdf/")
  public Response viewQuotePdf( @QueryParam("quoteId") String quoteId) {
    QuotePdf pdf = pdfrepo.viewPdfDocumentByDocumentId(quoteId);
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

  @GET
  @Path("/getQuotesPdfByProjectId/{projectId}")
  public Response getQuotesPdfByProjectId(String projectId) {
    try {
      List<QuotePdf> quotes = pdfrepo.getQuote(projectId);
      return Response.ok(quotes).build();

    } catch (WebApplicationException e) {
      return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
    }
  }

  
  // @GET
  // @Path("/getQuotesByPdfStatus/{pdfStatus}")
  // public Response getQuotesByPdfStatus(Boolean pdfStatus) {
  //   try {
  //     List<Quote> quotes = repo.getQuotesByPdfStatus(pdfStatus);
  //     return Response.ok(quotes).build();

  //   } catch (WebApplicationException e) {
  //     return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
  //   }
  // }

  @PUT
  @Path("/modifyQuotes")
  public Response updateQuotes(Quote quote) {
    service.updateQuote(quote);
    ProjectDetails projectDetails = projectRepo.getProjectDetailsById(quote.getProjectId());
    projectDetails.setEndDate(quote.getEndDate());
    projectDetails.setStartDate(quote.getDate());
    projectDetails.setPa(quote.getPa());
    projectDetails.setPo(quote.getPo());
    projectDetails.setSfdc(quote.getSfdc());
    projectDetails.setUnitPrice(quote.getUnitPrice());
    projectDetails.setTotalManDays(quote.getTotalManDays());
    projectDetails.setDuration(quote.getDuration());
    projectDetails.setValidDate(quote.getValidDate());
    projectDetails.setTotalAmount(quote.getTotalAmount());
    projectRepo.update(projectDetails);
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

  @DELETE
  @Path("/deleteQuotePdf/{quoteId}")
  public void deleteQuotePdf(@PathParam ("quoteId") String quoteId){
    pdfrepo.deleteQuotesById(quoteId);
  }

  @GET
  @Path("/download/{quoteId}")
  @Produces(MediaType.APPLICATION_OCTET_STREAM)
  public Response downloadPdf(@PathParam("quoteId") String quoteId) {
      // ObjectId objectId = new ObjectId(id);
      QuotePdf pdf = pdfrepo.viewPdfDocumentByDocumentId(quoteId);
          Binary pdfData = pdf.data;
          
          // Set the appropriate response headers
          Response.ResponseBuilder responseBuilder = Response.ok(pdfData.getData());
          responseBuilder.header("Content-Disposition", "attachment; filename=download.pdf");
          responseBuilder.header("Content-Length", String.valueOf(pdfData.length()));
          
          return responseBuilder.build();
      } 
  
}
