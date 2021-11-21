package com.example.dvdshopspring.dao.exceptions;

import java.io.IOException;

public class UnableToSaveException extends IOException {
    public UnableToSaveException(Throwable cause){
        super(cause);
    }
}
