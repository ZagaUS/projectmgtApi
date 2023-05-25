package com.zaga.resource;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.types.Binary;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.zaga.model.entity.PO;
import com.zaga.model.entity.PdfEntity;
import com.zaga.repository.PORepo;
import com.zaga.repository.PdfRepository;

@Tag(name = "PO", description = "CRUD Operations for PO")
@Path("/po")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class POResource {

    @Inject
    PORepo poRepo;

    // @Inject
    // PdfRepository repo;

    @POST
    @Path("/uploadPO")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public Response uploadPODocument(InputStream inputStream, @QueryParam("projectName") String projectName,
            @QueryParam("projectId") String projectId, @QueryParam("startDate") LocalDate startDate,
            @QueryParam("endDate") LocalDate endDate)
            throws IOException {
        // ProjectDetails projectDetails = new ProjectDetails();

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
    
}
