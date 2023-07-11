package com.zaga.model.entity;

import java.time.LocalDate;

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
    public String clientAddress;
    public Currency clientCurrency;
    public Float unitPrice;
    // public String duration; // reference for period
    public LocalDate startDate;
    public LocalDate endDate;
    public Boolean quoteFlag;
    public Boolean poStatus;
}
