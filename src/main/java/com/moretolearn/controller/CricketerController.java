package com.moretolearn.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.moretolearn.model.Cricketer;
import com.moretolearn.model.Group;
import com.moretolearn.service.CricketerService;

import java.util.List;
import java.util.Optional;

@Controller
public class CricketerController {

    private final CricketerService cricketerService;

    public CricketerController(CricketerService cricketerService) {
        this.cricketerService = cricketerService;
    }

    @QueryMapping
    public List<Cricketer> findAll() {
        return cricketerService.findAll();
    }

    @QueryMapping
    public Optional<Cricketer> findOne(@Argument Integer id) {
        return cricketerService.findOne(id);
    }

    @MutationMapping
    public Cricketer create(@Argument String name, @Argument Group group) {
        return cricketerService.create(name,group);
    }

    @MutationMapping
    public Cricketer update(@Argument Integer id, @Argument String name, @Argument Group group) {
        return cricketerService.update(id,name,group);
    }

    @MutationMapping
    public Cricketer delete(@Argument Integer id) {
        return cricketerService.delete(id);
    }
}