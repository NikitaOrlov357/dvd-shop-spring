package com.example.dvdshopspring.date.exceptions;

public class YearException extends DateException{
    public YearException(){
        super(new Throwable("The year is incorrect"));
    }
}
