package com.example.dvdshopspring.controller;

import com.example.dvdshopspring.dao.exceptions.DatabaseConnectionException;
import com.example.dvdshopspring.dao.exceptions.DvdAdditionException;
import com.example.dvdshopspring.dao.exceptions.DvdDeleteException;
import com.example.dvdshopspring.dao.exceptions.GetAllDvdException;
import com.example.dvdshopspring.dto.Dvd;
import com.example.dvdshopspring.service.DvdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

@Slf4j
@Controller


public class UiController {
    private final DvdService dvdService;

    public UiController( DvdService dvdService ){
        this.dvdService = dvdService;
    }

    @GetMapping(value = "ui/dvd/adding")
    public String showAddingPage (Model model) {
        model.addAttribute("dvd", new Dvd());
        return "add";
    }

    @GetMapping(value = "ui/dvd/deleting")
    public String showDeletingPage (Model model) {
        model.addAttribute("dvd", new Dvd());
        return "delete";
    }

    @PostMapping(value = "dvd")
    public RedirectView addDvd(@ModelAttribute Dvd dvd) throws DvdAdditionException, DatabaseConnectionException {
        log.debug(dvd.toString());
        dvdService.addDvd(dvd);
        log.debug("добавлено");
        return new RedirectView("/ui/dvd/adding");
    }

    @PostMapping (value = "delete/dvd")
    public RedirectView deleteDvd (@ModelAttribute Dvd dvd) throws DatabaseConnectionException, DvdDeleteException {
        dvdService.deleteDvdByTitle(dvd.getTitle());
        return new RedirectView("/ui/dvd/deleting");
    }

    @GetMapping(value = "dvd")
    public String showAllDvd (Model model) throws DatabaseConnectionException, GetAllDvdException {
        ArrayList<Dvd> list = dvdService.getDvdArrayList();
        log.debug(list.toString());
        model.addAttribute("dvds", list);
        return "show";
    }

    @GetMapping(value = "menu")
    public String mainMenu (){
        return "menu";
    }

    @GetMapping("test")
    public String test(){

        return null;
    }


}
