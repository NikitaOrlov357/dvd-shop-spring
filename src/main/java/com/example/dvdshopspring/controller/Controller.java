package com.example.dvdshopspring.controller;

import com.example.dvdshopspring.dao.DvdDaoDb;
import com.example.dvdshopspring.dao.exceptions.DatabaseConnectionException;
import com.example.dvdshopspring.dao.exceptions.DvdAdditionException;
import com.example.dvdshopspring.dto.Dvd;
import com.example.dvdshopspring.service.DvdService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private final DvdService dvdService;

    public Controller( DvdService dvdService){
        this.dvdService =dvdService;
    }

    @PostMapping(value = "dvd", consumes = "application/json")
    public String addDvd(@RequestBody Dvd dvd) {
        try {
            dvdService.addDvd(dvd);
        }
        catch (DvdAdditionException exception){
            return "ошибка добавления";
        }
        catch (DatabaseConnectionException exception){
            return "ошибка с подключением";
        }
        //dvdDaoDb.add(dvd);
        return dvd.toString();
    }

    @GetMapping(value = "/")
        public String p (){
        return "I am working";
    }


}
