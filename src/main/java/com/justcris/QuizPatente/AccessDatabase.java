/*
 * Copyright (c) 24/4/2021. Scapin Cristian
 */

package com.justcris.QuizPatente;

import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;
import java.sql.*;

public class AccessDatabase {

    final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    final String DB_URL = "jdbc:mysql://127.0.0.1/patente";

    //  Database credentials
    final String USER = "root";
    final String PASS = "";

    private Connection _conn;
    private Statement statement;

    public AccessDatabase() {
        _conn = null;
        statement = null;
        try {
            //Load dbms drivers
            Class.forName(JDBC_DRIVER);

            //Open database connection
            System.out.println("Connecting to database...");
            _conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");


        } catch (Exception e) {
            //Handle errors for JDBC
            e.printStackTrace();
        }
    }

    //if opened close the connection to the dbms
    //opened and close -> true
    //already close or error -> false
    public boolean CloseDatabse() {
        if (_conn != null) {
            try {
                _conn.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public ArrayList<QuestionQuiz> GetQuiz() {
        ArrayList<QuestionQuiz> quizs = new ArrayList<>();

        //if the connection doesn't exist return null immediately
        if (_conn == null) return null;

        try {
            statement = _conn.createStatement();
            String sql = "SELECT  risp, dom FROM patente.domande";      //Sql query for retrieving questions and
            //answers from the database
            ResultSet resultSet = statement.executeQuery(sql);          //Get data from database
            while (resultSet.next()) {
                //retrieve data from database to QuestionQuiz class
                QuestionQuiz questionQuiz = new QuestionQuiz(
                        resultSet.getString("dom"),
                        resultSet.getString("risp").equals("V")
                );
                //add the retrieved data to the arraylist
                quizs.add(questionQuiz);
            }

            return quizs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean AddUser(String username){
        try {
            String sql = "INSERT INTO patente.utenti (username)\n" +
                    "VALUES " + username;
            Statement statement = _conn.createStatement();
            statement.executeQuery(sql);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}



