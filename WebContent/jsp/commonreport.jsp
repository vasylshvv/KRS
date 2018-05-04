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
	<script type="text/javascript" src="vendor/vendor.js"></script>
	<script type="text/javascript" src="scripts/validation.js"></script>
	<script type="text/javascript" src="scripts/viewdetailkurin.js"></script>
	<script type="text/javascript" src="scripts/report-error.js"></script>
	<script type="text/javascript" src="scripts/commonreports.js"></script>
</head>
<body>
	<%@ include file = "header.jsp" %>
	<div id="formationreport">
		<form action="commonreport.html" method="post">
			<div id="datereport">
				<legend>Загальний звіт по куренях</legend>
				<div class="form-group">
					<label>Виберіть дату звітування</label>
					<div class="input-group date">
						<input type="text" class="datepicker form-control" name="ondatereport" width="100" id="ondatereport">
						<span class="input-group-addon">
							<span class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</div>
				<button type="submit" name="submitreport" class="btn btn-black" id="reportok">
					<span class="glyphicon glyphicon-list-alt"></span> Сформувати звіт
				</button>
				<!-- <input type="checkbox" name="lvivokruha"> Для львівської округи-->
				<!-- <input type="submit" name="submitreport" value="Сформувати звіт" id="reportok" class="btn btn-success"> -->
			</div>
			<div class="notcorrect alert alert-danger"><p></p></div>
		</form>
	</div>
	<div id="report">
		<form action="viewdetailkurinupu.html" method="post" id="detailkurin">
			<input type="hidden" name="detail" />
		</form>
		<form action="viewdetailsamhurt.html" method="post" id="detailsamhurt">
			<input type="hidden" name="detail" />
		</form>

		<c:forEach items="${listondate}" var="listondate">
			<legend class="report-title">Звіт по юнацтву станом на: <b><c:out value="${listondate}"/> р.</b>
				<button type="button" class="btn btn-black btn-filter">
					<span class="glyphicon glyphicon-filter" aria-hidden="true"></span> Фільтри
				</button>
			</legend>
			<div class="report-filter">
				<div class="row">
					<div class="col-xs-4">
						<div class="form-group">
							<label>Округа</label>
							<select id="filter-okruha" class="form-control">
								<option value="0">Виберіть округу</option>
								<c:forEach items="${listokruha}" var="listokruha">
									<option value="${listokruha.id}">
										<c:out value="${listokruha.nameokruha}"></c:out>
									</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="col-xs-4">
						<div class="form-group">
							<label>Станиця</label>
							<select id="filter-stanytsya" class="form-control">
								<option value="0">Виберіть станицю</option>
								<c:forEach items="${liststanytsya}" var="liststanytsya">
									<option value="${liststanytsya.id}">
										<c:out value="${liststanytsya.namestan}"></c:out>
									</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="col-xs-4">
						<div class="form-group">
							<label>Статус звіту</label>
							<select id="filter-status" class="form-control">
								<option value="-1">Виберіть</option>
								<option value="0">Не прозвітовано</option>
								<option value="1">Очікується на підтвердження</option>
								<option value="3">Прозвітовано</option>
							</select>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>

		<c:forEach items="${listokruha}" var="listokruha">
			<div class="okruha" data-okruha="${listokruha.id}">
			<div class="name-okruha"><h4><c:out value="${listokruha.nameokruha}"></c:out></h4></div>
				<c:forEach items="${liststanytsya}" var="liststanytsya">
					<c:choose>
						<c:when test="${liststanytsya.okruha.id == listokruha.id}">

					<div class="panel panel-default stanytsya" data-stanytsya="${liststanytsya.id}">
						<div class="panel-heading">Станиця:
							<b><c:out value="${liststanytsya.namestan}"></c:out></b>
						</div>
						<div class="panel-body">
							<table class="table kurin">
								<thead id="ttt">
									<tr class="together">
										<th style="width: 27%;">Курінь</th>
										<th style="width: 5%;">Прихильників</th>
										<th style="width: 5%;">Учасників</th>
										<th style="width: 5%;">Розвідувачів</th>
										<th style="width: 5%;">Скобів/Вірлиць</th>
										<th style="width: 5%;">Гетьманських скобів/вірлиць</th>
										<th style="width: 5%;">Всього</th>
										<th style="width: 10%;">Детальніше</th>
										<th style="width: 15%;">Статус</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${listreportcommon}" var="listreportcommon">
									<c:choose>
										<c:when test="${listreportcommon.idstan == liststanytsya.id}">
										<tr class="datakurin" data-status="${listreportcommon.approved}">
											<td>
												<c:if test="${listreportcommon.numberkurin == 0}">підг.к.ім.<c:out value="${listreportcommon.namekurin}"/></c:if>
												<c:if test="${listreportcommon.numberkurin > 0}">
													к.ч.<span class="knum"><c:out value="${listreportcommon.numberkurin}"/></span>
													ім.<c:out value="${listreportcommon.namekurin}"/>
												</c:if>
											</td>
											<td class="prykh" id="prk"><c:out value="${listreportcommon.prykhylnyk}"></c:out></td>
											<td class="uch"><c:out value="${listreportcommon.uchansnyk}"></c:out></td>
											<td class="rozv"><c:out value="${listreportcommon.rozviduvach}"></c:out></td>
											<td class="skbvirl"><c:out value="${listreportcommon.skobvirlytsya}"></c:out></td>
											<td class="htskbvirl"><c:out value="${listreportcommon.hetmanskobvirlytsa}"></c:out></td>
											<td class="count"><c:out value="${listreportcommon.allcount}"></c:out></td>
											<td class="detailkurin"><button value="${listreportcommon.idkurin}" class="clickdetailkurin btn btn-xs btn-cancel" name="detail"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> <span class="hidden-xs">Детальніше</span></button></td>
											<td style="text-align: left;">
												<div class="status">
													<c:if test="${listreportcommon.approved == 0}"><span class="mark"></span> Не прозвітовано</c:if>
													<c:if test="${listreportcommon.approved == 1}"><span class="mark warning"></span> Очікується підтвердження</c:if>
													<c:if test="${listreportcommon.approved == 2}"><span class="mark danger"></span> Відхилено</c:if>
													<c:if test="${listreportcommon.approved == 3}"><span class="mark success"></span> Прозвітовано</c:if>
												</div>
											</td>
										</tr>
										</c:when>
									</c:choose>
									</c:forEach>

								</tbody>

								<!-- TEST -->

								<thead>
									<tr class="together">
										<th style="width: 27%;">Самостійний гурток</th>
										<th style="width: 5%;">Прихильників</th>
										<th style="width: 5%;">Учасників</th>
										<th style="width: 5%;">Розвідувачів</th>
										<th style="width: 5%;">Скобів/Вірлиць</th>
										<th style="width: 5%;">Гетьманських скобів/вірлиць</th>
										<th style="width: 5%;">Всього</th>
										<th style="width: 10%;">Детальніше</th>
										<th style="width: 15%;">Cтатус</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${listreportsmhurtok}" var="listreportsmhurtok">
									<c:choose>
										<c:when test="${listreportsmhurtok.idstan == liststanytsya.id}">
										<tr class="datakurin" data-status="${listreportsmhurtok.approved}">
											<td><c:out value="${listreportsmhurtok.namesmhurtok}"/></td>
											<td class="prykh" id="prh"><c:out value="${listreportsmhurtok.prykhylnyk}"></c:out></td>
											<td class="uch"><c:out value="${listreportsmhurtok.uchansnyk}"></c:out></td>
											<td class="rozv"><c:out value="${listreportsmhurtok.rozviduvach}"></c:out></td>
											<td class="skbvirl"><c:out value="${listreportsmhurtok.skobvirlytsya}"></c:out></td>
											<td class="htskbvirl"><c:out value="${listreportsmhurtok.hetmanskobvirlytsa}"></c:out></td>
											<td class="count"><c:out value="${listreportsmhurtok.allcount}"></c:out></td>
											<td class="detailsamhurt"><button value="${listreportsmhurtok.idsmhurtok}" class="clickdetailsamhurt btn btn-xs btn-cancel" name="detail"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> <span class="hidden-xs">Детальніше</span></button></td>
											<td style="text-align: left;">
												<div class="status">
													<c:if test="${listreportsmhurtok.approved == 0}"><span class="mark"></span> Не прозвітовано</c:if>
													<c:if test="${listreportsmhurtok.approved == 1}"><span class="mark warning"></span> Очікується підтвердження</c:if>
													<c:if test="${listreportsmhurtok.approved == 2}"><span class="mark danger"></span> Відхилено</c:if>
													<c:if test="${listreportsmhurtok.approved == 3}"><span class="mark success"></span> Прозвітовано</c:if>
												</div>
											</td>
										</tr>
										</c:when>
									</c:choose>
									</c:forEach>
								</tbody>
								<thead>
									<tr class="together">
										<td><b>Разом</b></td>
										<td class="allprykh"></td>
										<td class="alluch"></td>
										<td class="allrozv"></td>
										<td class="allskbvirl"></td>
										<td class="allhtskbvirl"></td>
										<td class="allcount"></td>
										<td></td>
									</tr>
								</thead>
								<!-- END TEST -->
							</table>
						</div>
					</div>
					</c:when>
					</c:choose>
				</c:forEach>
			</div>
		</c:forEach>
	</div>
	<div id="allReport">
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading"><h3 class="panel-title">Юнацтва загалом</h3></div>
				<table class="table">
					<thead>
						<tr>
							<th>Всього юнацтва</th>
							<th>Прихильників</th>
							<th>Учасників</th>
							<th>Розвідувачів</th>
							<th>Скобів/Вірлиць</th>
							<th>Гетьманських скобів/вірлиць</th>
							<th>Всього</th>
						</tr>
					</thead>
					<tbody>
						<tr class="alltogether">
							<td><b>Разом</b></td>
							<td class="allsumprykh">0</td>
							<td class="allsumuch">0</td>
							<td class="allsumrozv">0</td>
							<td class="allsumskbvirl">0</td>
							<td class="allsumhtskbvirl">0</td>
							<td class="allsumcount">0</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<%@ include file = "footer.jsp" %>
</body>
</html>