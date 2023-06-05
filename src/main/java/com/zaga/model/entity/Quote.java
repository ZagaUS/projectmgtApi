package com.zaga.model.entity;

import java.time.LocalDate;

import org.bson.types.ObjectId;

import com.aayushatharva.brotli4j.common.annotations.Local;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({ "id" })
@MongoEntity(collection = "Quotes", database = "ProjectManagement")
public class Quote extends PanacheMongoEntity {

    public ObjectId id;
    public String quoteId;
    public String projectId;
    public String quoteStatus;
    public LocalDate startDate; // start date
    public LocalDate endDate;
    public LocalDate validDate;
    public LocalDate date; // start date
    // public String from; // companyAddress
    public String to; // clientAddress
    // public String serviceDescription;
    public Float totalManDays; // changed fieldname
    public Float unitPrice; // changed fieldname
    public Currency clientCurrency;
    public Float totalAmount; // changed datatype

    public String duration; // peroid
    // public String manDays;
    public Float totalPrice;
    public Float gstAmount;
    public String employeeRole;
    public String employeeName;
    public String projectName;
    public String gstPercent;
    
    
    // public String quoteName;

    public String po;
    public String sfdc;
    public String pa;
    public Boolean pdfStatus;
  
}
