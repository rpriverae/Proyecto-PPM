package com.example.pabloperezmartinez;

import com.example.models.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;


public class StudentListController {

    DataSingleton data = DataSingleton.getInstance();

    private ObservableList<Student> studentData;
    @FXML
    private TableView studentsTableView;
    @FXML
    private TableColumn idCol;
    @FXML
    private TableColumn firstNameCol;
    @FXML
    private TableColumn lastNameCol;
    @FXML
    private TableColumn emailCol;
    @FXML
    private TableColumn ageCol;

    private Optional<Student> student = Optional.empty();

    @FXML
    public void initialize() {

        Student student = new Student("", "", "", LocalDate.now(), "", "", 1);

        this.studentData = FXCollections.observableArrayList(student.findAll());

        this.idCol.setCellValueFactory(new PropertyValueFactory<Student, String>("id"));
        this.firstNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
        this.lastNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
        this.emailCol.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
        this.ageCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("age"));

        this.studentsTableView.setItems(this.studentData);
    }

    @FXML
    private void displayStudentForm() throws IOException {
        App.setRoot("studentForm");
    }
    @FXML
    private void deleteStudent(ActionEvent event ){

        if(student.isPresent()){
            Student student = new Student("", "", "", LocalDate.now(), "", "", 1);
            student.delete(this.student.get().getId());
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("debe selecionar un estudiante");
            alert.showAndWait();
        }
    }
    @FXML
    private void seleccionar(MouseEvent event)  throws IOException{
        student = Optional.of((Student) this.studentsTableView.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void createStudent(ActionEvent event) throws IOException {
        App.setRoot("studentForm");
    }

    @FXML
    private void editStudent(ActionEvent event) throws IOException {

        if(student.isPresent()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("studentForm.fxml"));
            Parent root = loader.load();
            StudentFormController studentFormController = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            studentFormController.init(student.get(),stage,this);
            stage.show();

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("debe selecionar un estudiante");
            alert.showAndWait();
        }

    }


}
