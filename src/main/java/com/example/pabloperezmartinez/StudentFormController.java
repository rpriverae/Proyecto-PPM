package com.example.pabloperezmartinez;

import java.io.IOException;

import com.example.models.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class StudentFormController {
    
    DataSingleton data = DataSingleton.getInstance();

    @FXML TextField idTextField;
    @FXML TextField firstNameTextField;
    @FXML TextField lastNameTextField;
    @FXML TextField emailTextField;
    @FXML TextField phoneTextField;
    @FXML DatePicker birthDatePicker;
    @FXML ComboBox levelComboBox;
    //ObservableList<Integer> levelItems = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6);

    private String id;
    ObservableList<Integer> levelItems = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6);

    @FXML
    public void initialize() {
        levelComboBox.setItems(levelItems);
    }

    @FXML
    private void saveStudent() throws IOException {
        Student student = new Student(
            idTextField.getText(), 
            firstNameTextField.getText(),
            lastNameTextField.getText(),
            birthDatePicker.getValue(), 
            emailTextField.getText(),
            phoneTextField.getText(),
            Integer.parseInt(levelComboBox.getValue().toString()));
        
        student.save();
        
        data.addStudent(student);
        
        App.setRoot("studentList");
    }


    @FXML
    private void cancel() throws IOException {
        App.setRoot("studentList");
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void closeWindows() {
    }


    public void init(Student student, Stage stage, StudentListController studentListController) {
        idTextField.setText(student.getId());
        firstNameTextField.setText(student.getFirstName());
        lastNameTextField.setText(student.getLastName());
        birthDatePicker.setValue(student.getBirthday());
        emailTextField.setText(student.getEmail());
        phoneTextField.setText(student.getEmail());
    }
}
