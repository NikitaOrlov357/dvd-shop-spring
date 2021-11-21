package com.example.dvdshopspring.date;

import com.example.dvdshopspring.date.exceptions.DayException;
import com.example.dvdshopspring.date.exceptions.MonthException;
import com.example.dvdshopspring.date.exceptions.YearException;

import java.io.Serializable;

public class Date implements Comparable<Date>, Serializable {
    private final int day;
    private final int month;
    private final int year;

    public Date(int day, int month, int year) throws DayException, MonthException, YearException {

        if(day > 31 || day < 1 )  {
            throw new DayException();
        }
        if(month > 12 || month < 1){
            throw new MonthException();
        }
        if(year <= 0){
            throw new YearException();
        }

        this.day = day;
        this.month = month;
        this.year = year;

    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return day + "." + month + "." + year;
    }

    @Override
    public int compareTo(Date o) {
        if(this.year > o.getYear()){
            return 1;
        }
        else if(this.year < o.getYear()){
            return -1;
        }
        else {
            if(this.month > o.getMonth()){
                return 1;
            }
            else if (this.month < o.getMonth()){
                return -1;
            }
            else {
                return Integer.compare(this.day, o.getDay());
            }
        }
    }
}
