package com.example.dvdshopspring.service;

import com.example.dvdshopspring.dao.DvdDao;
import com.example.dvdshopspring.dao.DvdDaoDb;
import com.example.dvdshopspring.dao.exceptions.*;
import com.example.dvdshopspring.dto.Dvd;
import com.example.dvdshopspring.dto.Fields;
import com.example.dvdshopspring.service.exceptions.DvdWasNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.ListIterator;

@Service
public class DvdService  {
    private ArrayList<Dvd> dvdArrayList = new ArrayList<>();
    private final DvdDaoDb dvdDaoDb;

    private DvdService(DvdDaoDb dvdDaodb){
        this.dvdDaoDb = dvdDaodb;
    }

    public void addDvd (Dvd dvd) throws DvdAdditionException, DatabaseConnectionException {
        dvdDaoDb.add(dvd);
    }

//    public void addDvd  (String title, String date, int mpaaRating, String nameOfDirector, String studio,
//                        String note){
//        Dvd dvd = new Dvd(title, date, mpaaRating, nameOfDirector, studio, note);
//        dvdArrayList.add(dvd);
//    }

    public void deleteDvdByTitle (String title ) throws DvdDeleteException, DatabaseConnectionException{
        dvdDaoDb.delete(title);
    }

    public void updateDvd (Dvd dvd ) throws DvdUpdateException, DatabaseConnectionException{
        dvdDaoDb.update(dvd);
    }

    public void removeDvd (String title){
        ListIterator<Dvd> iterator = dvdArrayList.listIterator();
        while (iterator.hasNext()){
            if (iterator.next().getTitle().equals(title)){
                iterator.remove();
                break;
            }
        }
    }

    public void editDvd (String title, Fields fields, String correctedField) throws DvdWasNotFoundException{
        ListIterator<Dvd> iterator = dvdArrayList.listIterator();
        Dvd foundDvd = null;
        while (iterator.hasNext()){
            Dvd dvd = iterator.next();
            if (dvd.getTitle().equals(title)) {
                foundDvd = dvd;
                break;
            }
        }
        if (foundDvd == null){
            throw new DvdWasNotFoundException();
        }

        switch (fields) {
            case DATE -> foundDvd.setDate(correctedField);
            case TITLE -> foundDvd.setTitle(correctedField);
            case NOTE -> foundDvd.setNote(correctedField);
            case NAME_OF_DIRECTOR -> foundDvd.setNameOfDirector(correctedField);
            case STUDIO -> foundDvd.setStudio(correctedField);
            case MPAA_RATING -> foundDvd.setMpaaRating(Integer.parseInt(correctedField));
        }
    }

    public Dvd getDvdByName (String title) throws DvdWasNotFoundException{
        for (Dvd dvd : dvdArrayList) {
            if (dvd.getTitle().equals(title)) {
                return dvd;
            }
        }
        throw new DvdWasNotFoundException();
    }

    public ArrayList<Dvd> getDvdArrayList() {
        return dvdArrayList;
    }

//    public void saveDvdLib () throws UnableToSaveException {
//        dvdDao.save(dvdArrayList);
//    }
//
//    public void loadDvdLib () throws UnableToLoadException {
//        dvdArrayList = dvdDao.load();
//    }
}
