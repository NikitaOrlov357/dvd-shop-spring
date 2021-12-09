package com.example.dvdshopspring.dao;

import com.example.dvdshopspring.dao.exceptions.*;
import com.example.dvdshopspring.dto.Dvd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.DateTimeException;
import java.util.ArrayList;

@Slf4j
@Repository
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
            String sql = "INSERT INTO dvdshop (mpaa_rating, title, studio, note, name_of_director, \"date\") VALUES " +
                    "(?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                //statement.execute("INSERT INTO table VALUES (23, \'name\')");

                String ttl = dvd.getTitle();
                int mpr = dvd.getMpaaRating();
                String dt = dvd.getDate();
                String nofd = dvd.getNameOfDirector();
                String st = dvd.getStudio();
                String nt = dvd.getNote();

                preparedStatement.setString(2, ttl);
                preparedStatement.setInt(1, mpr);
                preparedStatement.setString(3, st);
                preparedStatement.setString(4, nt);
                preparedStatement.setString(5, nofd);
                preparedStatement.setString(6, dt);
                System.out.println(preparedStatement.toString());
                preparedStatement.execute();

//                statement.executeUpdate("INSERT INTO dvdshop (mpaa_rating, name,) VALUES (" + mpr +","+
//                        "'" + ttl + "'" + "," + st + "," + nt + "," + nofd + "," + dt + ")");
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

    public void delete (String title) throws DvdDeleteException, DatabaseConnectionException {
        try (Connection connection = DriverManager.getConnection(url, user, password )){

            String sql = "DELETE FROM dvdshop WHERE title=?" ;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1, title);
                log.debug("sql={}", preparedStatement);
                preparedStatement.execute();
                log.info("удален диск {}", title );
            }
            catch (SQLException exception){
                throw new DvdDeleteException(exception);
            }
        }
        catch (DvdDeleteException exception){
            throw exception;
        }
        catch (SQLException exception){
            throw new DatabaseConnectionException (exception);
        }
    }

    public void update (Dvd dvd) throws DvdUpdateException, DatabaseConnectionException {
        try (Connection connection = DriverManager.getConnection(url, user, password )){

            String sql = "UPDATE dvdshop set mpaa_rating=?, studio=?, note=?, name_of_director=?, date=? WHERE title=?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                int mpr = dvd.getMpaaRating();
                String dt = dvd.getDate();
                String nofd = dvd.getNameOfDirector();
                String st = dvd.getStudio();
                String nt = dvd.getNote();
                String ttl = dvd.getTitle();

                preparedStatement.setInt(1, mpr);
                preparedStatement.setString(2, st);
                preparedStatement.setString(3, nt);
                preparedStatement.setString(4, nofd);
                preparedStatement.setString(5, dt);
                preparedStatement.setString(6, ttl);

                System.out.println(preparedStatement);
                preparedStatement.execute();
                System.out.println("поменялось");
            }
            catch (SQLException exception){
                throw new DvdUpdateException(exception);
            }
        }
        catch (DvdUpdateException exception){
            throw exception;
        }
        catch (SQLException exception){
            throw new DatabaseConnectionException (exception);
        }
    }

    public ArrayList<Dvd> getAllDvd ()throws SQLException, GetAllDvdException {
            try (Connection connection = DriverManager.getConnection
                    (url, user, password )){
                String sql = "SELECT * FROM dvdshop";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    ResultSet resultSet = preparedStatement.executeQuery();
                    ArrayList<Dvd> allDvd = new ArrayList<>();
                    while (resultSet.next()){
                        int mpr = resultSet.getInt("mpaa_rating");
                        String dt = resultSet.getString("title");
                        String nofd = resultSet.getString("studio");
                        String st = resultSet.getString("note");
                        String nt = resultSet.getString("name_of_director");
                        String ttl = resultSet.getString("date");
                        Dvd dvd = new Dvd(ttl, dt, mpr,nofd,st,nt);
                        allDvd.add(dvd);
                    }
                    return allDvd;
                }
                catch (SQLException exception){
                    throw new GetAllDvdException(exception);
                }

            }
            catch (SQLException exception){
                throw new DatabaseConnectionException (exception);
            }
        }

}
