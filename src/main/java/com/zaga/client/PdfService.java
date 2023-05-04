package com.zaga.client;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.zaga.model.entity.WeeklyTimesheet;

@RegisterRestClient(configKey = "pdf-api")
public interface PdfService {

    @GET
    @Path("/{amount}")
    public Response generatePdf(@PathParam("amount") String amount);

    @POST
    @Path("/createTimesheet")
    public Response generateTimesheetPdf(WeeklyTimesheet weekly);

}
