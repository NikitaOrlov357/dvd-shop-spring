package com.example.dvdshopspring.dao;

import com.example.dvdshopspring.dao.exceptions.*;
import com.example.dvdshopspring.dto.Dvd;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Slf4j
@Repository
public class DvdDaoDb {

    private HikariDataSource hikariDataSource;

    public DvdDaoDb( HikariDataSource hikariDataSource) {

        this.hikariDataSource = hikariDataSource;
    }

    public void add (Dvd dvd) throws DvdAdditionException, DatabaseConnectionException {
        try (Connection connection = hikariDataSource.getConnection()){
            String sql = "INSERT INTO dvdshop (mpaa_rating, title, studio, note, name_of_director, \"date\") VALUES " +
                    "(?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

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
                preparedStatement.execute();

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
        try (Connection connection = hikariDataSource.getConnection()){

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
        try (Connection connection = hikariDataSource.getConnection()){

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
                preparedStatement.execute();
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

    public ArrayList<Dvd> getAllDvd ()throws DatabaseConnectionException, GetAllDvdException {
            try (Connection connection = hikariDataSource.getConnection()){
                String sql = "SELECT * FROM dvdshop";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    ResultSet resultSet = preparedStatement.executeQuery();
                    ArrayList<Dvd> allDvd = new ArrayList<>();
                    while (resultSet.next()){
                        allDvd.add(getDvd(resultSet));
                    }
                    return allDvd;
                }
                catch (SQLException exception){
                    throw new GetAllDvdException(exception);
                }

            }
            catch (GetAllDvdException exception){
                throw exception;
            }
            catch (SQLException exception){
                throw new DatabaseConnectionException (exception);
            }
        }
    public Dvd getDvdByName (String title)throws DatabaseConnectionException, GetDvdByNameException  {
        try (Connection connection = hikariDataSource.getConnection()){
            String sql = "SELECT * FROM dvdshop WHERE title = ?" ;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, title);
                ResultSet resultSet = preparedStatement.executeQuery();
                return getDvd(resultSet);
            }
            catch (SQLException exception){
                throw new GetDvdByNameException(exception);
            }

        }
        catch (GetDvdByNameException exception){
            throw exception;
        }
        catch (SQLException exception){
            throw new DatabaseConnectionException (exception);
        }
    }

    private Dvd getDvd (ResultSet resultSet) throws SQLException{
        int mpr = resultSet.getInt("mpaa_rating");
        String dt = resultSet.getString("date");
        String nofd = resultSet.getString("name_of_director");
        String st = resultSet.getString("studio");
        String nt = resultSet.getString("note");
        String ttl = resultSet.getString("title");
        return new Dvd(ttl, dt, mpr,nofd,st,nt);
    }

}
