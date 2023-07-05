package com.zaga.serviceImplementation;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.zaga.model.entity.DailyTimesheet;
import com.zaga.repository.DailyTimesheetRepository;
import com.zaga.service.DailyTimesheetService;

@ApplicationScoped
public class DailyTimesheetServiceImpl implements DailyTimesheetService {

    @Inject
    DailyTimesheetRepository repo;

    @Override
    public DailyTimesheet createDailyTimesheet(DailyTimesheet dts) {

        StringBuilder DocId = new StringBuilder();
        DocId.append(dts.getProjectName());
        DocId.append("_");
        DocId.append(dts.getDate());
        dts.setDailyTimesheetId(DocId.toString());
        DailyTimesheet.persist(dts);
        return dts;
    }

    @Override
    public List<DailyTimesheet> getDailyTimeSheet() {
        List<DailyTimesheet> timesheet = repo.listAll();
        System.out.println("Timesheet");
        return timesheet;
    }

    @Override
    public DailyTimesheet UpdateDailyTimeSheet(DailyTimesheet dts) {

        DailyTimesheet dailyTimesheet = repo.getDailyTimeSheets(dts.getDailyTimesheetId());
        DailyTimesheet details = dts;
        details.setId(dailyTimesheet.getId());
        DailyTimesheet.update(details);
        return dts;
    }

    @Override
    public DailyTimesheet getDailyTimeSheetBydailyTimesheetId(String dailyTimesheetId) {
        DailyTimesheet dailyTimeSheet = repo.getDailyTimeSheets(dailyTimesheetId);
        return dailyTimeSheet;
    }

    @Override
    public void deleteDailyTimeSheetBydailyTimesheetId(String dailyTimesheetId) {
        repo.deleteDailyTimeSheetBydailyTimesheetId(dailyTimesheetId);
    }

    /*
     * @Override
     * public ProjectDetails getProjectDetailsById(String projectId) {
     * ProjectDetails projectDetails = repo.getProjectDetailsById(projectId);
     * if (projectDetails == null) {
     * throw new WebApplicationException("The Resource is empty ", 404);
     * }
     * return projectDetails;
     * }
     */

}
