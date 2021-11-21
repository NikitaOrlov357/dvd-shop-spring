package com.example.dvdshopspring.dto;

import java.io.Serializable;

public class Dvd implements Serializable {
    private String title;
    private String date;
    private int mpaaRating;
    private String nameOfDirector;
    private String studio;
    private String note;

    public Dvd(String title, String date, int mpaaRating, String nameOfDirector, String studio,
               String note) {
        this.title = title;
        this.date = date;
        this.mpaaRating = mpaaRating;
        this.nameOfDirector = nameOfDirector;
        this.studio = studio;
        this.note = note;
    }

    @Override
    public String toString() {
        return  "1) название - " + title + '\n' +
                "2) дата - " + date + '\n' +
                "3) MPAA рейтинг - " + mpaaRating + '\n' +
                "4) режиссёра - " + nameOfDirector + '\n' +
                "5) студия - " + studio + '\n' +
                "6) заметки - " + note + '\n'   ;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date.toString();
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(int mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getNameOfDirector() {
        return nameOfDirector;
    }

    public void setNameOfDirector(String nameOfDirector) {
        this.nameOfDirector = nameOfDirector;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
