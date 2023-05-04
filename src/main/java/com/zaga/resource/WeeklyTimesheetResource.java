package com.zaga.resource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.types.Binary;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.zaga.client.PdfService;
import com.zaga.model.entity.DocumentType;
import com.zaga.model.entity.PdfEntity;
import com.zaga.model.entity.WeeklyTimesheet;

import com.zaga.repository.PdfRepository;
import com.zaga.service.WeeklyTimesheetService;

@Tag(name = "Weekly Timesheet", description = "CRUD Operations for Project Details")
@Path("/weeklyTimesheet")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WeeklyTimesheetResource {

    @Inject
    WeeklyTimesheetService service;

    @Inject
    @RestClient
    PdfService pdfService;

    @Inject
    PdfRepository repository;

    @GET
    @Path("/{amount}")
    public Response generatePdf(@PathParam("amount") String amount) {
        return pdfService.generatePdf(amount);
    }

    @POST
    @Path("/createTimesheet")
    public Response generateTimesheetPdf(@QueryParam("projectName") String projectName,
            @QueryParam("projectId") String projectId, @QueryParam("startDate") LocalDate startDate,
            @QueryParam("endDate") LocalDate endDate, @QueryParam("documentType") String documentType)
            throws IOException {

        try {

            // Create Pdf entity for uploading timesheet
            PdfEntity pdfDocument = new PdfEntity();

            // Create Document Id by convention
            StringBuilder DocId = new StringBuilder();
            DocId.append(projectName);
            DocId.append("_");
            DocId.append(startDate);
            DocId.append("_");
            DocId.append(endDate);
            // Setting PdfEntity properties
            pdfDocument.setDocumentId(DocId.toString());
            pdfDocument.projectId = projectId;
            pdfDocument.projectName = projectName;
            pdfDocument.startDate = startDate;
            pdfDocument.endDate = endDate;
            pdfDocument.setDocumentType(DocumentType.valueOf(documentType));
            // Generate WeeklyTimesheetbased on input start date and end date
            WeeklyTimesheet timesheetpdf = service.generateWeeeklyTimesheet(projectId, startDate, endDate);
            // persist the weekly timesheet in weekytimesheet database
            timesheetpdf.setWeeklyTimesheetId(DocId.toString());
            WeeklyTimesheet.persist(timesheetpdf);
            // Pdf file obtained from document service
            Response response = pdfService.generateTimesheetPdf(timesheetpdf);

            byte[] pdfBytes = response.readEntity(byte[].class);
            InputStream inputStream = new ByteArrayInputStream(pdfBytes);

            pdfDocument.setData(new Binary(inputStream.readAllBytes()));
            // persist the pdf document
            repository.persist(pdfDocument);
            return Response.ok(pdfDocument).build();

        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();

        }
        return Response.noContent().build();
    }

    @POST
    @Path("/createWeeklyTimesheet")
    public WeeklyTimesheet createWeeklyTimesheet(WeeklyTimesheet weeklyTimesheet) {
        return service.createWeeklyTimesheet(weeklyTimesheet);
    }

    @GET
    @Path("/getWeeklyTimesheets")
    public List<WeeklyTimesheet> getWeeklyTimesheets() {
        return service.getWeeklyTimesheets();
    }

    @GET
    @Path("/getWeeklyTimesheet/{weeklyTimesheetType}")
    public List<WeeklyTimesheet> getWeeklyTimesheetByType(@PathParam("weeklyTimesheetType") String timesheetType,
            @QueryParam("projectId") String projectId) {
        return service.getWeeklyTimesheetByType(timesheetType, projectId);
    }

    @GET
    @Path("/getWeeklyTimesheetByProjectId/{projectId}")
    public List<WeeklyTimesheet> getWeeklyTimesheetByProjectId(@PathParam("projectId") String projectId) {
        return service.getWeeklyTimesheetsByProjectId(projectId);
    }

    @GET
    @Path("/getWeeklyTimesheetByWeeklyTimesheetId/{weeklyTimesheetId}")
    public WeeklyTimesheet getWeeklyTimesheetById(@PathParam("weeklyTimesheetId") String timesheetId) {
        return service.getWeeklyTimesheetById(timesheetId);
    }

    @PUT
    @Path("/updateWeeklyTimesheet")
    public WeeklyTimesheet updateWeeklyTimesheet(WeeklyTimesheet weeklyTimesheet) {
        return service.updateWeeklyTimesheet(weeklyTimesheet);
    }

    @DELETE
    @Path("/deleteWeeklyTimesheet")
    public WeeklyTimesheet deleteWeeklyTimesheet(String timesheetId) {
        return service.deleteWeeklyTimesheet(timesheetId);
    }
}
