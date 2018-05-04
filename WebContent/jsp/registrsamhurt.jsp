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
		<script type="text/javascript" src="scripts/stupin.js"></script>
		<script type="text/javascript" src="scripts/samohurtok.js"></script>
		<script type="text/javascript" src="scripts/report-error.js"></script>

	</head>
	<body>
	<%@ include file = "header.jsp" %>

	<div class="hurtok-info">
						<div class="status-label"><span class="mark"></span> Статус: <span class="status-text"></span></div>
						<div class="btn-group absoluteBtns kurinnyj">
							<button type="button" id="sendToAccept" class="btn btn-black">Відправити звіт на підтвердження</button>
						</div>
						<div class="btn-group absoluteBtns zvjazkovyy">
							<button type="button" id="cancelData" class="btn btn-cancel">Відхилити</button>
							<button type="button" id="acceptData" class="btn btn-black">Затвердити звіт</button>
						</div>
						<fieldset>
							<legend>Інформація по самостійному гуртку</legend>
							<c:forEach items="${listentered}" var="listentered">
								<input type="hidden" value="${listentered}" id="role">
							</c:forEach>
							
								<c:forEach items="${liststatus}" var="liststatus">
								 <input type="hidden" value="${liststatus.approved}" id="liststatus">
								 <span id="text_need"><c:out value="${liststatus.need}"></c:out></span>
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
									<button type="button" class="btn btn-cancel btn-xs" id="updatevporyadnyk">
										<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Редагувати
									</button>
								</div>
							</div>
							<div class="info-item">
								<div class="title"></div>
								<div class="value">
									<input type="button" value="Додати впорядника" id="addvporyadnyk" class="btn btn-black btn-sm" data-change="false">
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
										<th>Контактний телефон</th>
										<th>Контактний e-mail</th>
										<th>Детальніше</th>
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
															<td><c:out value="${listperson.phonenumber}"></c:out></td>
															<td><c:out value="${listperson.email}"></c:out></td>

															<td>
											<button value="${listperson.idperson}" class="detailunak btn btn-xs btn-cancel"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> Детальніше</button>
										</td>
														</tr>
													</c:forEach>

								</tbody>
							</table>
							<button class="add_unak btn btn-black btn-sm showDialog" value="" data-selector="#dialog_unak">Додати юнака</button>
						</fieldset>
					</div>

	<%@ include file = "footer.jsp" %>

		<!-- Modal Vporyadnyk -->
		<div id="dialog_vporyadnyk" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">Впорядник</h4>
					</div>
					<div class="modal-body">
						<form id="form_vporyadnyk">
							<input type="hidden" name="kurinorsmhurt" value="1">
							<input type="hidden" name="addupdate" value="1">
							<input type="hidden" name="samhurtokid">
							<div class="form-group">
								<label>Дата закінчення попереднього впорядника</label>
								<div class="input-group date">
									<input type="text" class="datepicker form-control" name="olddatetovp" size="7" id="vpdateto">
									<span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
							<div class="form-group">
								<label>Дата початку нового впорядника</label>
								<div class="input-group date">
									<input type="text" class="datepicker form-control" name="newdatefromvp" size="7" id="vpdatefrom">
									<span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
							<div class="form-group" id="seluladzv">
								<p class="help-block">Виберіть улад УПЮ, якщо впорядник молодший </small></p>
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
								<select id="liststan" class="form-control select2select">
									<option value="0">Виберіть станицю</option>
									<c:forEach items="${liststanp}" var="liststanp">
										<option value="${liststanp.id}"><c:out value="${liststanp.namestan}"/></option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group select2block" id="kurinUPUvp">
								<label>Виберіть курінь УПЮ</label>
								<select id="selectkurin" class="form-control select2select">
									<option value="0">Виберіть курінь</option>
									<c:forEach items="${listkurinupustan}" var="listkurinupustan">
										<option value="${listkurinupustan.id}"><c:out value="к.ч.${listkurinupustan.numberKurin} ім.${listkurinupustan.nameKurin}"></c:out></option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group half">
								<label>Список пластунів</label>
								<select id="idperson" name="newvporyadnyk" class="form-control select2select">
									<option value="0">Виберіть з списку</option>
									<c:forEach items="${listpersonsuspups}" var="listpersonsuspups">
										<option value="${listpersonsuspups.idperson}">
											<c:out value="${listpersonsuspups.stupin} ${listpersonsuspups.lastname} ${listpersonsuspups.firstname}"></c:out>
										</option>
									</c:forEach>
								</select>
								<input type="button" class="btn btn-cancel btn-sm" value="перегляд" id="viewpers">
								<input type="button" class="btn btn-black btn-sm showDialog" value="додати" data-selector="#dialog_person">
							</div>
						</form>
						<div class="alert alert-danger notcorrect"><p></p></div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-cancel" data-dismiss="modal">Відмінити</button>
						<button type="button" class="btn btn-black btn-save">Зберегти</button>
					</div>
				</div>
			</div>
		</div>

		<!-- Modal About vporyadnyk -->
		<div id="about_vporydnyk" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">Інформація про впорядника</h4>
					</div>
					<div class="modal-body">
					<c:forEach items="${vporyadnyk}" var="vporyadnyk">
						<div id="infvp" class="form-group">
							<input type="hidden" value="${vporyadnyk.idperson}" id="idperson">
							<span class="title">Впорядник</span>
							<span class="value"><c:out value="${vporyadnyk.stupin} ${vporyadnyk.lastname} ${vporyadnyk.firstname}"></c:out></span>
						</div>
						<div class="form-group">
							<span class="title">від:</span>
							<div class="value">
								<div class="input-group date">
									<input type="text" class="datepicker form-control input-sm" name="datestartzv" size="7" id="datestartvp">
									<span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<span class="title"></span>
							<span class="value">
								<button type="button" class="btn btn-cancel btn-sm" value="${vporyadnyk.idperson}" id="detailvp" >
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

		<!-- Modal New Zvyazkovyy -->
		<div id="dialog_person" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">Новий виховник</h4>
					</div>
					<div class="modal-body">
						<form id="form-person">
							<input type="hidden" name="idperson" class="idperson">
							<div class="form-group form-group-sm">
								<label>Прізвище</label>
								<input type="text" name="lastname" id="lastnamep" class="form-control input-sm">
							</div>
							<div class="form-group form-group-sm">
								<label>Ім'я</label>
								<input type="text" name="firstname" id="firstnamep" class="form-control input-sm">
							</div>
							<div class="form-group form-group-sm">
								<label>Дата народження</label>
								<div class="input-group date">
									<input type="text" class="datepicker form-control input-sm" name="datebirth" size="7" id="datebirthdayp">
									<span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
							<div class="form-group form-group-sm half">
								<label>Улад</label>
								<select name="selectulad" class="selectuladp form-control input-sm" id="idselectuladp">
									<option value="0">Виберіть улад</option>
									<c:forEach items="${listulad}" var="listulad">
										<option value="${listulad.id}"><c:out value="${listulad.nameulad}"></c:out></option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group form-group-sm half">
								<label>Ступінь</label>
								<select name="selectstupin" class="selstupinp form-control input-sm" id="idselstupinp">
									<option value="0">Виберіть ступінь</option>
								</select>
							</div>
							<div class="form-group form-group-sm half">
								<label>Виберіть станицю</label>
								<select name="stanytsya" id="idselstanp" class="form-control input-sm">
									<option value="0">Вибрати станицю</option>
									<c:forEach items="${liststanp}" var="liststanp">
										<option value="${liststanp.id}"><c:out value="${liststanp.namestan}"></c:out></option>
									</c:forEach>
								</select>
								<button class="btn btn-black showDialog btn-sm" data-selector="#dialog_stanyt">+</button>
							</div>
							<div class="form-group form-group-sm half">
								<label>Приналежність до куреня УСП/УПС</label>
								<select name="selkurinusp" id="selkurinusp" class="form-control input-sm">
									<option value="0">Виберіть курінь</option>
									<c:forEach items="${listkurinuspups}" var="listkurinuspups">
										<option value="${listkurinuspups.id}"><c:out value="${listkurinuspups.namekurin}"></c:out></option>
									</c:forEach>
								</select>
								<button id="addkurinpusp" class="btn btn-black btn-sm showDialog" data-selector="#dialog_kurin_uspups">+</button>
							</div>
							<div class="form-group form-group-sm">
								<label>Ступінь КВ</label>
								<select name="stupinkv" id="idstupkv" class="form-control input-sm">
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
								<select name="selvyshkiltabir" id="idselvyshkiltab" class="form-control input-sm">
									<option value="0">Вибрати таборовий вишкіл</option>
									<c:forEach items="${listvyshkiltabir}" var="listvyshkiltabir">
										<option value="${listvyshkiltabir.id}"><c:out value="${listvyshkiltabir.namevyshkiltabir}"></c:out></option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group form-group-sm">
								<label>Адреса (місце проживання)</label>
								<input name="address" type="text" id="adressid" class="form-control input-sm">
							</div>
							<div class="form-group form-group-sm">
								<label>Місце праці/навчання</label>
								<input name="jobeducat" type="text" id="workid" class="form-control input-sm">
							</div>
							<div class="form-group form-group-sm">
								<label>Телефон</label>
								<input type="text" name="phonenumber" id="phonenumberp" class="form-control input-sm">
							</div>
							<div class="form-group form-group-sm">
								<label>E-mail</label>
								<input type="text" name="email" id="emailp" class="form-control input-sm">
							</div>
							<div class="form-group form-group-sm date-picker">
								<label>Дата вступу в пласт</label>
								<div class="input-group date">
									<input type="text" class="datepicker form-control input-sm" name="dateregistr" id="dateregistrp">
									<span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
							<div class="form-group form-group-sm date-picker">
								<label>Дата заприсяження</label>
								<div class="input-group date">
									<input type="text" class="datepicker form-control input-sm" name="datesworn" id="dateswornp">
									<span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
						</form>
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
									<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#stupinperson" aria-expanded="false" aria-controls="stupinperson">
										Перехід по ступенях
									</a>
								  </h4>
								</div>
								<div id="stupinperson" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
									<div class="panel-body">
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
							<label>Введіть курінь</label>
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
						<h4 class="modal-title">Повідомлення про помилку</h4>
					</div>
					<div class="modal-body">
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
						<h4 class="modal-title">Потреби самостійного гуртка</h4>
					</div>
					<div class="modal-body">
						<form id="form-need">
							<div class="form-group">
								<label for="need" id="label_need">Вкажи, будь ласка, чи твій гурток щось потребує (книги, методичні матеріали, тощо)</label>
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
	</body>
</html>