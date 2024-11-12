package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class NoteController {
    Connection cn;
    PreparedStatement st;

    @FXML
    private TableColumn<notes, Float> CC;

    @FXML
    private TableColumn<notes, Float> EF;

    @FXML
    private TableColumn<notes, Float> DELI;

    @FXML
    private TableColumn<notes, Float> NOTEPV;

    @FXML
    private TableColumn<?, String> NomEtPrenom;

    @FXML
    private TableColumn<?, Button> D;

    @FXML
    private TableColumn<notes, Float> RATT;

    @FXML
    private TableColumn<?, Button> M;

    @FXML
    private TableColumn<notes, Float> N20212022;

    @FXML
    private TableColumn<notes, String> MODULE;

    @FXML
    private TableColumn<notes, Float> NOTE;

    @FXML
    private TableColumn<notes, String> CNE;

    @FXML
    private TableView<notes> TableView;

    public void importNotes(String filepath) throws FileNotFoundException {
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=PFEAPP;encrypt=true;trustServerCertificate=true";
        String user = "soufian";
        String password = "1234";
        String quere = "INSERT INTO notes VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn = DriverManager.getConnection(url, user, password);
            st = cn.prepareStatement(quere);
        }
        catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
        }
        BufferedReader breader = new BufferedReader(new FileReader(filepath));
        String line = null;
        try {
            breader.readLine();
            while((line = breader.readLine()) != null) {
                String Data[] = line.split(",");
                String CNE = Data[1];
                float ef = Float.parseFloat(Data[3]);
                float cc = Float.parseFloat(Data[4]);
                float noteNormal = Float.parseFloat(Data[5]);
                float rattrapage = Float.parseFloat(Data[8]);
                float notePV = Float.parseFloat(Data[6]);
                float Deli = Float.parseFloat(Data[9]);
                float Note_2021_2022 = Float.parseFloat(Data[7]);
                int annee = Integer.parseInt(Data[10]);
                String insertModule = "test";
                st.setString(1, CNE);
                st.setFloat(2, ef);
                st.setFloat(3, cc);
                st.setFloat(4, noteNormal);
                st.setFloat(5, rattrapage);
                st.setFloat(6, notePV);
                st.setFloat(7, Deli);
                st.setFloat(8, Note_2021_2022);
                st.setInt(10, annee);
                st.setString(9, "test2");
                st.executeUpdate();
            }
            st.close();
            breader.close();
        } catch (IOException | SQLException | NumberFormatException e) {
            throw new RuntimeException(e);
        }

    }

    class notes {
        public String CNE;
        public float cc;
        public float ef;
        public float noteNormal;
        public float rattrapage;
        public float notePV;
        public float Deli;
        public float Note_2021_2022;
        public String module;

        public notes(String CNE, float cc, float ef, float rattrapage, float deli, float Note_2021_2022, String module) {
            this.CNE = CNE;
            this.cc = cc;
            this.ef = ef;
            this.noteNormal = (float) ((cc * 1.40) + (ef * 1.60));
            this.rattrapage = rattrapage;
            if(rattrapage >= this.noteNormal)
                this.notePV = rattrapage ;
            else
                this.notePV = this.noteNormal;
            this.Deli = deli;
            this.Note_2021_2022 = Note_2021_2022;
            this.module = module;
        }
    }

    /*public static void main(String[] args) throws FileNotFoundException {
        NoteController note = new NoteController();
        note.importNotes("C:/Users/asus/Downloads/liste.classe.csv");
    }*/
}
