package com.example.dvdshopspring.controller;

import com.example.dvdshopspring.dao.exceptions.DatabaseConnectionException;
import com.example.dvdshopspring.dao.exceptions.DvdAdditionException;
import com.example.dvdshopspring.dao.exceptions.DvdDeleteException;
import com.example.dvdshopspring.dto.Dvd;
import com.example.dvdshopspring.service.DvdService;
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
        System.out.println(dvd);
        dvdService.addDvd(dvd);
        return new RedirectView("/ui/dvd/adding");
    }

    @PostMapping (value = "delete/dvd")
    public RedirectView deleteDvd (@ModelAttribute Dvd dvd) throws DatabaseConnectionException, DvdDeleteException {
        System.out.println(dvd);
        dvdService.deleteDvdByTitle(dvd.getTitle());
        return new RedirectView("/ui/dvd/deleting");
    }
}
