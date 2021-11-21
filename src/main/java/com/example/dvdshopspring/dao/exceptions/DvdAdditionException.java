package com.example.dvdshopspring.dao.exceptions;

import java.sql.SQLException;

public class DvdAdditionException extends SQLException {
    public DvdAdditionException (Throwable cause) {
        super(cause);
    }
}
