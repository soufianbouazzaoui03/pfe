package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;


public class LoginController {


    @FXML
    public void adminAccess(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminLoginPage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 800, 500);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        HelloApplication.closeStage.close();

    }

    public void profAccess(ActionEvent e) {
        System.out.println("Hello Admin!");
    }

    public void studentAccess(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentLoginPage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 800, 500);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    TextField TF1;
    @FXML
    PasswordField PF1;
    @FXML
    Label errorLabel;




    @FXML
    public void bntLogin(ActionEvent e) throws IOException {
        new ApplicationJDBC().connection();
        try {

            ApplicationJDBC.st.setString(1, TF1.getText());
            ApplicationJDBC.st.setString(2, PF1.getText());
            ApplicationJDBC.rs = ApplicationJDBC.st.executeQuery();

            if (ApplicationJDBC.rs.next()) {
                errorLabel.setText("done");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminHome.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root, 1250, 650);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();


            } else {
                errorLabel.setText("password or email incorrect!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }





}