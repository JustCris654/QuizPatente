<%@ page import="java.util.ArrayList" %>
<%@ page import="com.justcris.QuizPatente.Quiz" %>
<%@ page import="com.justcris.QuizPatente.beans.UserData" %>
<%@ page import="com.mysql.cj.conf.ConnectionUrlParser" %>
<%@ page import="com.justcris.QuizPatente.Pair" %>
<%@ page import="com.justcris.QuizPatente.AccessDatabase" %>

<%--
  Created by IntelliJ IDEA.
  User: justcris
  Date: 4/26/21
  Time: 4:03 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test</title>
</head>
<body>
<jsp:useBean id="UserData" class="com.justcris.QuizPatente.beans.UserData" scope="session"/>
<%
    Quiz quiz = (Quiz) session.getAttribute("quiz");
    ArrayList<Integer> quizNums = (ArrayList<Integer>) session.getAttribute("quizNums");
    int num = (int) session.getAttribute("num");
    AccessDatabase accessDatabase = (AccessDatabase) session.getAttribute("db");

    out.println("<form action=\"Quiz.jsp\" method=\"post\">\n");
    int numAnswer = 0;
    for (int i :
            quizNums) {
        out.println("    <p>");
        out.println(quiz.GetQuestionQuiz(i).get_question() + "</p>");
        out.println("<input type=\"radio\" id=\"true\" name=\"answer" + (numAnswer) + "\" value=\"true\">\n" +
                "    <label for=\"true\">V</label><br>\n" +
                "    <input type=\"radio\" id=\"false\" name=\"answer" + (numAnswer) + "\" value=\"false\">\n" +
                "    <label for=\"false\">F</label><br>\n");
        numAnswer++;
    }
    out.println("<input type=\"submit\" value=\"Avanti\" id=\"submit\" name=\"submit\">");
    out.println("</form>");


    if (request.getParameter("submit") != null) {
        ArrayList<Boolean> answers = new ArrayList<Boolean>();
        ArrayList<Pair<Integer, Boolean>> user_answers = new ArrayList<>();
        numAnswer = 0;
        double score = 0.0;
        for (int i :
                quizNums) {
            boolean answ = request.getParameter("answer" + numAnswer).equals("true");
            if (answ == quiz.GetAnswer(i)) score += 1.0;
            user_answers.add(new Pair<>(numAnswer, answ));
            numAnswer++;
        }
        System.out.println("Score: " + score);
        score = score / quizNums.size() * 100;
        UserData.setScore(score);
        accessDatabase.CompileQuiz(UserData.getUsername(), user_answers);
        accessDatabase.CloseDatabse();
        String redirect = "http://localhost:8080/QuizPatente_war_exploded/Recap.jsp";
        response.sendRedirect(redirect);
    }
%>
<%--<form action="Quiz.jsp" method="post">--%>
<%--    <p><%--%>
<%--        int numQuiz = quizNums.get(num);--%>
<%--        out.println(quiz.GetQuestionQuiz(numQuiz).get_question()); %></p>--%>
<%--    <input type="radio" id="true" name="answer" value="true">--%>
<%--    <label for="true">V</label><br>--%>
<%--    <input type="radio" id="false" name="answer" value="false">--%>
<%--    <label for="false">F</label><br>--%>
<%--    <input type="submit" value="Avanti" id="submit" name="submit">--%>
<%--</form>--%>
<%--<%--%>
<%--    if (request.getParameter("submit") != null) {--%>
<%--        num += 1;--%>
<%--        session.setAttribute("num", num);--%>
<%--    }--%>
<%--%>--%>
</body>
</html>
