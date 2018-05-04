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
	<script type="text/javascript" src="scripts/kurin.js"></script>
	<script type="text/javascript" src="scripts/stanytsya.js"></script>
	<script type="text/javascript" src="scripts/report-error.js"></script>
</head>
<body>
	<%@ include file = "header.jsp" %>

	<form action="registrkurupu.html" method="post" id="formreg" name="regiskur">
			<div id="stanytsya" class="form-item">
				<fieldset>
					<legend>Станиця</legend>
					<div class="col-xs-4">
						<select id="idselstan" name="selstan" class="form-control select2select">
							<option value="0">Виберіть станицю</option>
							<c:forEach items="${liststanytsya}" var="liststanytsya">
								<option value="${liststanytsya.id}">
									<c:out value="${liststanytsya.namestan}"></c:out>
								</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="col-xs-1">
						<input type="button" value="+" id="addstan" class="btn btn-black btn-sm showDialog" data-selector="#dialog_stanyt">
					</div>
					<div class="col-xs-7">
						<p class="help-block">Якщо в списку немає станиці додайте натиснувши "+"</p>
					</div>
				</fieldset>
			</div>
			<div id="kurinupu" class="form-item">
				<fieldset>
					<legend>Курінь</legend>
					<div class="col-xs-12" id="selkurinorsh">
						<label>Виберіть курінь або самостійний гурток</label>
						<div class="radio">
							<label><input type="radio" value="0" name="group" class="selradio"> Курінь</label>
						</div>
						<div class="radio">
							<label><input type="radio" value="1" name="group" class="selradio"> Самостійний гурток</label>
						</div>
					</div>
					<div class="form-group select2block" id="listkurin">
						<div class="col-xs-4">
							<select id="selectkurin" name="selkur" class="selectkurin form-control select2select">
								<option value="0">Виберіть курінь</option>
							</select>
						</div>
						<div class="col-xs-1">
							<input type="button" value="+" id="addkurin" class="btn btn-black btn-sm showDialog" data-selector="#dialog_kurin">
						</div>
						<div class="col-xs-7">
							<p class="help-block">Якщо в списку немає куреня додайте натиснувши "+"</p>
						</div>
					</div>
					<div class="form-group select2block" id="listsh">
						<div class="col-xs-4">
							<select id="selecthurtok" name="selsamhurt" class="form-control select2select">
								<option value="0">Виберіть самостійний гурток</option>
							</select>
						</div>
						<div class="col-xs-1">
							<input type="button" value="+" class="btn btn-black btn-sm showDialog" data-selector="#dialog_samo_hurtok">
						</div>
						<div class="col-xs-7">
							<p class="help-block">Якщо в списку немає гуртка додайте натиснувши "+"</p>
						</div>
					</div>
					<div class="form-group" id="registercode">
						<div class="col-xs-4">
							<input type="password" id="codereg" class="form-control" placeholder="Кодове слово">
						</div>
						<div class="col-xs-8"><p class="help-text">Введіть кодове слово щоб продовжити<br>(Не передавайте свого коду стороннім особам. Дбай про безпеку даних)</p></div>
						<div class="col-xs-12">
							<input type="hidden" name="parameter" id="parametr" />
							<button id="continreg" class="btn btn-black">Продовжити</button>
						</div>
					</div>
				</fieldset>
			</div>
			<div class="alert alert-danger notcorrect"><p></p></div>
		</form>

	<%@ include file = "footer.jsp" %>
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
				<div class="alert alert-danger notcorrect" role="alert"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-cancel" data-dismiss="modal">Відмінити</button>
				<button type="button" class="btn btn-black btn-save">Зберегти</button>
			</div>
		</div>
	</div>
</div>

<!-- Modal Kurin -->
<div id="dialog_kurin" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="myModalLabel">Курінь</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label>Курінь:</label>
					<select id="numbk" class="form-control">
						<option value="-1">Число</option>
						<option value="0">підг.</option>
						<%
							for (int i = 0; i < 130; i++) {
								int value = i + 1;
						%>
						<option value=<%=value%>><%=value%></option>
						<%
							}
						%>
					</select>
					<select id="sexk" class="form-control">
						<option value="-1">Стать</option>
						<option value="0">Хл</option>
						<option value="1">Дівч</option>
						<option value="2">Мішн</option>
					</select>
				</div>
				<div class="form-group">
					<label>Патрон куреня (імені ...): </label>
					<input type="text" name="namekurin" id="kurinname" class="form-control">
				</div>
				<div class="form-group">
					<label>Дата заснування: </label>
					<div class="input-group date">
						<input type="text" class="datepicker form-control" name="dateregistr" id="dateregister">
						<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
					</div>
				</div>
				<div class="form-group">
					<label><b>E-mail</b> на який буде скинуто <b>код</b> реєстрації для продовження</label>
					<input type="text" id="emailk" name="emailk" class="form-control">
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

<!-- Modal Hurtok -->
<div id="dialog_samo_hurtok" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="myModalLabel">Самостійний гурток</h4>
			</div>
			<div class="modal-body">
				<form>
					<div class="form-group">
						<label for="name_hurtok">Назва гуртка:</label>
						<input type="text" name="namesamhurtok" id="name_hurtok" class="form-control">
					</div>
					<div class="form-group half">
						<label for="sex_hurtok">Стать гуртка</label>
						<select id="sex_hurtok" name="sexsamhurtok" class="form-control" data-error="Обов'язково вибрати стать гуртка!">
							<option value="-1">Вибрати стать</option>
							<option value="0">Хл</option>
							<option value="1">Дівч</option>
							<option value="2">Мішн</option>
						</select>
					</div>
					<div class="form-group">
						<label for="dateregister_hurtok">Дата заснування: </label>
						<div class="input-group date">
							<input type="text" class="datepicker form-control" name="dateregister" id="dateregister_hurtok" data-error="Введіть коректну дату реєстрацію гуртка!">
							<span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
						</div>
					</div>
					<div class="form-group">
						<label for="emblem_hurtok">Відзнака гуртка</label>
						<div class="input-group">
							<label class="btn btn-default btn-file">
								Додати файл <input type="file" id="emblem_hurtok" accept="image/*" style="display: none;" data-error="Обов'язково додати відзнаку гуртка!">
							</label>
							<span class="name-file"></span>
						</div>
					</div>
					<div class="form-group">
						<label for="email_hurtok"><b>E-mail</b> на який буде скинуто <b>код</b> реєстрації для продовження</label>
						<input type="email" name="emailsamhurtok" id="email_hurtok" class="form-control" data-error="Введіть коректний e-mail!">
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
</body>
</html>
