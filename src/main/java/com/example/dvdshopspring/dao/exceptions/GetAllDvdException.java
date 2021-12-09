package com.example.dvdshopspring.dao.exceptions;

import java.sql.SQLException;

public class GetAllDvdException extends SQLException {

    public GetAllDvdException (Throwable cause) {
        super(cause);
    }
}