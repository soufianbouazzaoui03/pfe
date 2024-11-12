package com.example.demo1;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;

public class AdminStudentController {
    static Stage close;
    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    @FXML
    private TableView<Student> tableview;

    @FXML
    private TableColumn<Student, String> NomEtPrenom;
    @FXML
    private TableColumn<Student, Integer> Number;
    @FXML
    private TableColumn<Student, String> CNE;
    @FXML
    private TextField NomEtPrenomTF;

    @FXML
    private TextField CNETF;

    public void connection() {
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=PFEAPP;encrypt=true;trustServerCertificate=true";
        String user = "soufian";
        String password = "1234";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException  e) {
            throw new RuntimeException(e);
        }

    }


//TODO REWRITE THIS METHODE.
    public void addtotable() {
        Connection cn;
        Statement st;
        ResultSet rs;
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=PFEAPP;encrypt=true;trustServerCertificate=true";
        String user = "soufian";
        String password = "1234";
        String query = "SELECT * FROM student";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn = DriverManager.getConnection(url, user, password);
            st = cn.createStatement();
            rs = st.executeQuery(query);
            ObservableList<Student> data = FXCollections.observableArrayList();

            while (rs.next())
            {
                int num = rs.getInt(1);
                String CNE = rs.getString(2);
                String NomEtPrenom = rs.getString(3);
                Student student = new Student(num, CNE, NomEtPrenom);
                data.add(student);
            }
            Number.setCellValueFactory(new PropertyValueFactory<Student, Integer>("num"));
            CNE.setCellValueFactory(new PropertyValueFactory<Student, String>("CNE"));
            NomEtPrenom.setCellValueFactory(new PropertyValueFactory<Student, String>("nomEtPrenom"));
            tableview.setItems(data);
            //tableview.getColumns().addAll(Number, CNE, NomEtPrenom);
            cn.close();
        }
        catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
        }

    }
    @FXML
    void ajouter (ActionEvent event) {
        addtotable();
    }

    @FXML
    void ajouterWindow(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("popupAjouterStudent.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        Stage popupStage = new Stage();
        popupStage.setScene(scene);
        popupStage.show();
        close = popupStage;
    }

    @FXML
    void ajouterbtn (ActionEvent event) {
        int next_NUM = 0;
        connection();
        String lastNum = "USE PFEAPP; SELECT MAX(NUM) as MaxNum FROM student;";
        String query = "INSERT INTO student VALUES (?,?,?)";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(lastNum);
            if (resultSet.next()) {
                next_NUM = resultSet.getInt(1);
                next_NUM++;
            }
            preparedStatement = connection.prepareStatement(query);
            String CNEInsert = CNETF.getText();
            String NomEtPrenomInsert = NomEtPrenomTF.getText();
            preparedStatement.setInt(1,next_NUM);
            preparedStatement.setString(2,CNEInsert);
            preparedStatement.setString(3, NomEtPrenomInsert);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            this.close.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.addtotable();
    }

    @FXML
    void supprimer (ActionEvent event) {

    }

    @FXML
    void modifier(ActionEvent event) {

    }




}

