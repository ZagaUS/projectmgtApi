package com.zaga.model.dto;

import java.time.LocalDate;

import javax.enterprise.context.ApplicationScoped;

import com.zaga.model.entity.Currency;
import com.zaga.model.entity.ProjectType;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApplicationScoped
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewProjectDetails{
    
    public String employeeName;
    public String employeeEmail;
    // public String employeeId;
    public String employeeRole;
    public String projectId;
    public String projectManager, projectName;
    public String clientName;
    public String clientCountry;
    public String clientTimezone;
    public String clientAddress;
    public String clientEmail;
    public Currency clientCurrency;
    public String duration; 
    public LocalDate startDate;
    public LocalDate endDate;
    public String quoteId;
    public LocalDate validDate;
    // public String serviceDescription;
    public Float totalManDays;
    public Float unitPrice;
    public String po;
    public String sfdc;
    public String pa;
    public ProjectType projectType;    

}
