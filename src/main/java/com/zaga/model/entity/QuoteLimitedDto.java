package com.zaga.model.entity;

import java.time.LocalDate;

import javax.enterprise.context.ApplicationScoped;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApplicationScoped
public class QuoteLimitedDto {
    public String projectName;
    public String quoteNumber;
    public LocalDate validDate;
    public Float totalAmount;
    public Float totalManDays;
    public Boolean pdfStatus;
}
