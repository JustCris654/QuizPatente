<%@ page import="com.justcris.QuizPatente.beans.UserData" %>
<%--
  ~ Copyright (c) 2/5/2021. Scapin Cristian
  --%>

<%--
  Created by IntelliJ IDEA.
  User: justcris
  Date: 5/2/21
  Time: 2:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dumb recap</title>
    <jsp:useBean id="UserData" class="com.justcris.QuizPatente.beans.UserData" scope="session"/>
</head>

<body>
<p>Username : <%out.println(UserData.getUsername());%></p>
<p>Domande
    corrette: <%out.println(UserData.getScore() / 100 * (UserData.isRenewal() ? 5 : 10) + " su " + (UserData.isRenewal() ? 5 : 10));%></p>
<p><%out.println(UserData.getScore() >= 70 ? "Esame passato" : "Esame non passato");%></p>

</body>
</html>
