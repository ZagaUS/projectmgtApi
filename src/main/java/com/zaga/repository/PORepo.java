package com.zaga.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;

import com.zaga.model.entity.PO;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.mongodb.panache.PanacheQuery;


@ApplicationScoped
public class PORepo implements PanacheMongoRepository<PO> {

    public List<PO> viewPOByProjectId(String projectId) {

        List<PO> po = PO.list("projectId=?1", projectId);
        return po;
    }

    public PO viewPOByPoId(String poId) {

        PanacheQuery<PO> po = PO.find("poId=?1",poId);
        PO pd = po.firstResult();
        return pd;
    }

    public void deletePOByPoId(String poId) {
    PO.delete("poId=?1", poId);
    }

}