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
	<script type="text/javascript">
		$(document).ready(function(){

			$(".together").css('font-weight', 'bold');

          $('.kurin').each(function(){
            var myVar = $(this).find('.datakurin').text();

            $(this).find('tbody').each(function () {
              if (!$(this).find('.datakurin').length) {
                $(this).prev().remove();
                $(this).remove();
              }
            });
            $(this).fadeIn();

            if(myVar==""){
              $(this).find(".together").empty();
            } else {
              var prykh = 0;
              $(this).find('.prykh').each(function() {prykh +=  parseInt($(this).text());});
              $(this).find('.allprykh').text(prykh).css('text-align','center');
              $(this).find('.prykh').css('text-align','center');

              var uch = 0;
              $(this).find('.uch').each(function() {uch +=  parseInt($(this).text());});
              $(this).find('.alluch').text(uch).css('text-align','center');
              $(this).find('.uch').css('text-align','center');

              var rozv = 0;
              $(this).find('.rozv').each(function() {rozv +=  parseInt($(this).text());});
              $(this).find('.allrozv').text(rozv).css('text-align','center');
              $(this).find('.rozv').css('text-align','center');

              var skbvirl = 0;
              $(this).find('.skbvirl').each(function() {skbvirl +=  parseInt($(this).text());});
              $(this).find('.allskbvirl').text(skbvirl).css('text-align','center');
              $(this).find('.skbvirl').css('text-align','center');

              var htskbvirl = 0;
              $(this).find('.htskbvirl').each(function() {htskbvirl +=  parseInt($(this).text());});
              $(this).find('.allhtskbvirl').text(htskbvirl).css('text-align','center');
              $(this).find('.htskbvirl').css('text-align','center');

              var count = 0;
              $(this).find('.count').each(function() {count +=  parseInt($(this).text());});
              $(this).find('.allcount').text(count).css('text-align','center');
              $('.count').css('font-weight', 'bold').css('text-align','center');
              $('.allcount').css('font-weight', 'bold').css('text-align','center').css('font-size','20px');
            }
          });

			$('.kurin').each(function(){
				var allprykh = 0;
				$('.allprykh').each(function() {allprykh +=  parseInt($(this).text());});
				$('.allsumprykh').text(allprykh).css('font-size','20px');

				var alluch = 0;
				$('.alluch').each(function() {alluch +=  parseInt($(this).text());});
				$('.allsumuch').text(alluch).css('font-size','20px');

				var allrozv = 0;
				$('.allrozv').each(function() {allrozv +=  parseInt($(this).text());});
				$('.allsumrozv').text(allrozv).css('font-size','20px');

				var allskbvirl = 0;
				$('.allskbvirl').each(function() {allskbvirl +=  parseInt($(this).text());});
				$('.allsumskbvirl').text(allskbvirl).css('font-size','20px');

				var allhtskbvirl = 0;
				$('.allhtskbvirl').each(function() {allhtskbvirl +=  parseInt($(this).text());});
				$('.allsumhtskbvirl').text(allhtskbvirl).css('font-size','20px');

				var allcount = 0;
				$('.allcount').each(function() {allcount +=  parseInt($(this).text());});
				$('.allsumcount').text(allcount).css('font-size','30px');
			});

			$("#rep").css('font-size','25px').css('text-decoration', 'underline');

		
			
	});
	</script>
</head>
<body>
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
			<ul class="nav nav-tabs nav-justified">
				<li><a href="registrkurupu.html">Реєстрація куренів</a></li>
				<li class="active"><a href="commonreport.html">Звіт</a></li>
			</ul>
			<!-- /NAVIGATION -->

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
						<input type="checkbox" name="lvivokruha"> Для львівської округи
						<!-- <input type="submit" name="submitreport" value="Сформувати звіт" id="reportok" class="btn btn-success"> -->
					</div>
					<div class="notcorrect alert alert-danger"><p></p></div>
				</form>
			</div>
			<div id="report">
				<form action="viewdetailkurinupu.html" method="post" id="detailkurin">

				<c:forEach items="${listondate}" var="listondate">
					<legend>Звіт по юнацтву станом на:<b><c:out value="${listondate}"/> р.</b></legend>
				</c:forEach>
				
				
					
					
				
			<c:forEach items="${listokruha}" var="listokruha">
				<b><c:out value="${listokruha.nameOkruha}"></c:out></b> округа
			
				<c:forEach items="${liststanytsya}" var="liststanytsya">
					<c:choose>
							<c:when test="${liststanytsya.okruha.id == listokruha.id}">
					
					
					<div class="panel panel-default">
						<div class="panel-heading">Станиця:
							<c:out value="${liststanytsya.namestan}"></c:out></b>
						</div>
						<div class="panel-body">
							<table class="table kurin">
								<thead id="ttt">
									<tr class="together">
										<th>Курінь</th>
										<th>Прихильників</th>
										<th>Учасників</th>
										<th>Розвідувачів</th>
										<th>Скобів/Вірлиць</th>
										<th>Гетьманських скобів/вірлиць</th>
										<th>Всього</th>
										<th>Детальніше</th>
									</tr>									
								</thead>
								<tbody>
									<c:forEach items="${listreportcommon}" var="listreportcommon">
									<c:choose>
										<c:when test="${listreportcommon.idstan == liststanytsya.id}">
										<tr class="datakurin">
											<td>к.ч.<c:out value="${listreportcommon.numberkurin}"/>
												ім.<c:out value="${listreportcommon.namekurin}"/>
											</td>

											<td class="prykh" id="prk"><c:out value="${listreportcommon.prykhylnyk}"></c:out></td>
											<td class="uch"><c:out value="${listreportcommon.uchansnyk}"></c:out></td>
											<td class="rozv"><c:out value="${listreportcommon.rozviduvach}"></c:out></td>
											<td class="skbvirl"><c:out value="${listreportcommon.skobvirlytsya}"></c:out></td>
											<td class="htskbvirl"><c:out value="${listreportcommon.hetmanskobvirlytsa}"></c:out></td>
											<td class="count"><c:out value="${listreportcommon.allcount}"></c:out></td>
											<td class="detailkurin"><button value="${listreportcommon.idkurin}" class="clickdetailkurin detailunak btn btn-xs btn-cancel" name="detail"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> <span class="hidden-xs">Детальніше</span></button></td>
										</tr>										
										</c:when>
									</c:choose>
									</c:forEach>						
									
								</tbody>
								
								<!-- TEST -->
								
								<thead>
									<tr class="together">
										<th>Самостійний гурток</th>
										<th>Прихильників</th>
										<th>Учасників</th>
										<th>Розвідувачів</th>
										<th>Скобів/Вірлиць</th>
										<th>Гетьманських скобів/вірлиць</th>
										<th>Всього</th>
										<th>Детальніше</th>
									</tr>									
								</thead>
								<tbody>
									<c:forEach items="${listreportsmhurtok}" var="listreportsmhurtok">
									<c:choose>
										<c:when test="${listreportsmhurtok.idstan == liststanytsya.id}">
										<tr class="datakurin">
											<td><c:out value="${listreportsmhurtok.namesmhurtok}"/>
											</td>

											<td class="prykh" id="prh"><c:out value="${listreportsmhurtok.prykhylnyk}"></c:out></td>
											<td class="uch"><c:out value="${listreportsmhurtok.uchansnyk}"></c:out></td>
											<td class="rozv"><c:out value="${listreportsmhurtok.rozviduvach}"></c:out></td>
											<td class="skbvirl"><c:out value="${listreportsmhurtok.skobvirlytsya}"></c:out></td>
											<td class="htskbvirl"><c:out value="${listreportsmhurtok.hetmanskobvirlytsa}"></c:out></td>
											<td class="count"><c:out value="${listreportsmhurtok.allcount}"></c:out></td>
											<td class="detailkurin"><button value="${listreportsmhurtok.idsmhurtok}" class="clickdetailkurin detailunak btn btn-xs btn-cancel" name="detail"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> <span class="hidden-xs">Детальніше</span></button></td>
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
				
				</c:forEach>
				</form>
			</div>
			<div id="allReport">
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
		<div id="dialog_report_error" class="modal fade" role="dialog" style="display: none;">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
						<h4 class="modal-title" id="myModalLabel">Форма зворотнього зв'язку</h4>
					</div>
					<div class="modal-body">
						<form id="form-report">
							<div class="form-group">
								<label for="report_fullname">Ім'я та прізвище</label>
								<input type="text" name="sfullname" id="report_fullname" class="form-control">
							</div>
							<div class="form-group">
								<label for="report_email">Email</label>
								<input type="text" name="email" id="report_email" class="form-control">
							</div>
							<div class="form-group">
								<label for="report_text">Текст повідомлення</label>
								<textarea name="textrequest" id="report_text" class="form-control"></textarea>
							</div>
							<div class="form-group">
								<label for="report_text">Файл</label>
								<div class="input-group">
									<label class="btn btn-default btn-file">
										Додати файл <input type="file" id="report_file" accept="image/*, application/pdf, application/rtf, application/x-rtf, application/msword" style="display: none;">
									</label>
									<span class="name-file"></span>
								</div>
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
		<div id="dialog_report_text" class="modal fade" role="dialog" style="display: none;">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
						<h4 class="modal-title" id="myModalLabel">Форма зворотнього зв'язку</h4>
					</div>
					<div class="modal-body">
					</div>
				</div>
			</div>
		</div>
	</main>
	<!-- /MAIN SECTION -->
</div>

<!-- FOOTER -->
<footer class="footer">
	<div class="col-sm-3">
		<a href="https://youtu.be/l7KTcDNfVpY" target="_blank" class="href-faq">Відео-інструкція</a>
	</div>
	<div class="col-sm-6">
		<p class="text-center">© Пласт НСОУ. КБ УПЮ. 2017. | <a href="mailto:vasylshvv@gmail.com">Контакти адміністраторів</a></p>
	</div>
</footer>
<!-- /FOOTER -->
</body>

</html>