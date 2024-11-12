package com.example.demo1;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.IOException;


public class adminPageController {
    @FXML
    private AnchorPane homePane;
    @FXML
    private TextField CSVTF;

    @FXML
    public void Homebtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminHomePage.fxml"));
        Node node = loader.load();
        homePane.getChildren().setAll(node);
    }

    @FXML
    public void Adminsbtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminTab.fxml"));
        Node node = loader.load();
        homePane.getChildren().setAll(node);
    }

    @FXML
    public void studentbtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminStudents.fxml"));
        Node node = loader.load();
        homePane.getChildren().setAll(node);
    }


    @FXML
    void insertCSV(ActionEvent event) throws FileNotFoundException {
        ApplicationJDBC insert = new ApplicationJDBC();
        insert.importStudentCSV(CSVTF.getText());
    }

    @FXML
    void Notesbtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminNotes.fxml"));
        Node node = loader.load();
        homePane.getChildren().setAll(node);
    }


}
