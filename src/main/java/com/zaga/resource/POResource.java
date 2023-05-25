package com.zaga.resource;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.types.Binary;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.zaga.model.entity.PO;
import com.zaga.model.entity.PdfEntity;
import com.zaga.model.entity.ProjectDetails;
import com.zaga.repository.PORepo;
import com.zaga.repository.PdfRepository;
import com.zaga.repository.ProjectDetailsRepository;

@Tag(name = "PO", description = "CRUD Operations for PO")
@Path("/po")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class POResource {

    @Inject
    PORepo poRepo;

    @Inject
    ProjectDetailsRepository projRepo;
    // PdfRepository repo;

    @POST
    @Path("/uploadPO")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public Response uploadPODocument(InputStream inputStream, @QueryParam("projectName") String projectName,
            @QueryParam("projectId") String projectId, @QueryParam("startDate") LocalDate startDate,
            @QueryParam("endDate") LocalDate endDate)
            throws IOException {
        // ProjectDetails projectDetails = new ProjectDetails();

        ProjectDetails projData = projRepo.getProjectDetailsById(projectId);
        projData.setPoStatus(true);
        projRepo.persistOrUpdate(projData);

        System.out.println("projectDetails " + projectName + " " + projectId);
        PO po = new PO();
        StringBuilder DocId = new StringBuilder();
        DocId.append(projectName);
        DocId.append("_");
        DocId.append(startDate);
        DocId.append("_");
        DocId.append(endDate);
        // String seqNo = sequenceRepository.getSequenceCounter("ApprovedTimesheet");
        po.setPoId(DocId.toString());
        po.projectId = projectId;
        po.projectName = projectName;
        po.startDate = startDate;
        po.endDate = endDate;
        // pdfDocument.documentType = documentType;
        po.data = new Binary(inputStream.readAllBytes());

        // ProjectDetails details = new ProjectDetails();
        // details.getProjectId();

        poRepo.persist(po);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/getPOByProjectId/{projectId}")
    public Response getPOByProjectId(String projectId) {
      try {
        List<PO> quotes = poRepo.viewPOByProjectId(projectId);
        return Response.ok(quotes).build();
  
      } catch (WebApplicationException e) {
        return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
      }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/viewPO/{poId}")
    public Response viewPOByDocumentId(@PathParam("poId") String poId) {

        // System.out.println("----------------strted");
        try {
            PO po = poRepo.viewPOByPoId(poId);
            String str = Base64.getEncoder().encodeToString(po.getData().getData());

            // String result =
            // Binary bin = new Binary(pdf.getData().getData());
            // System.out.println("--------------------------");
            // String str = new String(bin.getBytes()

            // System.out.println("-------------------------------");
            // System.out.println(str);
            return Response.ok(str).build();
        } catch (WebApplicationException e) {
            System.out.println("---------error");
            e.printStackTrace();
            return null;
        }
    }
    
    @DELETE
    @Path("/deletePOByPoId/{poId}")
    public Response deletePOByPoId(@PathParam("poId") String poId) {
      try {
        poRepo.deletePOByPoId(poId);
        return Response.ok().build();
      } catch (WebApplicationException e) {
        return Response.status(e.getResponse().getStatusInfo()).entity(e.getMessage()).build();
      }
    }
    
}
