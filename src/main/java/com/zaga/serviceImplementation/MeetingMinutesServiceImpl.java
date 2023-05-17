package com.zaga.serviceImplementation;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

import org.jboss.logging.Logger;

// import com.zaga.model.dto.UpdateMeetingMinutesDto;
import com.zaga.model.entity.MeetingMinutes;
import com.zaga.repository.MeetingMinutesRepository;
import com.zaga.repository.SequenceRepository;
import com.zaga.service.MeetingMinutesService;

@ApplicationScoped
public class MeetingMinutesServiceImpl implements MeetingMinutesService {
    

    

   @Inject
   MeetingMinutesRepository repo;

   @Inject
   Logger logger;

   @Inject
   SequenceRepository seqRepo;


   @Override
    public List<MeetingMinutes> getMeetingMinutesList() {
       
       List<MeetingMinutes> meetingMinutes =  repo.listAll();

       System.out.println(meetingMinutes);

       if(meetingMinutes.isEmpty()) {

        throw new WebApplicationException("The Resource is empty ",500);
       }
       return meetingMinutes;
    }

    @Override
    public MeetingMinutes getMeetingMinuteByMeetingMinutesId(String meetingMinutesId) {
       
        if (meetingMinutesId == null){
            throw new WebApplicationException("MeetingMinutesId is Invalid ",500);
        }
        MeetingMinutes meetingminutes = repo.getMeetingMinutesByMeetingMinutesId(meetingMinutesId);
        if (meetingminutes == null){
            throw new WebApplicationException("The Resource is empty ",500);
        }
        return meetingminutes;
        
    }
    
   @Override
   public MeetingMinutes createMeetingMinutes(MeetingMinutes meetingMinutes) {
      String seqno = seqRepo.getSequenceCounter("MeetingMinutes");
      meetingMinutes.setMeetingMinutesId(seqno);
      MeetingMinutes.persist(meetingMinutes);
      return meetingMinutes;
   }

   @Override
   public MeetingMinutes updateMeetingMinutes(MeetingMinutes minutes) {

      if (minutes.getMeetingMinutesId() == null) {

         throw new WebApplicationException("Invalid Meeting Minutes Id ", 500);
      }

      MeetingMinutes meetingMinutes = repo.getMeetingMinutesByMeetingMinutesId(minutes.getMeetingMinutesId());

      if (meetingMinutes == null) {

         throw new WebApplicationException("Resource Not Found For Meeting Minutes Id ", 500);
      }

      logger.info("------Meeting Minutes------" + meetingMinutes);

      
     System.out.println("-----input-----"+minutes);
      System.out.println("--------------ooo"+meetingMinutes.getId());

      minutes.setId(meetingMinutes.getId());

      logger.info("-----Dto -----" + minutes);
     System.out.println("aaaaaaaaa"+minutes);
      MeetingMinutes.update(minutes);

      return minutes;
   }

   @Override
   public void deleteMeetingMinutes(String meetingMinutesId) {

      if (meetingMinutesId == null) {

         throw new WebApplicationException("Invalid Meeting Minutes Id ", 500);

      }

      repo.deleteMeetingMinutesByMeetingMinutesId(meetingMinutesId);

   }
}
