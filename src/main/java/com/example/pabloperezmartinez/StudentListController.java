package com.example.pabloperezmartinez;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.models.Student;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import java.io.IOException;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentListController {
    
    DataSingleton data = DataSingleton.getInstance();

    private ObservableList<Student> studentData;

    @FXML private TableView studentsTableView;
    @FXML private TableColumn idCol;
    @FXML private TableColumn firstNameCol;
    @FXML private TableColumn lastNameCol;
    @FXML private TableColumn emailCol;
    @FXML private TableColumn ageCol;

    @FXML
    public void initialize() {
        // TODO Auto-generated method stub
        this.idCol.setCellValueFactory(new PropertyValueFactory<Student, String>("id"));
        this.firstNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
        this.lastNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
        this.emailCol.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
        this.ageCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("age"));
        this.studentData = data.getStudents();
        this.studentsTableView.setItems(this.studentData);
    }

    @FXML
    private void displayStudentForm() throws IOException {
        App.setRoot("studentForm");
    }
}
