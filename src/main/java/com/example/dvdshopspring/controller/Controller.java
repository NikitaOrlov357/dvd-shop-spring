package com.example.dvdshopspring.controller;

import com.example.dvdshopspring.dao.DvdDaoDb;
import com.example.dvdshopspring.dao.exceptions.DatabaseConnectionException;
import com.example.dvdshopspring.dao.exceptions.DvdAdditionException;
import com.example.dvdshopspring.dao.exceptions.DvdDeleteException;
import com.example.dvdshopspring.dao.exceptions.DvdUpdateException;
import com.example.dvdshopspring.dto.Dvd;
import com.example.dvdshopspring.service.DvdService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public class Controller {
    private final DvdService dvdService;

    public Controller( DvdService dvdService ){
        this.dvdService = dvdService;
    }

    @PostMapping(value = "dvd", consumes = "application/json")
    public ResponseEntity addDvd(@RequestBody Dvd dvd) {
        try {
            dvdService.addDvd(dvd);
        }
        catch (DvdAdditionException exception){
            return new ResponseEntity<String>("ошибка добавления", HttpStatus.BAD_REQUEST);
        }
        catch (DatabaseConnectionException exception){
            return new ResponseEntity<String>("ошибка с подключением", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //dvdDaoDb.add(dvd);
        return new ResponseEntity("ok", HttpStatus.OK);
    }

    @DeleteMapping(value = "dvd", consumes = "application/json")
    public String deleteDvd(@RequestBody Dvd dvd) {
        try {
            dvdService.deleteDvdByTitle(dvd.getTitle());
        }
        catch (DvdDeleteException exception){
            return "ошибка с удалением";
        }
        catch (DatabaseConnectionException exception){
            return "ошибка с подключением";
        }

        return dvd.getTitle();
    }

    @PatchMapping(value = "dvd", consumes = "application/json")
    public String updateDvd(@RequestBody Dvd dvd) {
        try {
            dvdService.updateDvd(dvd);
        }
        catch (DvdUpdateException exception){
            return "ошибка с удалением";
        }
        catch (DatabaseConnectionException exception){
            return "ошибка с подключением";
        }

        return dvd.toString();
    }

    @GetMapping(value = "/")
        public String p (){
        return "I am working";
    }


}
