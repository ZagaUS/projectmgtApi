package com.zaga.repository;

import javax.enterprise.context.ApplicationScoped;
import com.zaga.model.entity.SequenceCounters;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.mongodb.panache.PanacheQuery;

@ApplicationScoped
public class SequenceRepository implements PanacheMongoRepository<SequenceCounters> {
  public String getSequenceCounter(String seqname) {

    PanacheQuery<SequenceCounters> count = SequenceCounters.find("seqName = ?1", seqname);
    SequenceCounters scounter = count.firstResult();
    String seqnumber = Integer.toString(scounter.getSeqNumber());
    scounter.setSeqNumber(scounter.getSeqNumber() + 1);
    SequenceCounters.update(scounter);
    return seqnumber;
  }

}
