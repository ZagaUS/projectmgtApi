package com.zaga.service;

import java.util.List;

import com.zaga.model.dto.ViewProjectDetails;
import com.zaga.model.entity.ProjectDetails;
import com.zaga.model.entity.ProjectLimitedDto;

public interface ProjectDetailsService {

    ProjectDetails createProjectDetails(ProjectDetails projectDetails);

    ViewProjectDetails updateProjectDetails(ViewProjectDetails dto);

    List<ProjectLimitedDto> getProjectDetails();
   List<ViewProjectDetails> getProjectDetailsForInvoice();

    ViewProjectDetails getProjectDetailsByProjectId(String projectId);

    List<ProjectDetails> getProjectDetailsbyCategory(String category);

    ProjectDetails getProjectDetailsById(String projectId);

    void deleteProjectDetails(String projectId);

    Boolean canCreate(ProjectDetails projectDetails);

    ProjectDetails assignProject(String projectId, String employeeName, String employeeEmail,  String employeeId, String employeeRole);

    // void savePdfDocument(String name, InputStream inputstream) throws IOException
    // ;

    // ProjectDetails viewProjectDetails(ProjectDetails projectDetails);}
}