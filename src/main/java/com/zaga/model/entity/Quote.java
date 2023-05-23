package com.zaga.model.entity;

import org.bson.types.ObjectId;

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
public class Quote extends PanacheMongoEntity{
    
    public ObjectId id;
    public String quoteId;
    public String quoteStatus;
    public String date; // start date
    public String validDate; // endDate
    public String from; // companyAddress
    public String to; // clientAddress
    public String serviceDescription;
    public Float totalManDays; // changed fieldname
    public Float unitPrice; // changed fieldname
    public Currency clientCurrency;
    public Float totalAmount; // changed datatype
    
    

}
