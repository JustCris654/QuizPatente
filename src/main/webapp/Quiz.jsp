<%@ page import="java.util.ArrayList" %>
<%@ page import="com.justcris.QuizPatente.Quiz" %>

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
<%
Quiz quiz = (Quiz) session.getAttribute("quiz");
ArrayList<Integer> quizNums = (ArrayList<Integer>) session.getAttribute("quizNums");
int num = (int) session.getAttribute("num");
%>
<form action="Quiz.jsp" method="post">
    <p><%
        int numQuiz = quizNums.get(num);
        out.println(quiz.GetQuestionQuiz(numQuiz).get_question()); %></p>
    <input type="radio" id="true" name="answer" value="true">
    <label for="true">V</label><br>
    <input type="radio" id="false" name="answer" value="false">
    <label for="false">F</label><br>
    <input type="submit" value="Avanti" id="submit" name="submit">
</form>
<%
    if (request.getParameter("submit") != null){
        num += 1;
        session.setAttribute("num", num);
    }
%>
</body>
</html>
