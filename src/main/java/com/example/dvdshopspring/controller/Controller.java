package com.example.dvdshopspring.controller;

import com.example.dvdshopspring.dao.DvdDaoDb;
import com.example.dvdshopspring.dao.exceptions.DatabaseConnectionException;
import com.example.dvdshopspring.dao.exceptions.DvdAdditionException;
import com.example.dvdshopspring.dto.Dvd;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    DvdDaoDb dvdDaoDb;
    private Object Dvd;

    public Controller(DvdDaoDb dvdDaoDb){
        this.dvdDaoDb = dvdDaoDb;
    }

    @PostMapping(value = "dvd", consumes = "application/json")
    public Dvd addDvd(@RequestBody Dvd dvd) throws DvdAdditionException, DatabaseConnectionException {
        //dvdDaoDb.add(dvd);
        System.out.println(dvd);
        return dvd;
    }

    @GetMapping(value = "/")
        public String p (){
        return "I am working";
    }


}
