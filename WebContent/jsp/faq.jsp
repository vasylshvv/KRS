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
    <script type="text/javascript" src="scripts/complains.js"></script>
    <script type="text/javascript" src="scripts/report-error.js"></script>
    <script type="text/javascript">
    $(document).ready(function() {
    	$('.panel-body').each(function(){
	    	var $this = $(this);
	    	var t = $this.text();
	    	$this.html(t.replace('&lt','<').replace('&gt', '>'));
	    });
	});  
    </script>
  </head>
  <body>
    <%@ include file = "header.jsp" %>

    <div class="table-complain">
      <div class="table-complain-header">
        <span></span>
        <span>#</span>
        <span>Питання</span>
      </div>
      <div class="table-complain-body panel-group" id="accordion" role="tablist" aria-multiselectable="true">
        <c:forEach items="${listfaq}" var="listfaq">
          <div class="panel panel-default">
            <div class="panel-heading" role="tab" id="heading<c:out value="${listfaq.id}"/>" data-toggle="collapse" aria-expanded="false" data-parent="#accordion" href="#collapse<c:out value="${listfaq.id}"/>" aria-controls="collapse<c:out value="${listfaq.id}"/>">
              <span class="glyphicon glyphicon-play" aria-hidden="true"></span>
              <span><c:out value="${listfaq.id}"/></span>
              <span><c:out value="${listfaq.question}"/></span>  <%-- вставити питання --%>
            </div>
            <div id="collapse<c:out value="${listfaq.id}"/>" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading<c:out value="${listfaq.id}"/>">
              <div class="panel-body">
               <c:out value="${listfaq.answer}"/> <%-- вставити відповідь --%>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
    </div>
    <%@ include file = "footer.jsp" %>
  </body>
</html>