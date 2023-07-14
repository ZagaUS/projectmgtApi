package com.zaga.serviceImplementation;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.zaga.model.dto.DropDownDto;
import com.zaga.model.entity.CountryDropDown;
import com.zaga.model.entity.CountryList;
import com.zaga.model.entity.CurrencyList;
import com.zaga.model.entity.TimeZones;
import com.zaga.repository.CountryDropDownRepo;
import com.zaga.repository.CountryRepo;
import com.zaga.repository.CurrencyRepo;
import com.zaga.repository.TimezoneRepo;
import com.zaga.service.DropDown;

@ApplicationScoped
public class DropDownImpl implements DropDown {

    @Inject
    CountryDropDownRepo drop;

    @Inject
    CountryRepo countryRepo;

    @Inject
    CurrencyRepo currencyRepo;

    @Inject
    TimezoneRepo timezoneRepo;

    @Override
    public DropDownDto listDropDowns() {
        DropDownDto data = new DropDownDto();
        data.setCountry(countryRepo.findAll().firstResult());
        data.setCurrency(currencyRepo.findAll().firstResult());
        data.setTimezones(timezoneRepo.findAll().firstResult());
        return data;

    }

    @Override
    public void createCountry(CountryList list) {
        countryRepo.persist(list);
    }

    @Override
    public void createCurrency(CurrencyList list) {
        currencyRepo.persist(list);
    }

    @Override
    public void createTimezone(TimeZones list) {
        timezoneRepo.persist(list);
    }

    @Override
    public void CreateCountryDropDown(CountryDropDown dropDown) {
        drop.persist(dropDown);
    }

    @Override
    public List<CountryDropDown> getCountryDropDowns() {
        return drop.listAll();
    
    }

}
