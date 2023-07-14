package com.zaga.serviceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import org.jboss.logging.Logger;
import org.jboss.threads.ViewExecutor;

import com.zaga.model.dto.ViewProjectDetails;
import com.zaga.model.entity.ProjectDetails;
import com.zaga.model.entity.ProjectLimitedDto;
import com.zaga.model.entity.ProjectType;
import com.zaga.model.entity.Quote;
import com.zaga.repository.PdfRepository;
import com.zaga.repository.ProjectDetailsRepository;
import com.zaga.repository.QuotesRepository;
import com.zaga.repository.SequenceRepository;
import com.zaga.service.ProjectDetailsService;

@ApplicationScoped
public class ProjectDetailsServiceImpl implements ProjectDetailsService {

    @Inject
    Logger logger;

    @Inject
    ProjectDetailsRepository repo;

    @Inject
    SequenceRepository seqRepo;

    @Inject
    QuotesRepository quoteRepo;

    @Inject
    PdfRepository pdfRepo;

    @Inject
    ViewProjectDetails viewProjectDetails;

    @Override
    public ProjectDetails createProjectDetails(ProjectDetails projectDetails) {
        // TODO Auto-generated method stub
        String seqNo = seqRepo.getSequenceCounter("Project");
        System.out.println(projectDetails);
        projectDetails.setProjectId(seqNo);

        if (canCreate(projectDetails)) {
            projectDetails.setProjectType(ProjectType.UnAssigned);
            ProjectDetails.persist(projectDetails);
        } else {

            logger.error("Could not Persist data already exists");
            throw new WebApplicationException("Already exists", 500);
        }
        return projectDetails;
    }

    @Override
    public List<ProjectLimitedDto> getProjectDetails() {

        List<ProjectDetails> projects = repo.listAll();

        if (projects.isEmpty()) {

            throw new WebApplicationException("The Resource is empty ", 500);
        }

        List<ProjectLimitedDto> projectDtoList = projects.stream()
                .map(project -> {
                    ProjectLimitedDto dto = new ProjectLimitedDto();
                    System.out.println("------project stream----- "+project);
                    dto.setProjectId(project.getProjectId());
                    dto.setProjectName(project.getProjectName());
                    dto.setEmployeeName(project.getEmployeeName());
                    dto.setProjectManager(project.getProjectManager());
                    dto.setProjectType(project.getProjectType());
                    dto.setClientAddress(project.getClientAddress());
                    dto.setClientCurrency(project.getClientCurrency());
                    // dto.setDuration(project.getDuration());
                    dto.setStartDate(project.getStartDate());
                    dto.setEndDate(project.getEndDate());
                    dto.setUnitPrice(project.getUnitPrice());
                    dto.setQuoteFlag(project.getQuoteFlag());
                    dto.setPoStatus(project.getPoStatus());
                    return dto;
                })
                .collect(Collectors.toList());

        return projectDtoList;
    }

    
    @Override
    public List<ViewProjectDetails> getProjectDetailsForInvoice() {

        List<ProjectDetails> projects = repo.listAll();

        if (projects.isEmpty()) {

            throw new WebApplicationException("The Resource is empty ", 500);
        }

        List<ViewProjectDetails> projectDtoList = projects.stream()
                .map(project -> {
                    ViewProjectDetails dto = new ViewProjectDetails();
                    System.out.println("------project stream----- "+project);
                    dto.setProjectId(project.getProjectId());
                    dto.setProjectName(project.getProjectName());
                    dto.setEmployeeName(project.getEmployeeName());
                    dto.setProjectManager(project.getProjectManager());
                    dto.setProjectType(project.getProjectType());
                    dto.setClientAddress(project.getClientAddress());
                    dto.setClientCurrency(project.getClientCurrency());
                    // dto.setDuration(project.getDuration());
                    dto.setStartDate(project.getStartDate());
                    dto.setEndDate(project.getEndDate());
                    dto.setUnitPrice(project.getUnitPrice());
                    dto.setPo(project.getPo());
                    dto.setSfdc(project.getSfdc());
                    dto.setPa(project.getPa());
                    dto.setTotalManDays(project.getTotalManDays());
                    return dto;
                })
                .collect(Collectors.toList());

        return projectDtoList;
    }

    @Override
    public ViewProjectDetails getProjectDetailsByProjectId(String projectId) {
        ProjectDetails project = repo.getProjectDetailsById(projectId);
        System.out.println(project);
        if (project == null) {
            throw new WebApplicationException("The Resource is empty ", 500);
        }
                  ViewProjectDetails dto = new ViewProjectDetails();
                    System.out.println("------project stream----- "+project);
                    // dto.setProjectId(project.getProjectId());
                    dto.setEmployeeName(project.getEmployeeName());
                    dto.setEmployeeEmail(project.getEmployeeEmail());
                    dto.setEmployeeRole(project.getEmployeeRole());
                    dto.setProjectName(project.getProjectName());                
                    dto.setProjectManager(project.getProjectManager());
                    dto.setClientName(project.getClientName());
                    dto.setClientCountry(project.getClientCountry());
                    dto.setClientTimezone(project.getClientTimezone());
                    dto.setClientAddress(project.getClientAddress());
                    dto.setClientEmail(project.getClientEmail());
                    dto.setClientCurrency(project.getClientCurrency());            
                    // dto.setDuration(project.getDuration());
                    dto.setStartDate(project.getStartDate());
                    dto.setEndDate(project.getEndDate());
                    dto.setQuoteId(project.getQuoteId());
                    dto.setValidDate(project.getValidDate());
                    dto.setProjectId(project.getProjectId());
                    dto.setTotalManDays(project.getTotalManDays());
                    dto.setUnitPrice(project.getUnitPrice());
                    dto.setPo(project.getPo());
                    dto.setSfdc(project.getSfdc());
                    dto.setPa(project.getPa());
                    dto.setProjectType(project.getProjectType());
                    return dto;
    
    }
    @Override
    public ViewProjectDetails updateProjectDetails(ViewProjectDetails projectDetails) {

        logger.info("Projectdetail inside service implementation " + projectDetails);

        ProjectDetails details = repo.getProjectDetailsById(projectDetails.getProjectId());
        if (details == null) {
            throw new WebApplicationException("The Resource is empty ", 500);
        }
        

        // ViewProjectDetails details = dto;
        details.setClientAddress(projectDetails.getClientAddress());
        details.setProjectId(projectDetails.getProjectId());
        details.setClientCountry(projectDetails.getClientCountry());
        details.setClientEmail(projectDetails.getClientEmail());
        details.setClientCurrency(projectDetails.getClientCurrency());
        details.setClientName(projectDetails.getClientName());
        details.setClientTimezone(projectDetails.getClientTimezone());
        // details.setDuration(projectDetails.getDuration());
        details.setEmployeeEmail(projectDetails.getEmployeeEmail());
        details.setEmployeeName(projectDetails.getEmployeeName());
        details.setEmployeeRole(projectDetails.getEmployeeRole());
        details.setEndDate(projectDetails.getEndDate());
        details.setPa(projectDetails.getPa());
        details.setPo(projectDetails.getPo());
        details.setProjectManager(projectDetails.getProjectManager());
        details.setProjectName(projectDetails.getProjectName());
        details.setProjectType(projectDetails.getProjectType());
        details.setQuoteId(projectDetails.getQuoteId());
        details.setSfdc(projectDetails.getSfdc());
        details.setStartDate(projectDetails.getStartDate());
        details.setTotalManDays(projectDetails.getTotalManDays());
        details.setUnitPrice(projectDetails.getUnitPrice());
        details.setValidDate(projectDetails.getValidDate());
        // details.setId(projectDetails.getId());
        ProjectDetails.update(details);
        return projectDetails;

    }

    @Override
    public void deleteProjectDetails(String projectId) {

        repo.deleteProjectDetailsById(projectId);

    }

    @Override
    public Boolean canCreate(ProjectDetails projectDetails) {

        ProjectDetails testprojectDetails = repo.checkProjectDetails(projectDetails);

        if (testprojectDetails == null) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;

    }

    @Override
    public List<ProjectDetails> getProjectDetailsbyCategory(String category) {
        List<ProjectDetails> details = repo.getProjectDetailsByProjectType(category);
        return details;
    }

    @Override
    public ProjectDetails assignProject(String projectId, String employeeName, String employeeEmail, 
            String employeeId, String employeeRole) {
       ProjectDetails projectDetails = repo.getProjectDetailsById(projectId);
       System.out.println(("----------------------------------------------------"));
       System.out.println(projectDetails);
       projectDetails.setEmployeeName(employeeName);
       projectDetails.setEmployeeEmail(employeeEmail);
    //    projectDetails.setEmployeeNumber(employeeNumber);
       projectDetails.setEmployeeId(employeeId);
       projectDetails.setEmployeeRole(employeeRole);
       projectDetails.setProjectAssignmentStatus(true);
       projectDetails.setProjectType(ProjectType.Active);
    //    List<Quote> quote = quoteRepo.getQuotesByProjectId(projectId);
    //    quote.setEmployeeName(employeeName);
    //    quote.setEmployeeRole(employeeRole);
    //    quoteRepo.update(quote);
       projectDetails.update();  
       return projectDetails;

    }

    @Override
    public ProjectDetails getProjectDetailsById(String projectId) {
        ProjectDetails  details= repo.getProjectDetailsById(projectId);
        if (details == null){
            throw new WebApplicationException("The Resource is empty ",500);
        }
        return details;
    }

   

}
