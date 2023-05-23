package com.zaga.model.entity;

import java.time.LocalDate;
import java.util.List;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({ "id" })
@MongoEntity(collection = "DailyTimesheet", database = "ProjectManagement")
public class DailyTimesheet extends PanacheMongoEntity {

    public ObjectId id;
    public String projectId;
    public String dailyTimesheetId;
    public String projectName;
    public String employeeName;
    public Double hours;
    public LocalDate date;
    public String supportTicket;
    public List<String> clientOwners;
    public List<String> redHatOwners;
    public String description;
    public String timesheetType;

}
