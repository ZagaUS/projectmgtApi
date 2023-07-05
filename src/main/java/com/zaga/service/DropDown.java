package com.zaga.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.zaga.model.dto.DropDownDto;
import com.zaga.model.entity.CountryDropDown;
import com.zaga.model.entity.CountryList;
import com.zaga.model.entity.CurrencyList;
import com.zaga.model.entity.TimeZones;

@ApplicationScoped
public interface DropDown {

    void CreateCountryDropDown(CountryDropDown dropDown);

    DropDownDto listDropDowns();

    void createCountry(CountryList list);

    void createCurrency(CurrencyList list);

    void createTimezone(TimeZones list);

    List<CountryDropDown> getCountryDropDowns();
}
