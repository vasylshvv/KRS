<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setCharacterEncoding("UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav id="menu">
  <ul>
    <li><a href="/reports">Головна</a></li>
    <li>
      <a href="#subpanel1">Звітування виховних частин</a>
      <ul id="subpanel1">
        <li><a href="index.html">Прозвітувати</a></li>
        <li><a href="commonreport.html">Переглянути звіт</a></li>
      </ul>
    </li>
    <li><a href="#subpanel2">Зголошення заходів</a>
      <ul id="subpanel2">
        <li><a href="/advertaction">Зголосити захід</a></li>
        <li><a href="/advertaction/listevents.html">Переглянути список заходів</a></li>
      </ul>
    </li>
  </ul>
</nav>

<div id="wrapper">
  <span id="hamburger" class="mm-sticky">
    <a href="#wrapper" id="menu-icon" class="hamburger hamburger--collapse">
      <span class="hamburger-box">
        <span class="hamburger-inner"></span>
      </span>
    </a>
  </span>
  <div class="wrapper">

    <!-- HEADER -->
    <div class="header">
      <div class="container">
        <div class="logo">
          <img src="images/logo.svg" alt="">
        </div>
        <h1>Звітування виховних частин УПЮ <br>
          <small>Крайова булава УПЮ</small>
        </h1>
      </div>
    </div>
    <!-- /HEADER -->

    <!-- MAIN SECTION -->
    <main class="main-content">
      <div class="report-error showDialog" data-selector="#dialog_report_error">
          <span class="glyphicon glyphicon-alert" aria-hidden="true"></span>
          <span class="text"> Повідомити про помилку</span>
      </div>
      <div class="container">

          <!-- NAVIGATION -->
          <div class="menu">
              <ul class="nav nav-pills nav-justified">
                  <li><a href="index.html">Звітування</a></li>
                  <li data-tmpl="commonreport"><a href="commonreport.html">Сформувати звіт</a></li>
                  <li data-tmpl="faq"><a href="faq.html">FAQ</a></li>
              </ul>
          </div>
          <script>
            var pathTmpl = '${pageContext.request.requestURL}',
              pathTmplSplit = pathTmpl.split('/'),
              nameTmpl = pathTmplSplit[pathTmplSplit.length - 1].split('.')[0];
            if (pathTmpl.indexOf('index') !== -1) {
              $('.menu li:first-child').addClass('active');
            } else {
              $('.menu li[data-tmpl=' + nameTmpl + ']').addClass('active');
            }
          </script>
          <!-- /NAVIGATION -->