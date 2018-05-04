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
<!DOCTYPE html>
<html>
<head>
	<title>Звітування виховних частин УПЮ</title>
	<meta charset="UTF-8">
	<link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
	<link rel="stylesheet" type="text/css" href="css/normalize.css">
	<link rel="stylesheet" type="text/css" href="vendor/vendor.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/index.css">
	<script type="text/javascript" src="vendor/vendor.js"></script>
</head>
<body>
<div id="wrapper">
	<div class="wrapper">
		<!-- HEADER -->
		<div class="header">
			<div class="container">
				<div class="logo">
					<img src="images/logo.svg" alt="">
				</div>
				<h1>Електронні сервіси <br>
					<small>Крайова булава УПЮ</small>
				</h1>
			</div>
		</div>
		<!-- /HEADER -->

		<!-- MAIN SECTION -->
		<main class="main-content">
			<div class="container">
				<div class="index-page">
					<a href="index.html" class="href-item">
						<span class="item-icon"><img src="images/report_icon.png" /></span>
						<span class="item-title">Звітування виховних частин</span>
					</a>
					<a href="/advertaction" class="href-item">
						<span class="item-icon"><img src="images/event_icon.png" /></span>
						<span class="item-title">Зголошення заходів</span>
					</a>
					<span class="href-item disabled">
						<span class="item-icon"><img src="images/camp_icon.png" /></span>
						<span class="item-title">Зголошення таборів</span>
					</span>
				</div>
		<%@ include file = "jsp/footer.jsp" %>
</body>
</html>