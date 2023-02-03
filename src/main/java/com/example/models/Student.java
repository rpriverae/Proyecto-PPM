package com.example.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import com.example.dbhandler.PostgresConnector;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Student extends Person {
    private int level;

    /**
     * Constructor
     * @param id
     * @param firstName
     * @param lastName
     * @param birthday
     * @param email
     * @param phoneNumber
     * @param level
     */
    public Student(String id, String firstName, String lastName, LocalDate birthday, String email, String phoneNumber,
            int level) {
        super(id, firstName, lastName, birthday, email, phoneNumber);
        this.level = level;
    }

    /**
     * Gets level
     * @return
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets level
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }    
    
    /**
     * Prints the student
     */
    @Override
    public void print() {
        // TODO Auto-generated method stub
        super.print();
        System.out.println("Level: " + this.level);
    }

    @Override
    public String getFullName() {
        // TODO Auto-generated method stub
        return super.getFullName();
    }

    /**
     * Gets simple string property from id
     * @return SimpleStringProperty
     */
    public SimpleStringProperty idProperty() {
        return new SimpleStringProperty(this.getId());
    }

    /**
     * Gets simple string property from name
     * @return
     */
    public SimpleStringProperty firstNameProperty() {
        return new SimpleStringProperty(this.getFirstName());
    }

    /**
     * Gets simple string property from email
     * @return
     */
    public SimpleStringProperty emailProperty() {
        return new SimpleStringProperty(this.getEmail());
    }

    /**
     * Gets simple string property from lastName
     * @return
     */
    public SimpleStringProperty lastNameProperty() {
        return new SimpleStringProperty(this.getLastName());
    }

    /**
     * Gets simple string property from age
     * @return
     */
    public SimpleIntegerProperty ageProperty() {
        return new SimpleIntegerProperty(this.getAge());
    }

    public SimpleIntegerProperty level() {
        return new SimpleIntegerProperty(this.getLevel());
    }

    public void save() {
        if (!findById(this.getId()).isPresent()) {
        final String sql = "INSERT INTO student" +
        " (id, first_name, last_name, birthday, email, phone_number, level) VALUES " +
        " (?, ?, ?, ?, ?, ?, ?);";
        PostgresConnector pgConnector = new PostgresConnector();
        Connection connection = pgConnector.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1, this.getId());
            preparedStatement.setString(2, this.getFirstName());
            preparedStatement.setString(3, this.getLastName());
            preparedStatement.setDate(4, Date.valueOf(getBirthday()));
            preparedStatement.setString(5, this.getEmail());
            preparedStatement.setString(6, this.getPhoneNumber());
            preparedStatement.setInt(7, this.getLevel());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            System.out.println(e.getMessage());
        } 
    } else {

        final String sql = "UPDATE student SET first_name = ?, last_name= ?  , email = ? WHERE ID = ? ";
        PostgresConnector pgConnector = new PostgresConnector();
        Connection connection = pgConnector.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            preparedStatement.setString(1, this.getFirstName());
            preparedStatement.setString(2, this.getLastName());
            preparedStatement.setString(3, this.getEmail());
            preparedStatement.setString(4, this.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
           e.printStackTrace();
        }
    }
    }

    public List<Student> findAll() {
        final String sql = "select * from student ";
        final List<Student> result = new ArrayList<>();
        PostgresConnector pgConnector = new PostgresConnector();
        try (Connection connection = pgConnector.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Student student = new Student(
                        rs.getString("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("birthday").toLocalDate(),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getInt("level"));
                result.add(student);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public void delete(String id) {
        final String sql = "delete from student where id = ?";
        PostgresConnector pgConnector = new PostgresConnector();
        Connection connection = pgConnector.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public Optional<Student> findById(String id) {
        final String sql = "select * from student where id = ? ";
        Optional<Student> result = Optional.empty();
        PostgresConnector pgConnector = new PostgresConnector();
        try (Connection connection = pgConnector.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Student student = new Student(
                        rs.getString("id"),
                        rs.getString("first_name"),
                        rs.getString("level"),
                        LocalDate.now(),
                        rs.getString("email"),
                        rs.getString("last_name"),
                        rs.getInt("phone_number"));
                result = Optional.of(student);
            }

        } catch (SQLException e) {
           e.printStackTrace();
        }
        return result;
    }
}