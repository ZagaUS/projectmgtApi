package com.zaga.resource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.zaga.model.entity.SequenceCounters;
import com.zaga.repository.SequenceRepository;

@Tag(name = "Sequence Counter", description = "Sequence Generation counter")
@Path("/counters")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SequenceCreate {

    @Inject
    SequenceRepository repo;

    @POST
    public Response createCounter(SequenceCounters counter) {
        repo.persist(counter);
        return Response.ok(counter).build();
    }
}
