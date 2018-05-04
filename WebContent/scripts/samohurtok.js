var idpersonusp;

$(document).ready(function() {
	/* GLOBAL FUNCTION */
	$("#phonenumber").mask("38(999)999-99-99");
	$("#phonenumberp").mask("38(999)999-99-99");
	$.fn.datepicker.defaults.language = 'ua';
	$('.datepicker').datepicker({format: "dd.mm.yyyy", autoclose: true});
	$('.select2select').select2();
	$('body').on('click', '.showDialog', showDialog);
	$(document).on('hidden.bs.modal', '.modal', function (e) {
		$('.modal:visible').length && $(document.body).addClass('modal-open');
		$(e.target).find('input[type=text], input[type=email]').val('');
		$(e.target).find('input[type=checkbox]').removeProp('checked');
		$(e.target).find('select').val('0');
		$(e.target).find('.panel-body').html('');
		$(e.target).find('.notcorrect').html('').hide();
	});
  $('#dialog_person .selectuladp option[value=1]').prop('disabled', 'disabled');

	/* AUTO LOAD */
	checkNumberTable();
	checkVporyadnyk();
  checkRole();
  checkStatus();

	/* VPORYADNYK */
	$('#addvporyadnyk').on('click', showAddVporyadnyk);
	$('.seluladradio').on('change', changeUlad);
	$('#liststan').on('change', changeStan);
	$('#selectkurin').on('change', changeUpuKurin);
	$('body').on('click', '#dialog_vporyadnyk .btn-save', saveVporyadnyk);

	/* UPDATE PERSON */
	$('#viewpers').on('click', showDetailPerson);
	$('body').on('click', '#dialog_person .btn-save', savePerson);

	/* UPDATE VYHOVNYK */
	$('#updatevporyadnyk').on('click', showUpdateVporydnyk);
	$('body').on('click', '#about_vporydnyk .btn-save', updateDateVporyadnyk);
	$('#detailvp').on('click', showDetailPerson);

	/* STANYTCYA */
	$('body').on('click', '#dialog_stanyt .btn-save', saveStanycya); /* add new */

	/* УСП УПС курені*/
	$('body').on('click', '#dialog_kurin_uspups .btn-save', saveKurinUSP);

	/* STUPIN */
	$('.selectuladp').on('change', changeStupin);

	/* UNAK */
	$('body').on('click', '#dialog_unak .btn-save', saveUnak);
	$('.detailunak').on('click', showDetailUnak);
	$('body').on('click', '#change', showEditUnak);
	$('body').on('click', '#dialog_unak_detail .btn-ok', saveUnakDetail);

	/* DETAIL UNAK */
	$('body').on('click', '#addstupin', addStupin);
	$('body').on('click', '#dialog_move_stupin .btn-save', saveMoveStupin);
	$('body').on('click', '.changestup', changeMoveStupin);
	$('body').on('click', '#adddilovodhurt', addDilovod);
	$('body').on('click', '#dialog_dilovod_hurtok .btn-save', saveDilovod);
	$('body').on('click', '.changedilovod', changeDilovod);

  /* Send to zv */
  $('body').on('click', '#sendToAccept', sendToVh);
  $('body').on('click', '#form_need .btn-send', sendToAcceptVh);

  /* Work with data */
  $('body').on('click', '#acceptData', acceptData);
  $('body').on('click', '#cancelData', function(){ $('#reason_cancel').modal(); });
  $('body').on('click', '#form-reason-submit', cancelData);
  $('body').on('click', '.info-reason', openReason);
});

function checkNumberTable() {
	$('.table').each(function(){
		$(this).find('.tablerow').each(function (i) {
			$("td:first", this).html(i+1);
		});
	});
}

function checkVporyadnyk() {
	if ($('#vporyadnyk').text().trim().length) {
		$('#addvporyadnyk').val('Змінити впорядника').attr('data-change', true);
		$('#updatevporyadnyk').show();
	}
}

function showDialog(){
	var selector = $(this).attr('data-selector');
	$(selector).modal();
}

function showAddVporyadnyk(){
	console.log($(this).attr('data-change'));
	if ($(this).attr('data-change') == 'false') {
		$('#vpdateto').closest('.form-group').hide();
		$('input[name=addupdate]').val('0');
	}
	$('input[name=samhurtokid]').val($('#hurtokid').val());
	$('#dialog_vporyadnyk').modal();
}

function changeUlad(){
	var idselstan = $('#liststan').val();

	if ($('.seluladradio:checked').val() == '1'){
		$('#kurinUPUvp').show();

		if (idselstan != '0') {
			getListKureniv(idselstan);
			$('#idperson').html('<option value="0">Виберіть з списку</option>').select2();
		}

	} else {
		$('#kurinUPUvp').hide();

		if (idselstan != '0') {
			getListPersons(idselstan);
			$('#selectkurin').empty().append('<option value="0">Виберіть курінь</option>').select2();
		}
	}
}

function changeStan(){
	var idselstan = $(this).val();

	if (idselstan == '0') {
		$('#dialog_vporyadnyk .notcorrect').html('Потрібно вибрати станицю!').show();
		$('#selectkurin').empty().append('<option value="0">Виберіть курінь</option>');
		$('#idperson').html('<option value="0">Виберіть з списку</option>').select2();
	} else {
		$('#dialog_vporyadnyk .notcorrect').empty().hide();
		if ($('.seluladradio:checked').val() == '0') {
			getListPersons(idselstan);
		} else {
			getListKureniv(idselstan);
		}
	}
}

function changeUpuKurin(){
	var kurinUpu = $(this).val();

	if (kurinUpu == '0') {
		$('#dialog_vporyadnyk .notcorrect').html('Потрібно вибрати курінь!').show();
		$('#idperson').html('<option value="0">Виберіть з списку</option>').select2();
	} else {
		getListPersons(0, kurinUpu);
	}
}

function getListKureniv(idstan) {
	$.get('addkurinupu.html', {idselstan: idstan, kurinsh: 0}, function(result) {
		$('#selectkurin').empty().append('<option value="0">Виберіть курінь</option>');
		$.each(result, function(index, val) {
      const name = (val.number > 0 ? 'к. ч. ' + val.number : 'підг. к.') + ' ім. ' + val.namekurin ;
      $('#selectkurin').append('<option value="' + val.key + '">' + name + '</option>');
		});
	}, 'json');
}

function getListPersons(idstan, kurin) {
	if (idstan == '0') {
		var data = { kurinid: kurin};
	} else {
		var data = { idselstan: idstan};
	}

	$.get('selectstanpersons.html', data, function(xml) {
		$('#idperson').empty();
		$(xml).find("personsuspups").each(function(){
			var id = $(this).find("id").text(),
				fullnamepersons = $(this).find("fullnamepersons").text();
			$('#idperson').append('<option value="' + id + '">' + fullnamepersons + '</option>');
		});
	});
}

function showDetailPerson() {
  var idperson = $(this).closest('.modal-dialog').find("#idperson").val();

	if (idperson == '0'){
		$("#dialog_vporyadnyk .notcorrect").html('Виберіть зі списку!').show();
	} else {
		$("#dialog_vporyadnyk .notcorrect").empty().hide();
		getDetailPerson(idperson);
		$('#dialog_person .idperson').val(idperson);
		$("#dialog_person").modal();
	}
}

function getDetailPerson(idperson){
	$.get('detailperson.html', {personid: idperson}, function(xml) {
		$(xml).find("person").each(function(index, value) {
			$("#lastnamep").val($(this).find("lastname").text());
			$("#firstnamep").val($(this).find("firstname").text());
			$("#datebirthdayp").val($(this).find("datebirth").text());
			$("#idselectuladp").val($(this).find("uladid").text());

			var stanytsyaid = $(this).find("stanytsyaid").text()
			stanytsyaid = (stanytsyaid == "null") ? 0 : stanytsyaid;
			$("#idselstanp").val(stanytsyaid);

			var kurinuspid = $(this).find("kurinuspid").text()
			kurinuspid = (kurinuspid == "null") ? 0 : kurinuspid;
			$("#selkurinusp").val(kurinuspid);

			var kvid = $(this).find("kvid").text()
			kvid = (kvid == "null") ? 0 : kvid;
			$("#idstupkv").val(kvid);

			var vyshkiltabirid = $(this).find("vyshkiltabirid").text()
			vyshkiltabirid = (vyshkiltabirid == "null") ? 0 : vyshkiltabirid;
			$("#idselvyshkiltab").val(vyshkiltabirid);

			getListStupin($(this).find("uladid").text(), $(this).find("stupinid").text());

			$("#adressid").val($(this).find("adress").text());
			$("#workid").val($(this).find("jobeduc").text());
			$("#phonenumberp").val($(this).find("phone").text());
			$("#emailp").val($(this).find("email").text());
			$("#dateregistrp").val($(this).find("datestart").text());
			$("#dateswornp").val($(this).find("datesworn").text());

			var vyshkil = new Array();
			$(xml).find("personvyshkil").each(function(){
				var idvyshil = $(this).find("idvyshil").text();
				if(idvyshil!="null"){
					$('input[name="vyshkil[]"]')[idvyshil-1].checked = true;
				} else {
					$('input[name="vyshkil[]"]')[0].checked = false;
					$('input[name="vyshkil[]"]')[1].checked = false;
					$('input[name="vyshkil[]"]')[2].checked = false;
					$('input[name="vyshkil[]"]')[3].checked = false;
				}
			});
		});
	});
}

function getListStupin(idulad, stupinid) {
	if (idulad == 0) {
		$("dialog_person .notcorrect").html('Виберіть улад!').show();
	} else {
		$("#idselstupinp").empty();
		$.get('selectstupin.html', {ulad: idulad}, function(xml) {
			$(xml).find("stupin").each(function() {
				var stupin = '',
					id = $(this).find("id").text(),
					namestupin = $(this).find("namestupin").text();
				stupin = (id == stupinid) ? '<option value="' + id + '" selected>' + namestupin + '</option>' : '<option value="' + id + '">' + namestupin	+ '</option>';

				$("#idselstupinp").append(stupin);
			});
		});
	}
}

var characterReg = /^\s*[а-яА-ЯїЇіІєЄ"'a-zA-Z0-9,\-\s]+\s*$/;
var rgexpDate = /(^(((0[1-9]|1[0-9]|2[0-8])[-/.](0[1-9]|1[012]))|((29|30|31)[-/.](0[13578]|1[02]))|((29|30)[-/.](0[4,6,9]|11)))[-/.](19|[2-9][0-9])\d\d$)|(^29[-/.]02[-/.](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)/;
var regexpemail = /[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/i;
var regexpnumber = /^38\(\d{3}\)\d{3}-\d{2}-\d{2}$/;

function savePerson(){
  $('#dialog_person .btn-save').prop('disabled', 'true');
  var today = new Date();
  var dateBirthday = $("#datebirthdayp").val().trim().split('.');
  var dateRegister = $("#dateregistrp").val().split('.');

  if($("#lastnamep").val().trim() == ""){
		isSubmitting('#dialog_person', "Заповніть прізвище!");
	} else if (!characterReg.test($("#lastnamep").val().trim())){
		isSubmitting('#dialog_person', "Заповніть коректно прізвище!");
	} else if ($("#firstnamep").val().trim() == ""){
		isSubmitting('#dialog_person', "Заповніть ім\'я!");
	} else if (!characterReg.test($("#firstnamep").val().trim())){
		isSubmitting('#dialog_person', "Заповніть коректно ім\'я!");
	} else if ($("#datebirthdayp").val().trim() == ""){
		isSubmitting('#dialog_person', "Заповніть дату народження!");
	} else if (today < new Date(dateBirthday[2], dateBirthday[1] - 1, dateBirthday[0])) {
    isSubmitting('#dialog_person', "Дата народження не може бути більша за сьогоднішню!");
  } else if (!rgexpDate.test($("#datebirthdayp").val().trim())){
		isSubmitting('#dialog_person', "Заповніть коректно дату народження!");
	} else if ($("#idselectuladp").val() == 0){
		isSubmitting('#dialog_person', "Виберіть улад!");
	} else if ($("#idselstupinp").val() == 0){
		isSubmitting('#dialog_person', "Виберіть ступінь!");
	} else if ($("#idselstanp").val() == 0){
		isSubmitting('#dialog_person', "Виберіть станицю!");
	} else if ($("#adressid").val().trim() == ""){
		isSubmitting('#dialog_person', "Заповніть адресу!");
	} else if ($("#workid").val().trim() == ""){
		isSubmitting('#dialog_person', "Заповніть місце праці або навчання!");
	} else if ($("#phonenumberp").val() == 0){
		isSubmitting('#dialog_person', "Заповніть номер телефону!");
	} else if (!regexpnumber.test($("#phonenumberp").val())){
		isSubmitting('#dialog_person', "Заповніть коректно номер телефону!");
	} else if ($("#emailp").val() == ""){
		isSubmitting('#dialog_person', "Заповніть e-mail!");
	} else if (!regexpemail.test($("#emailp").val())){
		isSubmitting('#dialog_person', "Заповніть коректно e-mail!");
	} else if ($("#dateregistrp").val() == ""){
		isSubmitting('#dialog_person', "Заповніть дату вступу в Пласт!");
	} else if (today < new Date(dateRegister[2], dateRegister[1] - 1, dateRegister[0])) {
    isSubmitting('#dialog_person', "Дата вступу не може бути більша за сьогоднішню!");
  } else if (!rgexpDate.test($("#dateregistrp").val())){
		isSubmitting('#dialog_person', "Заповніть коректно дату вступу в Пласт!");
	} else {
    $("#form-person .idperson").val('0');
    var vyshkil = [];
    $('#dialog_person .checkbox-inline input:checked').each(function () {
			vyshkil.push($(this).val());
    });
    $('#form-person').append('<input type="hidden" id="hidden-vyshkil" name="vyshkil" value="' + vyshkil.join(',') + '"/>');
		$.get('addpersonsusp.html', $('#form-person').serialize(), function(xml) {
      $('#dialog_person .btn-save').removeProp('disabled');
      $('#idperson').html('');
      $(xml).find("personsuspups").each(function(){
				var id = $(this).find("id").text(),
					stupin = $(this).find("stupin").text(),
					fullname = $(this).find("fullnamepersons").text(),
					fullperson = "<option value='"+id+"'>" + stupin + " " + fullname + "</option>";
				$("#idperson").append(fullperson);
			});
      $('#dialog_person #hidden-vyshkil').remove();
      $("#dialog_person .notcorrect").empty().hide();
      $('#dialog_person').modal('hide');
		});
	}
}

function saveStanycya(){
  $('#dialog_stanyt .btn-save').prop('disabled', 'true');

  var stan = $('#stan').val().trim().toLowerCase(),
		characterReg = /^\s*[а-яА-ЯїЇіІєЄ"'a-zA-Z0-9,\-\s]+\s*$/,
		checkAlready = false;

	$('#idselstan>option').each(function(index, val) {
		if ($(this).text().trim().toLowerCase() === stan) {
			checkAlready = true;
			return false;
		}
	});
	$('#idselstanp>option').each(function(index, val) {
		if ($(this).text().trim().toLowerCase() === stan) {
			checkAlready = true;
			return false;
		}
	});

	if (checkAlready) {
		isSubmitting('#dialog_stanyt', "Така станиця є в списку!");
	} else {
		if (!stan.length || !characterReg.test(stan)){
			isSubmitting('#dialog_stanyt', "Поля повинні бути обов\'язково коректно заповнені!");
		} else {
			$('#idselstan').empty();
			$('#dialog_stanyt .notcorrect').empty();
			$.get('addnewstan.html', {'namestan': stan}, function(xml) {
        $('#dialog_stanyt .btn-save').removeProp('disabled');
        $(xml).find('stanytsya').each(function(){
					var id = $(this).find('id').text(),
						namestan = $(this).find('namestan').text(),
						stanytsya = '<option value="'+id+'">' + namestan + '</option>';
					$('#idselstan').append(stanytsya);
					$('#idselstanp').append(stanytsya);
				});
			});
			$('#dialog_stanyt').modal('hide');
		}
	}
}

function saveKurinUSP(){
  $('#dialog_kurin_uspups .btn-save').prop('disabled', 'true');
  var namekurinusp = $("#idkurinuspups").val().trim(),
		checkAlready = false;

	$("#selkurinusp>option").each(function(index, el) {
		if ($(this).text().trim() === namekurinusp) {
      isSubmitting('#dialog_kurin_uspups', "Такий курінь вже є в списку!");
      checkAlready = true;
			return false;
		}
	});

	if(!checkAlready) {
		if(namekurinusp == ""){
      isSubmitting('#dialog_kurin_uspups', "Поле не може бути порожнім!");
    } else if(!characterReg.test(namekurinusp)){
      isSubmitting('#dialog_kurin_uspups', "Введіть коректне ім'я куреня!");
    } else {
			$("#selkurinusp").empty();
			$.get('kurinuspups.html', {namekurin: namekurinusp, idulad: 3 }, function(xml) {
        $('#dialog_kurin_uspups .btn-save').removeProp('disabled');
        $(xml).find("kurinuspups").each(function(){
					var id = $(this).find("id").text();
					var namekurin = $(this).find("namekurin").text();
					var kurinuspups = "<option value='"+id+"'>" + namekurin + "</option>";
					$("#selkurinusp").append(kurinuspups);
				});
			});
			$('#dialog_kurin_uspups').modal('hide');
		}
	}
}

function showUpdateVporydnyk(){
	var vporyadnyk = $("#idvporyadnyk").val(),
		datebegzv = $(".datebegvp").text().trim();
	$("#datestartvp").val(datebegzv);
	$("#about_vporydnyk").modal();
}

function updateDateVporyadnyk(){
	var data = {
		kurinorsmhurt: 1,
		samhurtokid: $('#hurtokid').val(),
		datestart: $('#about_vporydnyk #datestartvp').val().trim(),
		idperson: $('#idvpor_person').val(),
		addupdate: 2
	}
	$.get('addnewvporyadnyk.html', data, function(result) {
		$('#about_vporydnyk').modal('hide');
		window.location.reload();
	});
}

function saveVporyadnyk() {
  $('#dialog_vporyadnyk .btn-save').prop('disabled', 'true');

  var today = new Date();
  var dateEnd = $('#vpdateto').val().split('.');
  var dateStart = $('#vpdatefrom').val().split('.');

  if (!$('#vpdateto').val().length && $('#addvporyadnyk').attr('data-change') == 'true'){
		isSubmitting('#dialog_vporyadnyk', "Заповніть кінцеву дату попереднього впорядника !");
	} else if (!rgexpDate.test($('#vpdateto').val()) && $('#addvporyadnyk').attr('data-change') == 'true'){
		isSubmitting('#dialog_vporyadnyk', "Заповніть КОРЕКТНО кінцеву дату попереднього впорядника !");
	} else if (today < new Date(dateEnd[2], dateEnd[1] - 1, dateEnd[0])) {
    isSubmitting('#dialog_vporyadnyk', "Кінцева дата не може бути більша за сьогоднішню!");
  } else if (!$('#vpdatefrom').val().length){
		isSubmitting('#dialog_vporyadnyk', "Заповніть початкову дату впорядника !");
	} else if (today < new Date(dateStart[2], dateStart[1] - 1, dateStart[0])) {
    isSubmitting('#dialog_vporyadnyk', "Початкова дата не може бути більша за сьогоднішню!");
  } else if (!rgexpDate.test($('#vpdatefrom').val())){
		isSubmitting('#dialog_vporyadnyk', "Заповніть КОРЕКТНО початкову дату впорядника !");
	} else if  (!$('#vpdateto').val().length && ($('#vpdatefrom').val() < $('#vpdateto').val())){
		isSubmitting('#dialog_vporyadnyk', "Дата закінчення має бути меншою за дату початку !");
	} else if ($('#idperson').val() == 0){
		isSubmitting('#dialog_vporyadnyk', "Виберіть зі списку впорядника !");
	} else {
		if ($('input[name=addupdate]').val() == 0) {
			$('#vpdateto').removeAttr('name');
		}
		$('input[name=group]').removeAttr('name');

		$.get('addnewvporyadnyk.html', $('#form_vporyadnyk').serialize(), function(xml) {
      $('#dialog_vporyadnyk .btn-save').removeProp('disabled');
      $('#dialog_vporyadnyk').modal('hide');
			window.location.reload();
		});
	}
}

function changeStupin() {
	var data = { ulad: $(this).val()};

	var that = $(this).closest('.modal-body').find(".notcorrect");
	if (data.ulad == 0) {
		$(that).empty();
		$(that).append("Виберіть улад!");
	} else {
		$(".selstupinp").empty();
		$.get('selectstupin.html', data, function(xml) {
			$(xml).find("stupin").each(function() {
				var id = $(this).find("id").text();
				var namestupin = $(this).find("namestupin").text();
				var stupin = "<option value='" + id	+ "'>" + namestupin	+ "</option>";
				$(".selstupinp").append(stupin);
			});
		});
	}
}

function saveUnak(){
  $('#dialog_unak .btn-save').prop('disabled', 'true');

  var data = {
		lastname: $("#lastname").val().trim(),
		firstname: $("#firstname").val().trim(),
		datebirthday: $("#datebirthday").val().trim(),
		phonenumber: $("#phonenumber").val().trim(),
		email: $("#email").val().trim(),
		dateregistr: $("#dateregistr").val().trim(),
		datesworn: $("#datesworn").val().trim(),
		address: $("#addressYu").val().trim(),
		education: $("#ideducation").val().trim(),
		update: $("#change").text().trim(),
		personid: $("#change").val(),
	 	stanid: $("#idstan").val(),
	 	samhurtokid: $('#hurtokid').val(),
	 	kurinorsmhurt: 1
	}
	data.personid = (data.personid == undefined) ? '' : data.personid;

  var today = new Date();
  var dateBirthday = data.datebirthday.split('.');
  var dateRegister = data.dateregistr.split('.');
  var dateSworn = data.datesworn.split('.');

	if (!data.lastname.length || !characterReg.test(data.lastname)){
		isSubmitting('#dialog_unak', "Заповніть коректно прізвище!");
	} else if (!data.firstname.length || !characterReg.test(data.firstname)){
		isSubmitting('#dialog_unak', "Заповніть коректно ім'я!");
	} else if (!data.datebirthday.length || !rgexpDate.test(data.datebirthday)){
		isSubmitting('#dialog_unak', "Заповніть коректно дату народження!");
	} else if (today < new Date(dateBirthday[2], dateBirthday[1] - 1, dateBirthday[0])) {
    isSubmitting('#dialog_unak', "Дата народження не може бути більша за сьогоднішню!");
  } else if(!data.phonenumber.length || !regexpnumber.test(data.phonenumber)){
		isSubmitting('#dialog_unak', "Заповніть коректно номер телефону!");
	} else if(!data.email.length || !regexpemail.test(data.email)){
		isSubmitting('#dialog_unak', "Заповніть коректно email!");
	} else if(!data.dateregistr.length || !rgexpDate.test(data.dateregistr)){
		isSubmitting('#dialog_unak', "Заповніть коректно дату вступу!");
	} else if (today < new Date(dateRegister[2], dateRegister[1] - 1, dateRegister[0])) {
    isSubmitting('#dialog_unak', "Дата вступу не може бути більша за сьогоднішню!");
  } else if(!data.address.length ){
		isSubmitting('#dialog_unak', "Заповніть коректно адресу проживання!");
	} else if(!data.education.length){
		isSubmitting('#dialog_unak', "Заповніть місце навчання!");
	} else if(data.datesworn.length && !rgexpDate.test(data.datesworn)){
		isSubmitting('#dialog_unak', "Заповніть коректно дату заприсяження!");
	} else if (today < new Date(dateSworn[2], dateSworn[1] - 1, dateSworn[0])) {
    isSubmitting('#dialog_kurin', "Дата заприсяження не може бути більша за сьогоднішню!");
  } else {
		$("#dialog_unak .notcorrect").empty().hide();
		$.get('addpersons.html', data, function(xml) {
      $('#dialog_unak .btn-save').removeProp('disabled');
      if (!data.update.trim().length){
				window.location.reload();
			} else {
				$(xml).find("person").each(function(){
					var out = '<table class="table table-border"><tbody>';
						out += '<tr><th align="left">Прізвище</th><td>' + $(this).find("lastname").text() + '</td></tr>';
						out += '<tr><th align="left">Ім\'я</th><td>' + $(this).find("firstname").text() + '</td></tr>';
						out += '<tr><th align="left">Дата народження</th><td>' + $(this).find("birthday").text() + '</td></tr>';
						out += '<tr><th align="left">Місце проживання</th><td>' + $(this).find("address").text() + '</td></tr>';
						out += '<tr><th align="left">Місце навчання</th><td>' + $(this).find("education").text() + '</td></tr>';
						out += '<tr><th align="left">Телефон</th><td>' + $(this).find("phone").text() + '</td></tr>';
						out += '<tr><th align="left">e-mail</th><td>' + $(this).find("email").text() + '</td></tr>';
						out += '</tbody></table>';
						out += '<button id="change" class="btn btn-sm btn-cancel" value="' + $(this).find("id").text() + '"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Редагувати</button>';
					$("#commoninfor .panel-body").html(out);

					$("#lastname").val(data.lastname);
					$("#firstname").val(data.firstname);
					$("#datebirthday").val(data.birthday);
					$("#addressYu").val(data.address);
					$("#ideducation").val(data.education);
					$("#phonenumber").val(data.phone);
					$("#email").val(data.email);
					$("#datesworn").val(data.datesworn);
					$("#idselhurt").val(data.hurtok);
					$("#dateregistr").val(data.startdate);

					$('#dialog_unak').modal('hide');
				});
			}
		});
	}
}

function showDetailUnak() {
	idperson = $(this).val();

	if (idperson != 0) {
		$.get('yunakdetail.html', {idperson: idperson, kurinorsmhurt: 1}, function(xml) {

			var datesworn = '';
			$(xml).find("person").each(function(){
				if ($(this).find("datesworn").text().length) {
					datesworn = $(this).find("datesworn").text();
				}
				var out = '<table class="table table-border"><tbody>';
				out += '<tr><th align="left">Прізвище</th><td>' + $(this).find("lastname").text() + '</td></tr>';
				out += '<tr><th align="left">Ім\'я</th><td>' + $(this).find("firstname").text() + '</td></tr>';
				out += '<tr><th align="left">Дата народження</th><td>' + $(this).find("birthday").text() + '</td></tr>';
				out += '<tr><th align="left">Місце проживання</th><td>' + $(this).find("address").text() + '</td></tr>';
				out += '<tr><th align="left">Місце навчання</th><td>' + $(this).find("education").text() + '</td></tr>';
				out += '<tr><th align="left">Телефон</th><td>' + $(this).find("phone").text() + '</td></tr>';
				out += '<tr><th align="left">e-mail</th><td>' + $(this).find("email").text() + '</td></tr>';
				out += '</tbody></table>';
				out += '<button id="change" class="btn btn-sm btn-cancel" value="' + $(this).find("id").text() + '"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Редагувати</button>';
				$("#commoninfor .panel-body").html(out);
			});

			/* PEREHID PO STUPENYAH */
			var out = '';
			if (datesworn.length) {
				out += '<div class="form-group"><div id="iddatesworn"><lable>Дата заприсяження: <b>' + datesworn + '</b> </lable></div></div>';
			}
			out += '<table class="table table-border"><tbody>';
			out += '<tr><th align="left">дата з</th><th align="left">дата по</th><th align="left">ступінь</th><th align="left">змінити</th></tr>';
			$(xml).find("changestupin").each(function(){
				out += '<td>' + $(this).find("begindate").text() + '</td>';
				out += '<td>' + $(this).find("enddate").text() + '</td>';
				out += '<td>' + $(this).find("stupin").text() + '</td>';
				out += '<td><button value="' + $(this).find("id").text() + '" class="changestup btn btn-xs btn-cancel"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> змінити</button></td></tr>';
			});
			out += '</tbody></table>';

			$("#stupinperson .panel-body").html(out);
			$("#stupinperson .panel-body").append('<button value="' + idperson + '"" id="addstupin" class="btn btn-sm btn-black">Додати</button>');

			//Діловеденя в гуртку
			var out = '<table class="table table-border"><tbody>';
				out += '<tr><th align="left">дата з</th><th align="left">дата по</th><th align="left">діловедення</th><th align="left">змінити</th></tr>';
			$(xml).find("dilovodhurtok").each(function(){
				out += '<tr><td>' + $(this).find("begindate").text() + '</td>';
				out += '<td>' + $(this).find("enddate").text() + '</td>';
				out += '<td>' + $(this).find("dilovedennya").text() + '</td>';
				out += '<td><button value="' + $(this).find("id").text() + '" class="changedilovod btn btn-xs btn-cancel"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> змінити</button></td></tr>';
			});
			out += '</tbody></table>';
			$("#dilovod_hurtok .panel-body").html(out);
			$("#dilovod_hurtok .panel-body").append('<button value="' +idperson+'" id="adddilovodhurt" class="btn btn-sm btn-black">Додати</button>');
		});
		$("#dialog_unak_detail").modal();
	}
}

function showEditUnak(){
	idperson = $(this).val();

	if (idperson != 0) {
		$.get('yunakdetail.html', {idperson: idperson, kurinorsmhurt: 1}, function(xml) {
			$(xml).find("person").each(function(){
				$("#dialog_unak #lastname").val($(this).find("lastname").text());
				$("#dialog_unak #firstname").val($(this).find("firstname").text());
				$("#dialog_unak #datebirthday").val($(this).find("birthday").text());
				$("#dialog_unak #addressYu").val($(this).find("address").text());
				$("#dialog_unak #ideducation").val($(this).find("education").text());
				$("#dialog_unak #phonenumber").val($(this).find("phone").text());
				$("#dialog_unak #email").val($(this).find("email").text());
				$("#dialog_unak #datesworn").val($(this).find("datesworn").text()).datepicker();
				$("#dialog_unak #idselhurt").val($(this).find("hurtok").text());
				$("#dialog_unak #dateregistr").val($(this).find("startdate").text()).datepicker();
			});

			$("#dialog_unak").modal();
		});
	}
}

var changebutton, changestupin;
function addStupin() {
  selectulad(2);
  changestupin = -1;
	$(".verifidate").html('<p class="help-block red\">При додаванні нового ступеня перевірте чи попередній ступінь є ЗАКРИТИМ!</p>');
	$("#dialog_move_stupin").modal();
}

function saveMoveStupin(){
  $('#dialog_move_stupin .btn-save').prop('disabled', 'true');

  var data = {
		idperson: $("#addstupin").val(),
		begindate: $("#datebegstup").val(),
		enddate: $("#dateendstup").val(),
		ulad: $("#unakulad").val(),
		stupin: $("#unakstupin").val(),
		changestupin: changestupin,
		changebutton: -1
	};

  var today = new Date();
  var dateBegin = data.begindate.split('.');
  var dateEnd = data.enddate.split('.');

	if (!data.begindate.length){
  	isSubmitting('#dialog_move_stupin', "Дата не може бути порожньою!");
	} else if(!rgexpDate.test(data.begindate)){
		isSubmitting('#dialog_move_stupin', "Введіть коректно дату!");
	} else if (today < new Date(dateBegin[2], dateBegin[1] - 1, dateBegin[0])) {
    isSubmitting('#dialog_move_stupin', "Дата кінцеву не може бути більша за сьогоднішню!");
  } else if(data.enddate.length && !rgexpDate.test(data.enddate)){
		isSubmitting('#dialog_move_stupin', "Введіть коректно дату!");
	} else if (today < new Date(dateEnd[2], dateEnd[1] - 1, dateEnd[0])) {
    isSubmitting('#dialog_move_stupin', "Дата початку не може бути більша за сьогоднішню!");
  } else if(data.ulad == 0){
		isSubmitting('#dialog_move_stupin', "Виберіть УЛАД!");
	} else if (data.stupin == 0){
		isSubmitting('#dialog_move_stupin', "Виберіть Ступінь!");
	} else {
		$("#dialog_move_stupin .notcorrect").empty().hide();
		$.get('yunakstupinchange.html', data, function(xml) {
      $('#dialog_move_stupin .btn-save').removeProp('disabled');
      var out = '<table class="table table-border"><tbody>';
				out += '<tr><th align="left">дата з</th><th align="left">дата по</th><th align="left">ступінь</th><th align="left">змінити</th></tr>';

			$(xml).find("changestupin").each(function(){
				out += '<td>' + $(this).find("begindate").text() + '</td>';
				out += '<td>' + $(this).find("enddate").text() + '</td>';
				out += '<td>' + $(this).find("stupin").text() + '</td>';
				out += '<td><button value="' + $(this).find("id").text() + '" class="changestup btn btn-xs btn-cancel"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> змінити</button></td></tr>';
			});

			out += '</tbody></table>';
			$("#stupinperson .panel-body").html(out);
			$("#stupinperson .panel-body").append('<button value="' + idperson + '"" id="addstupin" class="btn btn-sm btn-black">Додати</button>');
			$(".verifidate").empty();
			$('#dialog_move_stupin').modal('hide');
		});
	}
}

function changeMoveStupin(){
  changestupin = $(this).val();
	$(".verifidate, #unakstupin").empty();

	$.get('yunakstupinchange.html', {changestupin: changestupin, changebutton: 0}, function(xml) {
		$(xml).find("stupins").each(function(){
			var id = $(this).find("id").text(),
				stupinname = $(this).find("stupinname").text(),
				allstupin = "<option value="+id+">"+stupinname+"</option>";
			$("#unakstupin").append(allstupin);
		});
		$(xml).find("onlyonestupin").each(function(){
			var id = $(this).find("id").text(),
				begindate = $(this).find("begindate").text(),
				enddate = $(this).find("enddate").text(),
				stupin = $(this).find("stupin").text(),
				ulad = $(this).find("ulad").text();
			$("#datebegstup").val(begindate);
			$("#dateendstup").val(enddate);
			$("#unakulad").val(ulad);
			$("#unakstupin").val(stupin);
		});
		$("#dialog_move_stupin").modal();
	});
}

function addDilovod(){
	idperson = $(this).val();
	changedilovod = -1;
	$(".verifidate").empty();
	$(".verifidate").html('<p class=\"help-block\">При додаванні нового діловедення перевірте чи попередній є ЗАКРИТИМ!</p>');
	$("#dialog_dilovod_hurtok").modal();
}

function saveDilovod(){
  $('#dialog_dilovod_hurtok .btn-save').prop('disabled', 'true');

  var data = {
		idperson: idperson,
		begindate: $("#datebegdildh").val(),
		enddate: $("#dateenddildh").val(),
		seldilhurt: $("#idseldilhurt").val(),
		changedilovod: changedilovod,
		changebutton: -1,
		kurinorsmhurt: 1,
		samhurtokid: $('#hurtokid').val()
	};

  var today = new Date();
  var dateBegin = data.begindate.split('.');
  var dateEnd = data.enddate.split('.');

	if (!data.begindate.length){
		isSubmitting('#dialog_dilovod_hurtok', "Заповніть дату початку!");
	} else if (!rgexpDate.test(data.begindate)){
		isSubmitting('#dialog_dilovod_hurtok', "Заповніть коректно дату початку!");
	} else if (today < new Date(dateBegin[2], dateBegin[1] - 1, dateBegin[0])) {
    isSubmitting('#dialog_dilovod_hurtok', "Дата початку не може бути більша за сьогоднішню!");
  } else if (data.enddate.length && !rgexpDate.test(data.enddate)){
		isSubmitting('#dialog_dilovod_hurtok', "Заповніть коректно дату кінця!");
	} else if (data.seldilhurt ==0){
		isSubmitting('#dialog_dilovod_hurtok', "Виберіть діловедення зі списку!");
	} else if (today < new Date(dateEnd[2], dateEnd[1] - 1, dateEnd[0])) {
    isSubmitting('#dialog_dilovod_hurtok', "Дата кінцеву не може бути більша за сьогоднішню!");
  } else {
		$("#dialog_dilovod_hurtok .notcorrect").empty().hide();

		$.get('yunakdilovodhurtchange.html', data, function(xml) {
      $('#dialog_dilovod_hurtok .btn-save').removeProp('disabled');
      var out = '<table class="table table-border"><tbody>';
				out += '<tr><th align="left">дата з</th><th align="left">дата по</th><th align="left">ступінь</th><th align="left">змінити</th></tr>';

			$(xml).find("dilovodhurtok").each(function(){
				out += '<td>' + $(this).find("begindate").text() + '</td>';
				out += '<td>' + $(this).find("enddate").text() + '</td>';
				out += '<td>' + $(this).find("dilovedennya").text() + '</td>';
				out += '<td><button value="' + $(this).find("id").text() + '" class="changedilovod btn btn-xs btn-cancel"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> змінити</button></td></tr>';
			});

			out += '</tbody></table>';
			$("#dilovod_hurtok .panel-body").html(out);
			$("#dilovod_hurtok .panel-body").append('<button value="' + idperson + '"" id="addstupin" class="btn btn-sm btn-black">Додати</button>');
			$(".verifidate").empty();
			$('#dialog_dilovod_hurtok').modal('hide');
		});
	}
}

function changeDilovod(){
	changedilovod = $(this).val();
	$(".verifidate").empty();
	$.get('yunakdilovodhurtchange.html', {changedilovod: changedilovod, changebutton: 0}, function(xml) {
		$(xml).find("dilovodhurtok").each(function(){
			$("#datebegdildh").val($(this).find("begindate").text());
			$("#dateenddildh").val($(this).find("enddate").text());
			$("#idseldilhurt").val($(this).find("dilovodid").text());
		});
		$("#dialog_dilovod_hurtok").modal();
	});
}

function saveUnakDetail(){
	window.location.reload();
	$('#dialog_unak_detail').modal('hide');
}
function isSubmitting(id, error) {
	  $(id + ' .btn-save').removeProp('disabled');
	  $(id + ' .notcorrect').html(error).show();
	}

function sendToVh() {
  if (!$('#idvporyadnyk').val()) {
    $('#dialog_with_error .modal-title').html('Повідомлення про помилку');
    $('#dialog_with_error .modal-body').html('Спершу потрібно додати впорядника гуртка.');
    $('#dialog_with_error').modal();
    return false;
  }
  if ($('#text_need').text().length) { $('#need').val($('#text_need').text()); }
  $('#form_need').modal();
}

function sendToAcceptVh() {
  const need = $('#need').val();
  if (need.length > 500) {
    $("#form_need .notcorrect").html("Текст не повинен перевищувати 500 символів!").show();
    return false;
  }
  $('#sendToAccept, #form_need .btn-send, #acceptData, #cancelData').prop('disabled', true);
  var dataForSend = {};
  if ($('#role').val() === 'hsm') {
    dataForSend = {
      smhurtokid: $('#hurtokid').val(),
      btn: 'sendToVh',
      need: need
    };
  } else if ($('#role').val() === 'vsm'){
    dataForSend = {
      smhurtokid: $('#hurtokid').val(),
      vporyadnykid: $('#idvporyadnyk').val(),
      btn: 'acceptDate',
      need: need
    };
  }
  $.get('quarterlyreport.html', dataForSend, function(data) {
    if (data.status == '1:acceptSmHurtok') {
      $('.status-label .mark').addClass('warning');
      $('.status-label .status-text').text('Очікується підтвердження');
    } else if (data.status == '3:acceptDateVP') {
      $('.status-label .mark').addClass('success').removeClass('warning');
      $('.status-label .status-text').text('Затверджено');
      $('.zvjazkovyy').hide();
    }
    $('#form_need').modal('hide');
  }, 'json');
}

function acceptData() {
  if ($('#text_need').text().length) {
    $('#form_need #label_need').html('Твій гуртковий вказав наступні потреби. Доповни, якщо потрібно.');
    $('#need').val($('#text_need').text());
  } else {
    $('#form_need #label_need').text('Твій гуртковий не вказав жодних потреб для гуртка. Якщо необхідно, то ти можеш вказати:');
  }
  $('#form_need').modal();
}

function cancelData() {
	$('#reason_cancel').modal('hide');
  $('#acceptData, #cancelData').prop('disabled', true);
  $.get('quarterlyreport.html', {
    smhurtokid: $('#hurtokid').val(),
    vporyadnykid: $('#idvporyadnyk').val(),
    btn: 'cancelDate',
    reason: $('#reason').val()
  }, function(data) {
    if (data.status == '2:cancelDateVP') {
      $('.status-label .mark').addClass('danger').removeClass('warning');
      $('.status-label .status-text').text('Відхилено ');
      $('.status-label .status-text').append('<span class="glyphicon glyphicon-info-sign info-reason" aria-hidden="true"></span>');
      const date = new Date();
      const nowDate = date.getDate().toString() + '.' + (date.getMonth()+1).toString() + '.' + date.getFullYear().toString();
      $('.table-reason tbody').append('<tr><td>' + nowDate +'</td><td>' + $('#reason').val() + '</td></tr>');
    }
  }, 'json');
}

function openReason() {
  $('#dialog_with_error .modal-title').html('Причини');
  $('#dialog_with_error .modal-body').html('').html($('.table-reason').clone().show());
  $('#dialog_with_error').modal();
}

function checkStatus() {
  if ($('#liststatus').val() == 0) {
    $('.status-label .status-text').text('Не прозвітовано');
    $('#acceptData').prop('disabled', true);
    $('#cancelData').prop('disabled', true);
  } else if ($('#liststatus').val() == 1) {
    $('.status-label .mark').addClass('warning');
    $('.status-label .status-text').text('Очікується підтвердження');
    $('#sendToAccept').prop('disabled', true);
  } else if ($('#liststatus').val() == 2) {
    $('.status-label .mark').addClass('danger');
    $('.status-label .status-text').text('Відхилено ');
    $('.status-label .status-text').append('<span class="glyphicon glyphicon-info-sign info-reason" aria-hidden="true"></span>');
    $('#acceptData').prop('disabled', true);
    $('#cancelData').prop('disabled', true);
  } else if ($('#liststatus').val() == 3) {
    $('.status-label .mark').addClass('success');
    $('.status-label .status-text').text('Затверджено');
    $('#sendToAccept').prop('disabled', true);
    $('#acceptData').prop('disabled', true);
    $('#cancelData').prop('disabled', true);
  } else {
    $('.status-label .status-text').text('Не прозвітовано');
    $('#acceptData').prop('disabled', true);
    $('#cancelData').prop('disabled', true);
	}
}

function checkRole(){
  if ($('#liststatus').val() != 3) {
    if ($('#role').val() === 'hsm') {
      $('.kurinnyj').show();
    } else if ($('#role').val() === 'vsm') {
      $('.zvjazkovyy').show();
    }
  }
}