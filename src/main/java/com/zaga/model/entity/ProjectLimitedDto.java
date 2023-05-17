package com.zaga.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectLimitedDto {
    public String projectId;
    public String projectName;
    public String employeeName;
    public String projectManager;
    public ProjectType projectType;
}
