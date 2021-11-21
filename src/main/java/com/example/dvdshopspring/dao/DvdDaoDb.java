package com.example.dvdshopspring.dao;

import com.example.dvdshopspring.dao.exceptions.DatabaseConnectionException;
import com.example.dvdshopspring.dao.exceptions.DvdAdditionException;
import com.example.dvdshopspring.dto.Dvd;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DateTimeException;

@Component
public class DvdDaoDb {
    private String url;
    private String user;
    private String password;

    public DvdDaoDb(@Value("${db.url}") String url, @Value("${db.user}") String user,
                    @Value("${db.password}") String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void add (Dvd dvd) throws DvdAdditionException, DatabaseConnectionException {
        try (Connection connection = DriverManager.getConnection
                (url, user, password )){
            try (Statement statement = connection.createStatement()) {

                //statement.execute("INSERT INTO table VALUES (23, \'name\')");

                String st = dvd.getTitle();
                int mp = dvd.getMpaaRating();
                statement.executeUpdate("INSERT INTO dvdshop (mpaa_rating, name) VALUES (" + mp +","+ st + ")");

            }
            catch (SQLException exception){
                throw new DvdAdditionException(exception);
            }
        }
        catch (DvdAdditionException exception){
            throw exception;
        }
        catch (SQLException exception){
            throw new DatabaseConnectionException (exception);
        }
    }
}
