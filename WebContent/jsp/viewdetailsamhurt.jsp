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
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Звітування виховних частин УПЮ</title>
		<link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
		<link rel="stylesheet" type="text/css" href="css/normalize.css">
		<link rel="stylesheet" type="text/css" href="vendor/vendor.css">
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<script type="text/javascript" src="vendor/vendor.js"></script>
		<script type="text/javascript" src="scripts/samohurtok.js"></script>
		<script type="text/javascript" src="scripts/report-error.js"></script>
	</head>
	<body>
	<%@ include file = "header.jsp" %>

	<div class="hurtok-info">
		<fieldset>
			<legend>Інформація по самостійному гуртку</legend>
			<c:forEach items="${listentered}" var="listentered">
				<input type="hidden" value="${listentered}" id="role">
			</c:forEach>
			<div class="info-item">
				<div class="title">Станиця:</div>
				<div class="value">
					<c:forEach items="${liststanytsya}" var="liststanytsya">
						<input id="idstan" type="hidden" value="${liststanytsya.id}">
						<c:out value="${liststanytsya.namestan}"></c:out>
					</c:forEach>
				</div>
			</div>
			<div class="info-item">
				<div class="title">Гурток:</div>
				<div class="value">
					<c:forEach items="${listsamhurtok}" var="listsamhurtok">
						<input id="hurtokid" type="hidden" value="${listsamhurtok.id}">
						<c:out value="${listsamhurtok.nameSamHurtok}"></c:out>
					</c:forEach>
				</div>
			</div>
			<div class="info-item">
				<div class="title">Впорядник:</div>
				<div class="value">
					<span id="vporyadnyk">
					<c:forEach items="${vporyadnyk}" var="vporyadnyk">
						<input type="hidden" value="${vporyadnyk.idvporyad}" id="idvporyadnyk">
						<input type="hidden" value="${vporyadnyk.idperson}" id="idvpor_person">
						<c:out value="${vporyadnyk.stupin} ${vporyadnyk.lastname} ${vporyadnyk.firstname}"></c:out>
						<b> від </b> <span class="datebegvp"><fmt:formatDate type="date" pattern="dd.MM.yyyy" value="${vporyadnyk.fromdatevp}"/><c:out value="${fromdatevp}"></c:out></span>
					</c:forEach>
					</span>
				</div>
			</div>
		</fieldset>
		<fieldset>
			<legend>Склад гуртка</legend>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>№</th>
						<th>діловедення</th>
						<th>ступінь</th>
						<th>Прізвище</th>
						<th>Ім'я</th>
						<th>Дата народження</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listperson}" var="listperson">
						<tr class="tablerow">
							<td></td>
							<td>
								<c:forEach items="${personsdilovodhurt}" var="personsdilovodhurt">
									<c:choose>
										<c:when test="${personsdilovodhurt.persons.id == listperson.idperson}">
											<c:forEach items="${listdilovodhurtok}" var="listdilovodhurtok">
												<c:choose>
													<c:when test="${personsdilovodhurt.dilovodhurtok.id == listdilovodhurtok.id}">
														<c:out value="${listdilovodhurtok.nameDilovodHurt}"/>
														<br>
													</c:when>
												</c:choose>
											</c:forEach>
										</c:when>
									</c:choose>
								</c:forEach>
							</td>
							<td><c:out value="${listperson.namestupin}"></c:out></td>
							<td><c:out value="${listperson.lastname}"></c:out></td>
							<td><c:out value="${listperson.firstname}"></c:out></td>
							<td>
								<fmt:formatDate type="date" pattern="dd.MM.yyyy" value="${listperson.birthday}"/>
								<c:out value="${birthday}"></c:out>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</fieldset>
	</div>
	<%@ include file = "footer.jsp" %>
	</body>
</html>