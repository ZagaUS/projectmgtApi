package com.zaga.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.zaga.model.dto.DropDownDto;
import com.zaga.model.entity.CountryDropDown;
import com.zaga.model.entity.CountryList;
import com.zaga.model.entity.CurrencyList;
import com.zaga.model.entity.TimeZones;
import com.zaga.service.DropDown;

@Tag(name = "DropDown", description = "")
@Path("/dropdown")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DropDownApi {

    @Inject
    DropDown service;

    @GET
    public DropDownDto getdropdowns() {
        return service.listDropDowns();
    }

    @POST
    @Path("/createCountry")
    public void createCountry(CountryList list) {
        service.createCountry(list);
    }

    @POST
    @Path("/createCountryDropDown")
    public void createCountryDropDown(CountryDropDown dropdown) {
        service.CreateCountryDropDown(dropdown);
    }

    @POST
    @Path("/createCurrency")
    public void createCurrency(CurrencyList list) {
        service.createCurrency(list);
        ;
    }

    @POST
    @Path("/createTimezones")
    public void createTimezone(TimeZones list) {
        service.createTimezone(list);
    }

    @GET
    @Path("/countryDropDowns")
    public List<CountryDropDown> getCountrydropdowns() {
        return service.getCountryDropDowns();
    }

}
