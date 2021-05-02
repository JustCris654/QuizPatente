<%--
  ~ Copyright (c) 26/4/2021. Scapin Cristian
  --%>

<%--
  Created by IntelliJ IDEA.
  User: justcris
  Date: 4/26/21
  Time: 4:12 PM
--%>
<%@page import="com.justcris.QuizPatente.Quiz" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.justcris.QuizPatente.AccessDatabase" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login esame patente</title>
</head>
<jsp:useBean id="UserData" class="com.justcris.QuizPatente.beans.UserData" scope="session"/>
<body>
<form action="Login.jsp" method="post">
    <label for="username">Inserisci username:</label>
    <input type="text" name="username" id="username"> <br>

    <input type="radio" id="new_licence" name="renewal" value="new_licence">
    <label for="new_licence">Patente nuova</label><br>
    <input type="radio" id="renewal" name="renewal" value="renewal">
    <label for="renewal">Rinnovo</label><br>

    <input type="submit" id="submit" name="submit" value="Invia">
</form>
<%
    if(request.getParameter("submit") != null){
        String username = request.getParameter("username");
        boolean renewal = request.getParameter("renewal").equals("renewal");
        UserData.setUsername(username);
        UserData.setRenewal(renewal);

        Quiz quiz = new Quiz();
        quiz.PopulateQuiz();
        ArrayList<Integer> quizNums = quiz.GenerateQuiz(renewal);
        AccessDatabase accessDatabase = new AccessDatabase();
        int questionNum = 1;
        session.setAttribute("quizNums", quizNums);
        session.setAttribute("quiz", quiz);
        session.setAttribute("num", questionNum);
        session.setAttribute("db", accessDatabase);

        accessDatabase.AddUser(username);
        String redirect = "http://localhost:8080/QuizPatente_war_exploded/Quiz.jsp";
        response.sendRedirect(redirect);
    }
%>

</body>
</html>
