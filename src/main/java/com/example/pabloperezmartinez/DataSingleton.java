package com.example.pabloperezmartinez;

import java.time.LocalDate;

import com.example.models.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataSingleton {
    private static final DataSingleton instance= new DataSingleton();
    private ObservableList<Student> studentList = FXCollections.observableArrayList(
        new Student("1718131772", "Pablo", "Villamil", LocalDate.of(1995, 6, 23) , "pvillamil@puce.edu.ec", "3331585", 2),
        new Student("1706086699", "Juan", "Isaza", LocalDate.of(1994, 8, 9) , "jisaza@puce.edu.ec", "3221868", 4),
        new Student("1703525855", "Martin", "Vargas", LocalDate.of(1997, 7, 25) , "mvargas@puce.edu.ec", "2350696", 3)
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
