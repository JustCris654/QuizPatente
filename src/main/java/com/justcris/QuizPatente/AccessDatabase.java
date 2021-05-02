/*
 * Copyright (c) 24/4/2021. Scapin Cristian
 */

package com.justcris.QuizPatente;

import javax.persistence.criteria.CriteriaBuilder;
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
            _conn = DriverManager.getConnection(DB_URL, USER, PASS);


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

    // given the username return the associated user id
    public int GetIdUtente(String username) {
        try {
            String sql = "SELECT Id FROM patente.utenti WHERE username='" + username + "'";
            Statement statement = _conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                return resultSet.getInt("Id");
            }
            return -1;
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    public boolean CompileQuiz(String username, ArrayList<Pair<Integer, Boolean>> answers){
        int id = GetIdUtente(username);
        if(id == -1) return false;              // return false if the username doesnt exists in the 'utenti' table

        try {
            StringBuilder sql = new StringBuilder("INSERT INTO patente.compilazione (id_username, id_domanda, risp_utente)" +
                    "VALUES ");
            for (Pair<Integer, Boolean> num_answer:
                    answers) {
                sql.append("(").append(id).append(", ").append(num_answer.getL()).append(", ").append(num_answer.getR()?"'V'":"'F'").append("),");
            }
            sql.deleteCharAt( sql.length() - 1 );
            Statement statement = _conn.createStatement();
            statement.executeUpdate(sql.toString());
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean AddUser(String username){
        try {
            String sql = "INSERT INTO patente.utenti (username) " +
                    "VALUES ('" + username+"')";
            Statement statement = _conn.createStatement();
            statement.executeUpdate(sql);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}



