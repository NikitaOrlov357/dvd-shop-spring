package com.example.dvdshopspring.dao.exceptions;

import java.sql.SQLException;

public class DvdDeleteException extends SQLException {
    public DvdDeleteException (Throwable cause) {
        super(cause);
    }
}