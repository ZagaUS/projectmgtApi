package com.zaga.model.dto;

import java.util.List;

import com.zaga.model.entity.CountryList;
import com.zaga.model.entity.CurrencyList;
import com.zaga.model.entity.TimeZones;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DropDownDto {
    public CurrencyList currency;
    public CountryList country;
    public TimeZones timezones;
}
