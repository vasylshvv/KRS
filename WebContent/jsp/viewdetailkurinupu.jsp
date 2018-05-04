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
		<!-- // <script src="jquery-ui-1.11.4.custom/jquery-ui.js"></script> -->
		<script type="text/javascript" src="scripts/hurtok.js"></script>
		<script type="text/javascript" src="scripts/persons.js"></script>
		<script type="text/javascript" src="scripts/zvyazkovyy.js"></script>
		<script type="text/javascript" src="scripts/vporyadnyk.js"></script>
		<script type="text/javascript" src="scripts/stanytsya.js"></script>
		<script type="text/javascript" src="scripts/kurin.js"></script>
		<script type="text/javascript" src="scripts/stupin.js"></script>
		<script type="text/javascript" src="scripts/viewdetailkurin.js"></script>
		<script type="text/javascript" src="scripts/dilovedennya.js"></script>
		<script type="text/javascript" src="scripts/report-error.js"></script>

		<script type="text/javascript">
			$(document).ready(function(){

				$("#selectulad :contains('УПЮ')").attr("selected", "selected");
				$(".add_unak").on('click',function(){
					var idhurtok = $(this).val();
					$("#idselhurt").val(idhurtok);
				});
				$(".add_vporyad").on('click',function(){
					var idhurtok = $(this).val();
					$("#idselhurtvporyad").val(idhurtok);
				});

				$("#phonenumber").mask("38(999)999-99-99");
				$("#phonenumberp").mask("38(999)999-99-99");

				  $('.table').each(function(){
					$(this).find('.tablerow').each(function (i) {
					  $("td:first", this).html(i+1);
					});
				  });

				/*
				$('span[class="vpfullname"]').each(function(index,item){
					if(parseInt($(item).data('index'))>2){
						$(item).html('Testimonial '+(index+1)+' by each loop');
					}
				});

				for(var i = 0; i < $(".vpfullname").length; i++){

					var zv = $(".vpfullname")[i].text().trim();
					alert(zv);
					//$(".vpfullname").parent('.cont').childrer('.addvyhovnyk').val(zv == "" ? 'Add' : 'Change')
					if(zv == ""){
						$(".add_vporyad")[i].val('Додати');
					} else {
						$(".add_vporyad")[i].val('Змінити');
					}
				}

			/*

				$(".vpfullname").each(function(){

					var vp = $(this).text().trim();
					//alert(vp);
					if(vp == ""){
					//	alert(1);
						//$(".add_vporyad", this).text('Додати');
					} else {
					//	alert(0);

						//$(".add_vporyad", this).text('Змінити');
					}

				});
				*/


			});
		</script>
	</head>
	<body>
	<%@ include file = "header.jsp" %>

	<div class="kurin-information">
		<fieldset>
			<legend>Інформація по куреню</legend>
			<div id="regstanytsya">
				<c:forEach items="${liststanystya}" var="liststanystya">
					<input type="hidden" value="${liststanystya.id}" id="idstan">
					<span class="title">Станиця:</span>
					<span class="value"><c:out value="${liststanystya.namestan}"></c:out></span>
				</c:forEach>
			</div>
			<div id="regkurinupu">
				<c:forEach items="${listkurinupu}" var="listkurinupu">
					<input type="hidden" value="${listkurinupu.id}" id="kurinid">
					<span class="title">Курінь:</span>
					<span class="value">
						<c:if test="${listkurinupu.numberKurin == 0}">підг. ім. <c:out value="${listkurinupu.nameKurin}"/></c:if>
						<c:if test="${listkurinupu.numberKurin > 0}">
							<c:out value="${listkurinupu.numberKurin}"/>
							ім. <c:out value="${listkurinupu.nameKurin}"/>
						</c:if>
					</span>
				</c:forEach>
			</div>
			<div id="zvyazkovyy">
				<span class="title">Зв'язковий:</span>
				<span class="value">
					<span id="zvfullname">
						<c:forEach items="${zvyazkovyy}" var="zvyazkovyy">
							<input type="hidden" value="${zvyazkovyy.idzvyaz}" id="idzvyaz">
							<c:out value="${zvyazkovyy.stupin} ${zvyazkovyy.lastname} ${zvyazkovyy.firstname}"></c:out>
							<b>від</b> <span id="datebegzv"><fmt:formatDate type="date" pattern="dd.MM.yyyy" value="${zvyazkovyy.fromdatezv}"/> <c:out value="${fromdatezv}"></c:out></span>
						</c:forEach>
					</span>
					<button type="button" disabled class="btn btn-cancel btn-xs" id="detailzvyazkovyy">
						<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> Деталі зв'язкового
					</button>
				</span>
			</div>
		</fieldset>
		<fieldset>
			<legend>Провід куреня</legend>
			<div id="providkur">
				<table class="table table-striped">
					<thead>
					<tr>
						<th>№</th>
						<th>діловедення</th>
						<th>ступінь</th>
						<th>Прізвище</th>
						<th>Ім'я</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach  items="${listprovidkurin}" var="listprovidkurin">
						<tr class="tablerow">
							<td></td>
							<td><c:out value="${listprovidkurin.namedilovod}"></c:out></td>
							<td><c:out value="${listprovidkurin.namestupin}"></c:out></td>
							<td><c:out value="${listprovidkurin.lastname}"></c:out></td>
							<td><c:out value="${listprovidkurin.firstname}"></c:out></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</fieldset>
	</div>
	<div id="hurtok">
		<fieldset>
			<legend>Гуртки в курені</legend>
			<c:forEach items="${listhurtokupu}" var="listhurtokupu">
				<div class="gurtok-item panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title"><strong>Гурток: № <c:out value="${listhurtokupu.number}"></c:out> "<c:out value="${listhurtokupu.nameHurtok}"></c:out>" </strong></h3>
					</div>
					<div class="panel-body">
						<div class="members">
							<h5><b>Члени Гуртка</b></h5>
							<table class="table table-condensed table-striped">
								<thead>
								<tr>
									<th>№</th>
									<th>діловедення</th>
									<th>ступінь</th>
									<th>Прізвище</th>
									<th>Ім'я</th>
									<th align="center" class="hidden-xs">Дата народження</th>
									<th>Детальніше</th>
								</tr>
								</thead>
								<tbody>
									<c:forEach items="${listperson}" var="listperson">
										<c:choose>
											<c:when test="${listperson.idhurtok == listhurtokupu.id}">
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
													<td align="center" class="hidden-xs">
														<fmt:formatDate type="date" pattern="dd.MM.yyyy" value="${listperson.birthday}"/>
														<c:out value="${birthday}"></c:out>
													</td>
													<td><button value="${listperson.idperson}" disabled class="detailunak btn btn-xs btn-cancel"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> <span class="hidden-xs">Детальніше</span></button></td>
												</tr>
											</c:when>
										</c:choose>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="vp">
							<span class="title">Впорядник гуртка: </span>
							<span class="value">
								<c:forEach items="${vporyadnyk}" var="vporyadnyk">
									<c:choose>
										<c:when test="${vporyadnyk.idhurtok ==  listhurtokupu.id}">
											<span class="vpfullname">
												<c:out value="${vporyadnyk.stupin} ${vporyadnyk.lastname} ${vporyadnyk.firstname}"></c:out>
												<b> від </b>
												<span class="datebegvp">
												<fmt:formatDate type="date" pattern="dd.MM.yyyy" value="${vporyadnyk.fromdatevp}"/>
												<c:out value="${fromdatevp}"></c:out>
												</span>
											</span>
										</c:when>
									</c:choose>
								</c:forEach>
							</span>
						</div>
						<div class="btns">
							<span class="title"></span>
							<span class="value">
								<button disabled class="detailvporyad btn btn-black btn-sm" value="${listhurtokupu.id}">
									<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> Деталі впорядника
								</button>
							</span>
						</div>
					</div>
				</div>
			</c:forEach>
		</fieldset>
	</div>

	<%@ include file = "footer.jsp" %>
	<!-- Modal Zvyazkovyy -->
			<div id="dialog_vykhovnyk" class="modal fade" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="myModalLabel">Зв'язковий</h4>
						</div>
						<div class="modal-body">
							<div class="form-group" id="datetozv">
								<label>Дата закінчення попереднього зв'язкового</label>
								<div class="input-group date">
									<input type="text" class="datepicker form-control" name="zvdateto" size="7" id="zvdateto">
									<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
								</div>
							</div>
							<div class="form-group" id="datefromzv">
								<label>Дата затвердження зв'язковим</label>
								<div class="input-group date">
									<input type="text" class="datepicker form-control" name="zvdatefrom" id="zvdatefrom">
									<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
								</div>
							</div>
							<div class="form-group" id="seluladzv">
								<p class="help-block">Виберіть улад УПЮ, якщо зв'язковий молодший </small></p>
								<div class="radio">
									<label>
										<input type="radio" name="group" class="seluladradio" value="0" checked> УСП/УПС
									</label>
								</div>
								<div class="radio">
									<label>
										<input type="radio" name="group" class="seluladradio" value="1"> УПЮ
									</label>
								</div>
							</div>
							<div class="form-group select2block" id="stanytsya">
								<label>Виберіть станицю</label>
								<select class="liststan form-control select2select">
									<option value="0">Виберіть станицю</option>
									<c:forEach items="${liststanp}" var="liststanp">
										<option value="${liststanp.id}"><c:out value="${liststanp.namestan}"/></option>
									</c:forEach>
								</select>
							</div>
							<div class="kurinUPU form-group select2block">
								<label>Виберіть курінь УПЮ</label>
								<select class="selectkurin form-control select2select">
									<option>Виберіть курінь</option>
									<c:forEach items="${listkurinupustan}" var="listkurinupustan">
										<option value="${listkurinupustan.id}"><c:out value="к.ч.${listkurinupustan.numberKurin} ім.${listkurinupustan.nameKurin}"></c:out></option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group half">
								<label>Список пластунів</label>
								<select name="person" class="idperson form-control select2select" id="idperson">
									<option value="0">Виберіть з списку</option>
									<c:forEach items="${listpersonsuspups}" var="listpersonsuspups">
										<option value="${listpersonsuspups.idperson}">
											<c:out value="${listpersonsuspups.stupin} ${listpersonsuspups.lastname} ${listpersonsuspups.firstname}"></c:out>
										</option>
									</c:forEach>
								</select>
								<input type="button" class="btn btn-cancel btn-sm" value="перегляд" id="viewpers">
								<input type="button" id="addzvyazkovyymodal" class="btn btn-black btn-sm" value="додати">
							</div>
							<div class="alert alert-danger notcorrect"><p></p></div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-cancel" data-dismiss="modal">Відмінити</button>
							<button type="button" class="btn btn-black btn-save">Зберегти</button>
						</div>
					</div>
				</div>
			</div>

			<!-- Modal About Zvyazkovyy -->
			<div id="about_zvyaz" class="modal fade" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="myModalLabel">Інформація про зв'язкового</h4>
						</div>
						<div class="modal-body">
							<c:forEach items="${zvyazkovyy}" var="zvyazkovyy">
								<div id="infzv" class="form-group">
									<span class="title">Зв'язковий</span>
									<span class="value"><c:out value="${zvyazkovyy.stupin} ${zvyazkovyy.lastname} ${zvyazkovyy.firstname}"></c:out></span>
								</div>
								<div class="form-group">
									<c:forEach items="${listkurinupu}" var="listkurinupu">
										<input type="hidden" value="${listkurinupu.id}" id="kurinidzv">
										<span class="title">Курінь: </span>
										<span class="value"><c:out value="${listkurinupu.numberKurin}"/> ім. <c:out value="${listkurinupu.nameKurin}"/></span>
									</c:forEach>
								</div>
								<div class="form-group">
									<span class="title">від:</span>
									<div class="value">
										<div class="input-group date">
											<input type="text" class="datepicker form-control input-sm" name="datestartzv" size="7" id="datestartzv">
											<span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
										</div>
									</div>
								</div>
								<div class="form-group">
									<span class="title"></span>
									<span class="value">
								<button type="button" class="btn btn-cancel btn-sm" value="${zvyazkovyy.idperson}" id="detilzv" >
									<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>  Більше інформації
								</button>
							</span>
								</div>
							</c:forEach>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-cancel" data-dismiss="modal">Відмінити</button>
							<button type="button" class="btn btn-black btn-save">Зберегти</button>
						</div>
					</div>
				</div>
			</div>
			<!-- Modal Vporyadnyk -->
			<div id="dialog_vporyadnyk" class="modal fade" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="myModalLabel">Впорядник</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label>Гурток</label>
								<select id="idselhurtvporyad" name="selhurtvporyad" class="form-control">
									<option value="0">Виберіть гурток</option>
									<c:forEach items="${listhurtokupu}" var="listhurtokupu">
										<option value="${listhurtokupu.id}">
											<c:out value="${listhurtokupu.nameHurtok}"></c:out>
										</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label>Дата закінчення попереднього впорядника</label>
								<div class="input-group date">
									<input type="text" class="datepicker form-control" name="vpdateto" size="7" id="vpdateto">
									<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
								</div>
							</div>
							<div class="form-group">
								<label>Дата початку нового впорядника</label>
								<div class="input-group date">
									<input type="text" class="datepicker form-control" name="vpdatefrom" size="7" id="vpdatefrom">
									<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
								</div>
							</div>
							<div class="form-group" id="seluladzv">
								<p class="help-block">Виберіть улад УПЮ, якщо виховник молодший </small></p>
								<div class="radio">
									<label>
										<input type="radio" name="group" class="seluladradio" value="0" checked> УСП/УПС
									</label>
								</div>
								<div class="radio">
									<label>
										<input type="radio" name="group" class="seluladradio" value="1"> УПЮ
									</label>
								</div>
							</div>
							<div class="form-group select2block" id="stanytsya">
								<label>Виберіть станицю</label>
								<select class="liststan form-control select2select">
									<option value="0">Виберіть станицю</option>
									<c:forEach items="${liststanp}" var="liststanp">
										<option value="${liststanp.id}"><c:out value="${liststanp.namestan}"/></option>
									</c:forEach>
								</select>
							</div>
							<div class="kurinUPU form-group select2block">
								<label>Виберіть курінь УПЮ</label>
								<select class="selectkurin form-control select2select">
									<option>Виберіть курінь</option>
									<c:forEach items="${listkurinupustan}" var="listkurinupustan">
										<option value="${listkurinupustan.id}"><c:out value="к.ч.${listkurinupustan.numberKurin} ім.${listkurinupustan.nameKurin}"></c:out></option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group half">
								<label>Список пластунів</label>
								<select name="person" class="idperson form-control select2select">
									<option value="0">Виберіть з списку</option>
									<c:forEach items="${listpersonsuspups}" var="listpersonsuspups">
										<option value="${listpersonsuspups.idperson}">
											<c:out value="${listpersonsuspups.stupin} ${listpersonsuspups.lastname} ${listpersonsuspups.firstname}"></c:out>
										</option>
									</c:forEach>
								</select>
								<input type="button" class="btn btn-cancel btn-sm" value="перегляд" id="viewpersvp">
								<input type="button" value="додати" id="addpersvp" class="btn btn-black btn-sm showDialog" data-selector="#dialog_person">
							</div>
							<div class="alert alert-danger notcorrect"><p></p></div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-cancel" data-dismiss="modal">Відмінити</button>
							<button type="button" class="btn btn-black btn-save">Зберегти</button>
						</div>
					</div>
				</div>
			</div>
			<!-- Modal Vporyadnyk -->
			<div id="dialog_vporyadnyk" class="modal fade" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="myModalLabel">Впорядник</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label>Гурток</label>
								<select id="idselhurtvporyad" name="selhurtvporyad" class="form-control">
									<option value="0">Виберіть гурток</option>
									<c:forEach items="${listhurtokupu}" var="listhurtokupu">
										<option value="${listhurtokupu.id}">
											<c:out value="${listhurtokupu.nameHurtok}"></c:out>
										</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label>Дата закінчення попереднього впорядника</label>
								<div class="input-group date">
									<input type="text" class="datepicker form-control" name="vpdateto" size="7" id="vpdateto">
									<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
								</div>
							</div>
							<div class="form-group">
								<label>Дата початку нового впорядника</label>
								<div class="input-group date">
									<input type="text" class="datepicker form-control" name="vpdatefrom" size="7" id="vpdatefrom">
									<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
								</div>
							</div>
							<div class="form-group" id="seluladzv">
								<p class="help-block">Виберіть улад УПЮ, якщо виховник молодший </small></p>
								<div class="radio">
									<label>
										<input type="radio" name="group" class="seluladradio" value="0" checked> УСП/УПС
									</label>
								</div>
								<div class="radio">
									<label>
										<input type="radio" name="group" class="seluladradio" value="1"> УПЮ
									</label>
								</div>
							</div>
							<div class="form-group select2block" id="stanytsya">
								<label>Виберіть станицю</label>
								<select class="liststan form-control select2select">
									<option value="0">Виберіть станицю</option>
									<c:forEach items="${liststanp}" var="liststanp">
										<option value="${liststanp.id}"><c:out value="${liststanp.namestan}"/></option>
									</c:forEach>
								</select>
							</div>
							<div class="kurinUPU form-group select2block">
								<label>Виберіть курінь УПЮ</label>
								<select class="selectkurin form-control select2select">
									<option>Виберіть курінь</option>
									<c:forEach items="${listkurinupustan}" var="listkurinupustan">
										<option value="${listkurinupustan.id}"><c:out value="к.ч.${listkurinupustan.numberKurin} ім.${listkurinupustan.nameKurin}"></c:out></option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group half">
								<label>Список пластунів</label>
								<select name="person" class="idperson form-control select2select">
									<option value="0">Виберіть з списку</option>
									<c:forEach items="${listpersonsuspups}" var="listpersonsuspups">
										<option value="${listpersonsuspups.idperson}">
											<c:out value="${listpersonsuspups.stupin} ${listpersonsuspups.lastname} ${listpersonsuspups.firstname}"></c:out>
										</option>
									</c:forEach>
								</select>
								<input type="button" class="btn btn-cancel btn-sm" value="перегляд" id="viewpersvp">
								<input type="button" value="додати" id="addpersvp" class="btn btn-black btn-sm showDialog" data-selector="#dialog_person">
							</div>
							<div class="alert alert-danger notcorrect"><p></p></div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-cancel" data-dismiss="modal">Відмінити</button>
							<button type="button" class="btn btn-black btn-save">Зберегти</button>
						</div>
					</div>
				</div>
			</div>
			<!-- Modal Move Stupin -->
			<div id="dialog_move_stupin" class="modal fade" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="myModalLabel">Додати/Змінити ступінь</h4>
						</div>
						<div class="modal-body">
							<div class="verifidate"><p></p></div>
							<div class="form-group">
								<label>Дата з</label>
								<div class="input-group date">
									<input type="text" class="datepicker form-control" name="datebegstup" size="7" id="datebegstup">
									<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
								</div>
							</div>
							<div class="form-group">
								<label>Дата по</label>
								<div class="input-group date">
									<input type="text" class="datepicker form-control" name="dateendstup" size="7" id="dateendstup">
									<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
								</div>
							</div>
							<div class="form-group">
								<label>Улад</label>
								<select class="selectuladp form-control" id="unakulad">
									<option value="0">Виберіть улад</option>
									<c:forEach items="${listulad}" var="listulad">
										<option value="${listulad.id}"><c:out value="${listulad.nameulad}"></c:out></option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label>Ступінь</label>
								<select class="selstupinp form-control" id="unakstupin">
									<option value="0">Виберіть ступінь</option>
								</select>
							</div>
							<div class="alert alert-danger notcorrect"><p></p></div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-cancel" data-dismiss="modal">Відмінити</button>
							<button type="button" class="btn btn-black btn-save">Зберегти</button>
						</div>
					</div>
				</div>
			</div>

			<!-- Modal dilovod hurtok -->
			<div id="dialog_dilovod_hurtok" class="modal fade" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="myModalLabel">Додати/Змінити гурткове діловедення</h4>
						</div>
						<div class="modal-body">
							<div class="verifidate"><p></p></div>
							<div class="form-group">
								<label>Дата з</label>
								<div class="input-group date">
									<input type="text" class="datepicker form-control" name="datebegdildh" size="7" id="datebegdildh">
									<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
								</div>
							</div>
							<div class="form-group">
								<label>Дата по</label>
								<div class="input-group date">
									<input type="text" class="datepicker form-control" name="dateenddildh" size="7" id="dateenddildh">
									<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
								</div>
							</div>
							<div class="form-group half">
								<label>Гурткове діловедення</label>
								<select class="selectdildh form-control" id="idseldilhurt">
									<option value="0">Виберіть діловедення</option>
									<c:forEach items="${listdilovodhurtok}" var="listdilovodhurtok">
										<option value="${listdilovodhurtok.id}">
											<c:out value="${listdilovodhurtok.nameDilovodHurt}"/>
										</option>
									</c:forEach>
								</select>
								<button id="adddilvhur" class="btn btn-black showDialog" data-selector="#v">+</button>
							</div>
							<div class="alert alert-danger notcorrect"><p></p></div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-cancel" data-dismiss="modal">Відмінити</button>
							<button type="button" class="btn btn-black btn-save">Зберегти</button>
						</div>
					</div>
				</div>
			</div>

			<!-- Modal add new dilovedenya -->
			<div id="dialog_dilovedennya_hurtka" class="modal fade" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="myModalLabel">Гурткові діловедення</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label>Назва діловедення</label>
								<input type="text" id="namediloved" name="namediloved" class="form-control">
							</div>
							<div class="alert alert-danger notcorrect"><p></p></div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-cancel" data-dismiss="modal">Відмінити</button>
							<button type="button" class="btn btn-black btn-save">Зберегти</button>
						</div>
					</div>
				</div>
			</div>

			<!-- Modal dilovod kurin -->
			<div id="dialog_dilovod_kurin" class="modal fade" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="myModalLabel">Додати/Змінити курінне діловедення</h4>
						</div>
						<div class="modal-body">
							<div class="verifidate"><p></p></div>
							<div class="form-group">
								<label>Дата з</label>
								<div class="input-group date">
									<input type="text" class="datepicker form-control" name="datebegdildk" size="7" id="datebegdildk">
									<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
								</div>
							</div>
							<div class="form-group">
								<label>Дата по</label>
								<div class="input-group date">
									<input type="text" class="datepicker form-control" name="dateenddildk" size="7" id="dateenddildk">
									<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
								</div>
							</div>
							<div class="form-group half">
								<label>Курінне діловедення</label>
								<select class="selectdildk form-control" id="idseldilkurin">
									<option value="0">Виберіть діловедення</option>
									<c:forEach items="${listdilovodkurin}" var="listdilovodkurin">
										<option value="${listdilovodkurin.id}">
											<c:out value="${listdilovodkurin.nameDilovod}"></c:out>
										</option>
									</c:forEach>
								</select>
								<button id="adddilvkur" class="btn btn-black">+</button>
							</div>
							<div class="alert alert-danger notcorrect"><p></p></div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-cancel" data-dismiss="modal">Відмінити</button>
							<button type="button" class="btn btn-black btn-save">Зберегти</button>
						</div>
					</div>
				</div>
			</div>

			<!-- Modal add dilovod kurin -->
			<div id="dialog_dilovedennya_kurin" class="modal fade" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="myModalLabel">Курінні діловедення</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label>Назва діловедення</label>
								<input type="text" id="namedilovedk" name="namedilovedk" class="form-control">
							</div>
							<div class="alert alert-danger notcorrect"><p></p></div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-cancel" data-dismiss="modal">Відмінити</button>
							<button type="button" class="btn btn-black btn-save">Зберегти</button>
						</div>
					</div>
				</div>
			</div>

			<!-- Modal Unak Detail -->
			<div id="dialog_unak_detail" class="modal fade" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="myModalLabel">Інформація про юнака</h4>
						</div>
						<div class="modal-body">
							<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
								<div class="panel panel-default">
									<div class="panel-heading" role="tab" id="headingOne">
										<h4 class="panel-title">
											<a role="button" data-toggle="collapse" data-parent="#accordion" href="#commoninfor" aria-expanded="true" aria-controls="commoninfor">
												Загальна інформація
											</a>
										</h4>
									</div>
									<div id="commoninfor" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
										<div class="panel-body">
										</div>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading" role="tab" id="headingTwo">
										<h4 class="panel-title">
											<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
												Перехід по ступенях
											</a>
										</h4>
									</div>
									<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
										<div class="panel-body">
											<div class="form-group">
												<div id="iddatesworn"></div>
											</div>
											<div id="stupinperson"></div>
										</div>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading" role="tab" id="headingThree">
										<h4 class="panel-title">
											<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#dilovod_hurtok" aria-expanded="false" aria-controls="dilovod_hurtok">
												Діловедення в гуртку
											</a>
										</h4>
									</div>
									<div id="dilovod_hurtok" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
										<div class="panel-body">
										</div>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading" role="tab" id="headingFourth">
										<h4 class="panel-title">
											<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#dilovod_kurin" aria-expanded="false" aria-controls="dilovod_kurin">
												Діловедення в курені
											</a>
										</h4>
									</div>
									<div id="dilovod_kurin" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFourth">
										<div class="panel-body">
										</div>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading" role="tab" id="headingFifth">
										<h4 class="panel-title">
											<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFifth" aria-expanded="false" aria-controls="collapseFifth">
												Перестороги
											</a>
										</h4>
									</div>
									<div id="collapseFifth" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFifth">
										<div class="panel-body">
											На даний момент в розробці
										</div>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading" role="tab" id="headingSixth">
										<h4 class="panel-title">
											<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseSixth" aria-expanded="false" aria-controls="collapseSixth">
												Відзначення
											</a>
										</h4>
									</div>
									<div id="collapseSixth" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSixth">
										<div class="panel-body">
											На даний момент в розробці
										</div>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading" role="tab" id="headingSeventh">
										<h4 class="panel-title">
											<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseSeventh" aria-expanded="false" aria-controls="collapseSeventh">
												Відбуті табори
											</a>
										</h4>
									</div>
									<div id="collapseSeventh" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSeventh">
										<div class="panel-body">
											На даний момент в розробці
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-cancel btn-ok" data-dismiss="modal">Добре</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>