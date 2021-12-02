package com.example.dvdshopspring.dao.exceptions;

import java.sql.SQLException;

public class DvdUpdateException extends SQLException {
    public DvdUpdateException (Throwable cause) {
        super(cause);
    }
}
