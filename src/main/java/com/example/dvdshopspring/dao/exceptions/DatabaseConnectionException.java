package com.example.dvdshopspring.dao.exceptions;

import java.sql.SQLException;

public class DatabaseConnectionException extends SQLException {

    public DatabaseConnectionException (Throwable cause) {
        super(cause);
    }
}
