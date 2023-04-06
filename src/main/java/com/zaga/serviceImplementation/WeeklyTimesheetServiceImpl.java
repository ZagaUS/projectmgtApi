package com.zaga.serviceImplementation;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.logging.Logger;
import com.zaga.model.entity.WeeklyTimesheet;
import com.zaga.repository.SequenceRepository;
import com.zaga.repository.WeeklyTimesheetRepository;
import com.zaga.service.WeeklyTimesheetService;

@ApplicationScoped
public class WeeklyTimesheetServiceImpl implements WeeklyTimesheetService {
    
    @Inject
    Logger logger;

    @Inject
    SequenceRepository sequenceRepository;

    @Inject
    WeeklyTimesheetRepository weeklyTimesheetRepository;

    @Override
    public WeeklyTimesheet createWeeklyTimesheet(WeeklyTimesheet weeklyTimesheet) {
        String seqNo = sequenceRepository.getSequenceCounter("WeeklyTimesheet");
        weeklyTimesheet.setWeeklyTimesheetId(seqNo);
        WeeklyTimesheet.persist(weeklyTimesheet);
        return weeklyTimesheet;
    }

    @Override
    public List<WeeklyTimesheet> getWeeklyTimesheets() {
        List<WeeklyTimesheet> timesheets = WeeklyTimesheet.listAll();
        return timesheets;
    }

    @Override
    public List<WeeklyTimesheet> getWeeklyTimesheetByType(String timesheetType) {
        List<WeeklyTimesheet> timesheets = weeklyTimesheetRepository.getWeeklyTimesheetByType(timesheetType);
        return timesheets;
        
    }

    @Override
    public WeeklyTimesheet getWeeklyTimesheetById(String timesheetId) {
        WeeklyTimesheet weeklyTimesheet2 = weeklyTimesheetRepository.getWeeklyTimesheetById(timesheetId);
        return weeklyTimesheet2;
    }

    @Override
    public WeeklyTimesheet updateWeeklyTimesheet(WeeklyTimesheet weeklyTimesheet) {
        WeeklyTimesheet weeklyTimesheet2 = weeklyTimesheetRepository.getWeeklyTimesheetById(weeklyTimesheet.getWeeklyTimesheetId());
        weeklyTimesheet.setId(weeklyTimesheet2.getId());
        weeklyTimesheet.update();
        return weeklyTimesheet;
    }

    @Override
    public WeeklyTimesheet deleteWeeklyTimesheet(String timesheetId) {
        WeeklyTimesheet weeklyTimesheet2 = weeklyTimesheetRepository.getWeeklyTimesheetById(timesheetId);
        weeklyTimesheet2.delete();
        return weeklyTimesheet2;
    }
}
