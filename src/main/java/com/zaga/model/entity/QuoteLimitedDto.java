package com.zaga.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuoteLimitedDto {
    public String projectName;
    public String quoteNumber;
    public String date;
    public Float totalAmount;
    public Float totalManDays;
}
