package com.example.dvdshopspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dvd implements Serializable {
    private String title;
    private String date;
    private int mpaaRating;
    private String nameOfDirector;
    private String studio;
    private String note;
}
