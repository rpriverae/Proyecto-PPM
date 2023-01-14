package com.example.pabloperezmartinez;

import java.time.LocalDate;

import com.example.models.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataSingleton {
    private static final DataSingleton instance= new DataSingleton();
    private ObservableList<Student> studentList = FXCollections.observableArrayList(
        new Student("123522252", "Juan", "Avilés", LocalDate.of(1992, 1, 31) , "juan@puce.edu.ec", "3512313", 2),
        new Student("123522253", "Mario", "Contero", LocalDate.of(1992, 2, 22) , "mario@puce.edu.ec", "3512313", 2),
        new Student("123522254", "Alba", "Pelaez", LocalDate.of(1992, 3, 2) , "alba@puce.edu.ec", "3512313", 2)
    );

    private DataSingleton(){}

    public static DataSingleton getInstance() {
        return instance;
    }

    public ObservableList<Student> getStudents () {
        return this.studentList;
    }

    public void addStudent (Student student) {
        this.studentList.add(student);
    }
}
