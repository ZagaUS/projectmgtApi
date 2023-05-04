package com.zaga.resource;

import java.time.LocalDate;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaga.client.PoWorkflow;
import com.zaga.event.EventDto;

import com.zaga.model.entity.ProjectDetails;

//import com.zaga.client.PoWorkflow;

@Tag(name = "Po workflow", description = "Workflow operations")
@Path("/poWorkflow")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Workflow {

    @RestClient
    @Inject
    PoWorkflow workflow;

    String username = "rhpamAdmin";
    String password = "Admin@123";

    @POST
    public void startProcess(@HeaderParam("Authorization") String authorization,
            @QueryParam("containerId") String containerId,
            @QueryParam("processID") String processID,
            Map<String, Object> data) {
        System.out.println("-------ready to start -----" + authorization);
        System.out.println("---------process data-------" + data);
        workflow.startProcess(authorization, containerId, processID, data);
    }

    private String encodeCredentials(String username, String password) {
        String credentials = username + ":" + password;
        return Base64.getEncoder().encodeToString(credentials.getBytes());
    }

    @Incoming("po-in")
    public void triggerprocess(EventDto message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String username = "rhpamAdmin";

        String password = "Admin@123";

        String processID = "poworkflow.poworkflow";

        String containerId = "poworkflow_1.0.0-SNAPSHOT";

        String credentials = encodeCredentials(username, password);
        System.out.println("-----------Before ---- mapping---------");
        Object vdata = message.getEventData();

        String creds = "Basic " + credentials;
        System.out.println("------------message data-------" + vdata.toString());
        String payload = objectMapper.writeValueAsString(vdata);
        // TypeReference<ProjectDetails> typeRef = new TypeReference<ProjectDetails>() {
        // };
        ProjectDetails pd = objectMapper.convertValue(payload, ProjectDetails.class);

        System.out.println("---------pd------" + pd);

        // Data data = new Data();
        // data.setDatas(vdata);

        Map<String, Object> dataMap = new HashMap<>();
        // Map<String, Object> projectDetails = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        data.put("com.projectautomation.poworkflow.ProjectDetails", message.getEventData());
        dataMap.put("Data", data);

        // System.out.println("-----data---" + payload);
        startProcess(creds, containerId, processID, dataMap);
    }
}
