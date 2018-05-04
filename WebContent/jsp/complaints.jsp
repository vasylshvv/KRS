<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setCharacterEncoding("UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <title>Звітування виховних частин УПЮ</title>
    <meta charset="UTF-8">
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="css/normalize.css">
    <link rel="stylesheet" type="text/css" href="vendor/vendor.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script type="text/javascript" src="vendor/vendor.js"></script>
    <script type="text/javascript" src="scripts/report-error.js"></script>
</head>
  <body>
      <%@ include file = "header.jsp" %>
      <table class="table">
          <thead>
          <tr>
              <th>#</th>
              <th>Ім'я та прізвище</th>
              <th>email</th>
              <th>Текст повідомлення</th>
              <th>Прикріплений файл</th>
              <th>Дата</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${listproblem}" var="listproblem">
          <tr>

              <td><c:out value="${listproblem.id}"/></td>
              <td><c:out value="${listproblem.sfullname}"/></td>
              <td><c:out value="${listproblem.email}"/></td>
              <td><c:out value="${listproblem.textrequest}"/></td>
              <td>...</td>
              <td><c:out value="${listproblem.sysdate}"/></td>

          </tr>
          </c:forEach>
          </tbody>
      </table>
      <%@ include file = "footer.jsp" %>
  </body>
</html>