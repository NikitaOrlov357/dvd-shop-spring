package com.example.dvdshopspring.controller;

import com.example.dvdshopspring.dao.exceptions.DatabaseConnectionException;
import com.example.dvdshopspring.dao.exceptions.DvdAdditionException;
import com.example.dvdshopspring.dto.Dvd;
import com.example.dvdshopspring.service.DvdService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UiController {
    private final DvdService dvdService;

    public UiController( DvdService dvdService ){
        this.dvdService = dvdService;
    }

    @GetMapping(value = "ui/dvd")
    public String mainIndex (Model model) {
        model.addAttribute("dvd", new Dvd());
        return "add";
    }

    @PostMapping(value = "dvd")
    public RedirectView addDvd(@ModelAttribute Dvd dvd, Model model) throws DvdAdditionException, DatabaseConnectionException {
        System.out.println(dvd);
        dvdService.addDvd(dvd);
        return new RedirectView("ui/dvd");
    }

}
