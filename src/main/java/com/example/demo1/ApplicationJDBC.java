package com.example.demo1;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class ApplicationJDBC {
    static Connection cn;
    static PreparedStatement st;
    static PreparedStatement statement;
    static ResultSet rs;

    //TODO remove the query
    public void connection(){
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=PFEAPP;encrypt=true;trustServerCertificate=true";
        String user = "soufian";
        String password = "1234";
        String query = "SELECT * FROM USERS WHERE email = ? AND password = ?;";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn = DriverManager.getConnection(url, user, password);
            st = cn.prepareStatement(query);
        }
        catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
        }

    }
    public void importStudentCSV(String filePath) throws FileNotFoundException {
        String sql = "INSERT INTO student (NUM,CNE,NomPrenom) VALUES(?,?,?)";
        try {
            this.connection();
            statement = cn.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println("error here in st");;
        }
        //String filePath = "C:/Users/asus/Downloads/exemple PV.csv";
        BufferedReader breader = new BufferedReader(new FileReader(filePath));
        String line = null;

        try {
            breader.readLine();
            while ((line = breader.readLine()) != null) {
                String[] Data = line.split(",");
                int NUM = Integer.parseInt(Data[0]);
                String CNE = Data[1];
                String NomPrenom = Data[2];
                statement.setInt(1, NUM);
                statement.setString(2, CNE);
                statement.setString(3, NomPrenom);
                statement.executeUpdate();

            }
            breader.close();
            statement.close();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);

        }

    }


}

