package com.zaga.repository;

import javax.enterprise.context.ApplicationScoped;

import com.zaga.model.entity.TimeZones;

import io.quarkus.mongodb.panache.PanacheMongoRepository;

@ApplicationScoped
public class TimezoneRepo implements PanacheMongoRepository<TimeZones> {

}
