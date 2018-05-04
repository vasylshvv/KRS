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
		<script type="text/javascript" src="scripts/hurtok.js"></script>
		<script type="text/javascript" src="scripts/persons.js"></script>
		<script type="text/javascript" src="scripts/stupin.js"></script>
		<script type="text/javascript" src="scripts/zvyazkovyy.js"></script>
		<script type="text/javascript" src="scripts/vporyadnyk.js"></script>
		<script type="text/javascript" src="scripts/stanytsya.js"></script>
		<script type="text/javascript" src="scripts/kurin.js"></script>
		<script type="text/javascript" src="scripts/unakdetail.js"></script>
		<script type="text/javascript" src="scripts/dilovedennya.js"></script>
		<script type="text/javascript" src="scripts/report-error.js"></script>

		<script type="text/javascript">
			$(document).ready(function(){

				$("#selectulad :contains('УПЮ')").attr("selected", "selected");
				$(".add_unak").on('click',function(){
					var idhurtok = $(this).val();
					$('#dialog_unak input').val('');
					$('#dialog_unak select option:selected').removeAttr('selected');
					$("#idselhurt").val(idhurtok);
				});
				$(".add_vporyad").on('click',function(){
					var idhurtok = $(this).val();
					$('#dialog_person input').val('');
					$('#dialog_person select option:selected').removeAttr('selected');
					$("#idselhurtvporyad").val(idhurtok);
				});

				$('.table').each(function(){
					$(this).find('.tablerow').each(function (i) {
						$("td:first", this).html(i+1);
					});
				});

				var idstan = $("#idstan").val();
				$(".liststan").val(idstan).select2();

				var kurinid = $("#kurinid").val();
				$(".selectkurin").val(kurinid).select2();


				$('.gurtok-item').each(function(){
					if ($(this).find('.vpfullname').text().length) {
						$(this).find('.add_vporyad').text('Змінити впорядника')
						$(this).find('.info_vporyad').fadeIn();
					} else {
						$(this).find('.add_vporyad').text('Додати впорядника')
						$(this).find('.info_vporyad').fadeOut();
					}
				});
			});
		</script>
	</head>
	<body>
	<%@ include file = "header.jsp" %>

	<div class="kurin-information">
		<div class="status-label"><span class="mark"></span> Статус: <span class="status-text"></span></div>
		<div class="btn-group absoluteBtns kurinnyj">
			<button type="button" id="sendToAccept" class="btn btn-black">Відправити звіт на підтвердження</button>
		</div>
		<div class="btn-group absoluteBtns zvjazkovyy">
			<button type="button" id="cancelData" class="btn btn-cancel">Відхилити</button>
			<button type="button" id="acceptData" class="btn btn-black">Затвердити звіт</button>
		</div>
		<fieldset>
			<legend>Інформація по куреню</legend>
			<c:forEach items="${listentered}" var="listentered">
				<input type="hidden" value="${listentered}" id="role">
			</c:forEach>

			<c:forEach items="${liststatus}" var="liststatus">
				<input type="hidden" value="${liststatus.approved}" id="liststatus">
				<span id="text_need"><c:out value="${liststatus.need}"></c:out></span>
			</c:forEach>


			<div id="regstanytsya">
				<c:forEach items="${liststanytsya}" var="liststanytsya">
					<input type="hidden" value="${liststanytsya.id}" id="idstan">
					<span class="title">Станиця:</span>
					<span class="value"><c:out value="${liststanytsya.namestan}"></c:out></span>
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
					<button type="button" class="btn btn-cancel btn-xs" id="updatezvyazkovyy">
						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Редагувати
					</button>
				</span>
			</div>
			<div>
				<span class="title"></span>
				<span class="value">
					<input type="button" value="Додати зв'язкового" id="addzvyazkovyy" class="btn btn-black btn-sm">
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
							<th class="hidden-xs">Контактний телефон</th>
							<th>Контактний e-mail</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listprovidkurin}" var="listprovidkurin">
							<tr class="tablerow">
								<td></td>
								<td><c:out value="${listprovidkurin.namedilovod}"></c:out></td>
								<td><c:out value="${listprovidkurin.namestupin}"></c:out></td>
								<td><c:out value="${listprovidkurin.lastname}"></c:out></td>
								<td><c:out value="${listprovidkurin.firstname}"></c:out></td>
								<td class="hidden-xs"><c:out value="${listprovidkurin.phonenumber}"></c:out></td>
								<td><c:out value="${listprovidkurin.email}"></c:out></td>
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
										<th class="hidden-xs">Дата народження</th>
										<th class="hidden-sm hidden-xs">Контактний телефон</th>
										<th class="hidden-xs">Контактний email</th>
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
											<td align="center" class="hidden-sm hidden-xs"><c:out value="${listperson.phonenumber}"></c:out></td>
											<td align="center" class="hidden-xs"><c:out value="${listperson.email}"></c:out></td>
											<td><button value="${listperson.idperson}" class="detailunak btn btn-xs btn-cancel"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> <span class="hidden-xs">Детальніше</span></button></td>
										</tr>
										</c:when>
										</c:choose>
									</c:forEach>
								</tbody>
							</table>
							<button class="add_unak btn btn-black btn-sm showDialog" value="${listhurtokupu.id}" data-selector="#dialog_unak">Додати юнака</button>
						</div>
						<div class="vp">
							<span class="title">Впорядник гуртка: </span>
							<span class="value">
								<c:forEach items="${vporyadnyk}" var="vporyadnyk">
								<c:choose>
								<c:when test="${vporyadnyk.idhurtok ==  listhurtokupu.id}">
									<span class="vpfullname">
										<c:out value="${vporyadnyk.stupin} ${vporyadnyk.lastname} ${vporyadnyk.firstname}"></c:out>
										<b> від </b> <span class="datebegvp"><fmt:formatDate type="date" pattern="dd.MM.yyyy" value="${vporyadnyk.fromdatevp}"/><c:out value="${fromdatevp}"></c:out></span>
									</span>
									<input type="hidden" value="${vporyadnyk.idperson}" class="personvp">
									<button class="info_vporyad btn btn-cancel btn-xs" value="${listhurtokupu.id}">
										<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Редагувати
									</button>
									</br>
								</c:when>
								</c:choose>
								</c:forEach>
							</span>
						</div>
						<div class="btns">
							<span class="title"></span>
							<span class="value">
								<button class="add_vporyad btn btn-black btn-sm" value="${listhurtokupu.id}">Додати впорядника</button>
							</span>
						</div>
					</div>
				</div>
			</c:forEach>
			<input type="button" value="Додати гурток" class="btn btn-black btn-sm showDialog" data-selector="#dialog_hurtok" id="add_hurtok">
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
									<input type="radio" name="group" class="seluladradio" disabled value="1"> УПЮ
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
							<select name="person" class="idperson form-control select2select" id="idpersonvp">
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

		<!-- Modal Gurtok -->
		<div id="dialog_hurtok" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">Гурток</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label>Гурток</label>
							<select id="numbh" class="form-control">
								<option value="0">Число гуртка</option>
								<%for (int i = 0; i < 50; i++) {int value = i + 1;%>
								<option value=<%=value%>><%=value%></option><%}%>
							</select>
						</div>
						<div class="form-group">
							<label>Назва гуртка: </label>
							<input type="text" name="namehurtok" id="hurtokname" class="form-control">
						</div>
						<div class="form-group">
							<label>Дата заснування:</label>
							<div class="input-group date">
								<input type="text" class="datepicker form-control" name="dateregistr" size="7" id="dateregister">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
						</div>
						<div class="form-group">
							<label>Дата розвязання: </label>
							<div class="input-group date">
								<input type="text" class="datepicker form-control" name="dateend" size="7" id="dateend">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
						</div>
						<div class="form-group">
							<label>Відзнака гуртка</label>
							<div class="input-group">
								<label class="btn btn-default btn-file">
									Додати файл <input type="file" name="vidznaka" id="id_vidznaka" accept="image/*" style="display: none;">
								</label>
								<span class="name-file"></span>
							</div>
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


		<!-- Modal About Vporyadnyk -->
		<div id="about_vporydnyk" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">Приналежність до куреня</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<span class="title">Впорядник: </span>
							<span class="value">
								<span id="vpfull"></span>
							</span>
						</div>
						<div class="form-group">
							<span class="title">Гурток</span>
							<span class="value">
								<select id="about_sel_hurt_vp" class="form-control" name="sel_hurt_vporyad">
									<option value="0">Виберіть гурток</option>
									<c:forEach items="${listhurtokupu}" var="listhurtokupu">
										<option value="${listhurtokupu.id}">
											<c:out value="${listhurtokupu.nameHurtok}"></c:out>
										</option>
										<br>
									</c:forEach>
								</select>
							</span>
						</div>
						<div class="form-group">
							<span class="title">від:</span>
							<span class="value">
								<div class="input-group date">
									<input type="text" class="datepicker form-control" name="datestartzvp" size="7" id="datestartvp">
									<span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</span>
						</div>
						<div class="form-group">
							<span class="title"></span>
							<span class="value">
								<button id="vpordetail" class="btn btn-cancel btn-sm">
									<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> Більше інформації
								</button>
							</span>
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

		<!-- Modal New Zvyazkovyy -->
		<div id="dialog_person" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">Новий виховник</h4>
					</div>
					<div class="modal-body">
						<div class="form-group form-group-sm">
							<label>Прізвище</label>
							<input type="text" name="lastnamep" id="lastnamep" class="form-control input-sm">
						</div>
						<div class="form-group form-group-sm">
							<label>Ім'я</label>
							<input type="text" name="firstnamep" id="firstnamep" class="form-control input-sm">
						</div>
						<div class="form-group form-group-sm">
							<label>Дата народження</label>
							<div class="input-group date">
								<input type="text" class="datepicker form-control input-sm" name="datebirthdayp" size="7" id="datebirthdayp">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
						</div>
						<div class="form-group form-group-sm half">
							<label>Улад</label>
							<select class="selectuladp form-control input-sm" id="idselectuladp">
								<option value="0">Виберіть улад</option>
								<c:forEach items="${listulad}" var="listulad">
									<option value="${listulad.id}"><c:out value="${listulad.nameulad}"></c:out></option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group form-group-sm half">
							<label>Ступінь</label>
							<select class="selstupinp form-control input-sm" id="idselstupinp">
								<option value="0">Виберіть ступінь</option>
							</select>
						</div>
						<div class="form-group form-group-sm half">
							<label>Виберіть станицю</label>
							<select id="idselstanp" class="form-control input-sm">
								<option value="0">Вибрати станицю</option>
								<c:forEach items="${liststanp}" var="liststanp">
									<option value="${liststanp.id}"><c:out value="${liststanp.namestan}"></c:out></option>
								</c:forEach>
							</select>
							<button class="btn btn-black showDialog btn-sm" data-selector="#dialog_stanyt">+</button>
						</div>
						<div class="form-group form-group-sm half">
							<label>Приналежність до куреня УСП/УПС</label>
							<select id="selkurinusp" class="form-control input-sm">
								<option value="0">Виберіть курінь</option>
								<c:forEach items="${listkurinuspups}" var="listkurinuspups">
									<option value="${listkurinuspups.id}"><c:out value="${listkurinuspups.namekurin}"></c:out></option>
								</c:forEach>
							</select>
							<button id="addkurinpusp" class="btn btn-black btn-sm showDialog" data-selector="#dialog_kurin_uspups">+</button>
						</div>
						<div class="form-group form-group-sm">
							<label>Ступінь КВ</label>
							<select id="idstupkv" class="form-control input-sm">
								<option value="0">Вибрати ступінь в КВ</option>
								<c:forEach items="${listkv}" var="listkv">
									<option value="${listkv.id}"><c:out value="${listkv.name}"></c:out></option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group form-group-sm">
							<label>Вишколи</label>
							<div class="checkbox">
								<c:forEach items="${listvyshkil}" var="listvyshkil">
									<label class="checkbox-inline">
										<input type="checkbox" value="${listvyshkil.id}" name="vyshkil[]"> <c:out value="${listvyshkil.namevyshkil}"/>
									</label>
								</c:forEach>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label>Вишкіл ЛШ\ШБ</label>
							<select id="idselvyshkiltab" class="form-control input-sm">
								<option value="0">Вибрати таборовий вишкіл</option>
								<c:forEach items="${listvyshkiltabir}" var="listvyshkiltabir">
									<option value="${listvyshkiltabir.id}"><c:out value="${listvyshkiltabir.namevyshkiltabir}"></c:out></option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group form-group-sm">
							<label>Адреса (місце проживання)</label>
							<input type="text" id="adressid" class="form-control input-sm">
						</div>
						<div class="form-group form-group-sm">
							<label>Місце праці/навчання</label>
							<input type="text" id="workid" class="form-control input-sm">
						</div>
						<div class="form-group form-group-sm">
							<label>Телефон</label>
							<input type="text" name="phonep" id="phonenumberp" class="form-control input-sm">
						</div>
						<div class="form-group form-group-sm">
							<label>E-mail</label>
							<input type="text" name="emailp" id="emailp" class="form-control input-sm">
						</div>
						<div class="form-group form-group-sm date-picker">
							<label>Дата вступу в пласт</label>
							<div class="input-group date">
								<input type="text" class="datepicker form-control input-sm" name="dateregistrp" id="dateregistrp">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
						</div>
						<div class="form-group form-group-sm date-picker">
							<label>Дата заприсяження</label>
							<div class="input-group date">
								<input type="text" class="datepicker form-control input-sm" name="dateswornp" id="dateswornp">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
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

		<!-- Modal Add Unak -->
		<div id="dialog_unak" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">Новий член гуртка</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label>Гурток</label>
							<select id="idselhurt" name="selhurt" class="selhurt form-control">
								<option value="0">Виберіть гурток</option>
								<c:forEach items="${listhurtokupu}" var="listhurtokupu">
									<option value="${listhurtokupu.id}">
										<c:out value="${listhurtokupu.nameHurtok}"></c:out>
									</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label>Прізвище</label>
							<input type="text" name="lastname" id="lastname" class="form-control">
						</div>
						<div class="form-group">
							<label>Ім'я</label>
							<input type="text" name="firstname" id="firstname" class="form-control">
						</div>
						<div class="form-group">
							<label>Дата народження</label>
							<div class="input-group date">
								<input type="text" class="datepicker form-control" name="datebirthday" size="7" id="datebirthday">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
						</div>
						<div class="form-group">
							<label>Адреса (місце проживання)</label>
							<input type="text" name="adress" id="addressYu" class="form-control">
						</div>
						<div class="form-group">
							<label>Місце навчання</label>
							<input type="text" name="education" id="ideducation" class="form-control">
						</div>
						<div class="form-group">
							<label>Телефон</label>
							<input type="text" name="phone" id="phonenumber" class="form-control">
						</div>
						<div class="form-group">
							<label>e-mail</label>
							<input type="text" name="email" id="email" class="form-control">
						</div>
						<div class="form-group">
							<label>Дата вступу в пласт</label>
							<p class="help-block">(вважати дату іменування прихильником)</p>
							<div class="input-group date">
								<input type="text" class="datepicker form-control" name="dateregistr" size="7" id="dateregistr">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
						</div>
						<div class="form-group">
							<label>Дата заприсяження</label>
							<div class="input-group date">
								<input type="text" class="datepicker form-control" name="datesworn" size="7" id="datesworn">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
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

		<!-- Modal kurin USP -->
		<div id="dialog_kurin_uspups" class="modal fade" role="dialog">
			 <div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">Приналежність до куреня</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label>Введіть курніь</label>
							<input type="text" name="namekurin" id="idkurinuspups" class="form-control">
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
						<input type="hidden" id="unakulad" value="2">
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

		<!-- Modal Stanycya -->
		<div id="dialog_stanyt" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">Станиця</h4>
					</div>
					<div class="modal-body">
						<form>
							<div class="form-group">
								<label for="selectokruha">Виберіть округу</label>
								<select id="selectokruha" name="idokruha" class="form-control">
									<option value="0">Виберіть округу</option>
									<c:forEach items="${listokruha}" var="listokruha">
										<option value="${listokruha.id}">
											<c:out value="${listokruha.nameokruha}"></c:out>
										</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label for="stan">Введіть станицю</label>
								<input type="text" name="namestanp" id="stan" class="form-control" pattern="[а-яА-ЯїЇіІє'a-zA-Z0-9-]+">
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-cancel" data-dismiss="modal">Відмінити</button>
						<button type="button" class="btn btn-black btn-save">Зберегти</button>
					</div>
				</div>
			</div>
		</div>
		<div id="dialog_with_error" class="modal fade" role="dialog" style="display: none;">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
						<h4 class="modal-title"></h4>
					</div>
					<div class="modal-body">
					</div>
				</div>
			</div>
		</div>
		<div id="reason_cancel" class="modal fade" role="dialog" style="display: none;">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
						<h4 class="modal-title">Причина відхилення</h4>
					</div>
					<div class="modal-body">
						<form id="form-reason">
							<div class="form-group">
								<label for="reason">Причина</label>
								<textarea id="reason" class="form-control" rows="3"></textarea>
							</div>
						</form>
						<div class="alert alert-danger notcorrect" style="display: none;"></div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-cancel" data-dismiss="modal">Відмінити</button>
						<button type="button" class="btn btn-black btn-send" id="form-reason-submit">Відправити</button>
					</div>
				</div>
			</div>
		</div>
		<div id="form_need" class="modal fade" role="dialog" style="display: none;">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
						<h4 class="modal-title">Потреби куреня</h4>
					</div>
					<div class="modal-body">
						<form id="form-need">
							<div class="form-group">
								<label for="need" id="label_need">Вкажи, будь ласка, чи ваш курінь щось потребує (книги, методичні матеріали, тощо)</label>
								<textarea id="need" class="form-control" rows="3"></textarea>
							</div>
						</form>
						<div class="alert alert-danger notcorrect" style="display: none;"></div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-cancel" data-dismiss="modal">Відмінити</button>
						<button type="button" class="btn btn-black btn-send">Відправити</button>
					</div>
				</div>
			</div>
		</div>
		<table class="table table-condensed table-reason">
			<thead>
			<tr>
				<th>Дата</th>
				<th>Причина</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${listreason}" var="listreason">
				<tr>
					<td><fmt:formatDate type="date" pattern="dd.MM.yyyy" value="${listreason.datereason}"/></td>
					<td><c:out value="${listreason.description}"/></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</body>
</html>