$(document).ready(function(){
	checkStatus();
  checkRole();

	$('body').on('change', '.selradio', chooseItem);

	/* KURIN */
	$('body').on('click', '#dialog_kurin .btn-save', saveKurin); /* add new */

	/* HURTOK */
	$('body').on('click', '#dialog_samo_hurtok .btn-save', saveHurtok); /* add new */

	/* REGISTER CODE */
	$("#formreg .selectkurin, #selecthurtok").on('change', showRegisterCode);
	$('body').on('click', '#continreg', continueReg);

	/* УСП УПС курені*/
	$('body').on('click', '#dialog_kurin_uspups .btn-save', saveKurinUSP);

	/* Send to zv */
	$('body').on('click', '#sendToAccept', sendToZv);
	$('body').on('click', '#form_need .btn-send', sendToAcceptZv);

  /* Work with data */
  $('body').on('click', '#acceptData', acceptData);
  $('body').on('click', '#cancelData', function(){ $('#reason_cancel').modal(); });
  $('body').on('click', '#form-reason-submit', cancelData);
  $('body').on('click', '.info-reason', openReason);
});

var characterReg = /^\s*[а-яА-ЯїЇіІєЄ"'a-zA-Z0-9,\-\s]+\s*$/,
	rgexpDate = /(^(((0[1-9]|1[0-9]|2[0-8])[-/.](0[1-9]|1[012]))|((29|30|31)[-/.](0[13578]|1[02]))|((29|30)[-/.](0[4,6,9]|11)))[-/.](19|[2-9][0-9])\d\d$)|(^29[-/.]02[-/.](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)/,
	regexpemail = /[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/i;

function showRegisterCode(){
	if ($(this).val() == '0') {
		$("#registercode").fadeOut();
		return false;
	}
	$("#registercode").fadeIn();
}

function chooseItem(){
	var kurinORsh = parseInt($(this).val()),
		value = $('#idselstan').val(),
		id_select = (!kurinORsh) ? '.selectkurin' : '#selecthurtok',
    id_select_revert = (kurinORsh) ? '.selectkurin' : '#selecthurtok',
    text = (!kurinORsh) ? 'Виберіть курінь' : 'Виберіть самостійний гурток',
  	text_revert = (kurinORsh) ? 'Виберіть курінь' : 'Виберіть самостійний гурток';

  $("#registercode").fadeOut();
	$('#codereg').val('');
	$(id_select).val('0').select2();

	if (!kurinORsh) {
		$('#listkurin').fadeIn('fast');
		$('#listsh').fadeOut('fast');
	} else {
		$('#listsh').fadeIn('fast');
		$('#listkurin').fadeOut('fast');
	}

	if ($(id_select).find('option').length == 1) {
		$(id_select).empty();
    $.get('addkurinupu.html', {'idselstan': value, 'kurinsh': kurinORsh}, function(result) {
      $(id_select).append('<option value="0">' + text + '</option>');
      $.each(result, function(index, val) {
        var name = '';
        if (!kurinORsh) {
          name = (val.number > 0 ? 'к. ч. ' + val.number : 'підг. к.') + ' ім. ' + val.namekurin;
        } else {
          name = val.namesamhurtok;
        }
        $(id_select).append('<option value="' + val.key + '">' + name + '</option>');
        $(id_select_revert).html('<option value="0">' + text_revert + '</option>');
      });
    }, 'json');
	}
}

function saveKurin(){
	$('#dialog_kurin .btn-save').prop('disabled', 'true');
	var kurinORsh = $(".selradio").val().trim(),
		stan = $("#idselstan").val().trim();

	var numbk = $("#numbk").val().trim(),
		sexk = $("#sexk").val().trim(),
		kurinname = $("#kurinname").val().trim(),
		dateregister = $("#dateregister").val().trim(),
		emailk = $("#emailk").val().trim(),
		checkAlready = false;

	$.each(window.kureni, function(index, val) {
    if (numbk != 0 && val.number == numbk) {
      isSubmitting('#dialog_kurin', "Таке число куреня вже заведено!");
      checkAlready = true;
      return false;
    } else if (val.namekurin.trim().toLocaleLowerCase() == kurinname.toLowerCase()) {
      isSubmitting('#dialog_kurin', "Таке ім'я куреня вже заведено!");
      checkAlready = true;
      return false;
    }
  });

  var today = new Date();
  var dateRegister = dateregister.split('.');

	if(!checkAlready){
		if(numbk == "-1"){
      isSubmitting('#dialog_kurin', "Обов'язково вибрати число куреня!");
		} else if(sexk == "-1"){
      isSubmitting('#dialog_kurin', "Обов'язково вибрати стать куреня!");
		} else if(kurinname == "" || !characterReg.test(kurinname)){
      isSubmitting('#dialog_kurin', "Введіть коректне ім'я куреня!");
		} else if(dateregister == "" || !rgexpDate.test(dateregister)) {
      isSubmitting('#dialog_kurin', "Введіть коректну дату реєстрації куреня!");
    } else if (today < new Date(dateRegister[2], dateRegister[1] - 1, dateRegister[0])) {
      isSubmitting('#dialog_kurin', "Дата реєстрації куреня не може бути більша за сьогоднішню!");
    } else if(emailk == "" || !regexpemail.test(emailk)){
      isSubmitting('#dialog_kurin', "Введіть коректний e-mail!");
		} else if (stan == 0) {
      isSubmitting('#dialog_kurin', "Ви не вибрали станицю!");
		} else {
			$("#dialog_kurin .notcorrect").empty().hide();
			$("#selectkurin").empty();
			var data = {
				'stan': stan,
				'numbk': numbk,
				'sexk': sexk,
				'kurinname': kurinname,
				'dateregister': dateregister,
				'emailk': emailk,
				'kurinsh': kurinORsh
			};
			$.post('addkurinupu.html', data, function(result) {
        $('#dialog_kurin .btn-save').removeProp('disabled');
        $('#selectkurin').append('<option value="0">Виберіть курінь</option>');
				$.each(result, function(index, val) {
					const name = (val.number > 0 ? 'к. ч. ' + val.number : 'підг. к.') + ' ім. ' + val.namekurin ;
          			$('#selectkurin').append('<option value="' + val.key + '">' + name + '</option>');
				});
        $('#dialog_kurin').modal('hide');
				$('#dialog_report_text .modal-body').html('Лист з кодовим словом відправлено вам на скриньку!');
				$('#dialog_report_text').modal();
			}, 'json');
		}
	}
}

function saveHurtok(){
  $('#dialog_samo_hurtok .btn-save').prop('disabled', 'true');
  var stan = $("#idselstan").val(),
		checkError = false;

  $('#selecthurtok>option').each(function() {
    if ($('#name_hurtok').val().trim().toLocaleLowerCase() == $(this).text().toLocaleLowerCase()) {
      isSubmitting('#dialog_samo_hurtok', "Таке ім'я гуртка вже заведено!");
      checkError = true;
      return false;
    }
  });

  var today = new Date();
  var dateRegister = $('#dateregister_hurtok').val().split('.');

  if (!checkError) {
		if(!$('#name_hurtok').val().trim().length || !characterReg.test($('#name_hurtok').val().trim())){
      isSubmitting('#dialog_samo_hurtok', "Введіть коректне ім'я гуртка!");
		} else if($('#sex_hurtok').val() == '-1'){
      isSubmitting('#dialog_samo_hurtok', "Обов'язково вибрати стать гуртка!");
		} else if(!$('#dateregister_hurtok').val().trim().length || !rgexpDate.test($('#dateregister_hurtok').val().trim())){
      isSubmitting('#dialog_samo_hurtok', "Введіть коректну дату реєстрації гуртка!");
		} else if (today < new Date(dateRegister[2], dateRegister[1] - 1, dateRegister[0])) {
      isSubmitting('#dialog_samo_hurtok', "Дата реєстрації гурта не може бути більша за сьогоднішню!");
    } else if(!$('#email_hurtok').val().trim().length || !regexpemail.test($('#email_hurtok').val().trim())) {
      isSubmitting('#dialog_samo_hurtok', "Введіть коректний e-mail!");
		} else {
			$("#dialog_samo_hurtok .notcorrect").empty().hide();
			$.post('addsamhurtokupu.html', $('#dialog_samo_hurtok form').serialize()+'&idstan='+stan, function(result) {
				$('#selecthurtok').html('');
				$('#selecthurtok').append('<option value="0">Виберіть самостійний гурток</option>');
				$.each(result, function(index, val) {
					$('#selecthurtok').append('<option value="' + val.key + '">' + val.namesamhurtok + '</option>');
				});
        $('#dialog_samo_hurtok .btn-save').removeProp('disabled');
        $('#dialog_samo_hurtok').modal('hide');
				$('#dialog_report_text .modal-body').html('Лист з кодовим словом відправлено вам на скриньку!');
				$('#dialog_report_text').modal();
			}, 'json');
		}
	}
}

function continueReg(e){
	e.preventDefault();
	var stan = $("#idselstan").val(),
		data = { registrcode: $("#codereg").val().trim() };

	if ($('.selradio:checked').val() == '0') {
		data.selkur = $("#selectkurin").val();
		$("#selecthurtok").val('0');
	} else if ($('.selradio:checked').val() == '1'){
		data.selsamhurt = $('#selecthurtok').val();
		$("#selectkurin").val('0');
	} else {
		$("#formreg .notcorrect").html("Обов'язково вибрати курінь або гурток!").show();
	}

	if (stan == 0){
		$("#formreg .notcorrect").html("Обов'язково вибрати станицю!").show();
	} else if ($('.selradio').val() == '0' && data.selkur == 0) {
		$("#formreg .notcorrect").html("Обов'язково вибрати курінь!").show();
	} else if ($('.selradio').val() == '1' && data.selsamhurt == 0) {
		$("#formreg .notcorrect").html("Обов'язково вибрати курінь!").show();
	} else if (data.registrcode == "") {
		$("#formreg .notcorrect").html("Кодове слово не може бути порожнє!").show();
	} else {
		$.get('verificregister.html', data, function(xml) {
			if (xml != 0){
				$("#formreg .notcorrect").empty().hide();
				var url = '';
				// var url = (xml == 1) ? 'registrkurupu.html' : 'registrsamhurt.html';
				switch (parseInt(xml)){
					case 1:
					case 3:
						url = 'registrkurupu.html';
						$('#parametr').val((xml == 1) ? 'k' : 'z');
						break;
					case 2:
					case 4:
            url = 'registrsamhurt.html';
            $('#parametr').val((xml == 2) ? 'hsm' : 'vsm');
            break;
				}
				$('#formreg').attr('action', url).submit();
			} else {
				$("#formreg .notcorrect").html("Не правильно введений код!").show();
			}
		});
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
      isSubmitting('#dialog_kurin_uspups', "Патрон куреня може містити тільки букви, цифри, дефіс та пробіли!");
		} else {
      $('#dialog_kurin_uspups .btn-save').removeProp('disabled');
      $("#selkurinusp").empty();
			$.get('kurinuspups.html', {namekurin: namekurinusp, idulad: 3 }, function(xml) {
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

function sendToZv() {
  if (!$('#idzvyaz').val()) {
    $('#dialog_with_error .modal-title').html('Повідомлення про помилку');
    $('#dialog_with_error .modal-body').html('Спершу потрібно додати зв\'язкового куреня.');
    $('#dialog_with_error').modal();
    return false;
  }
  if ($('#text_need').text().length) { $('#need').val($('#text_need').text()); }
    $('#form_need').modal();
}

function sendToAcceptZv() {
  const need = $('#need').val();
  if (need.length > 500) {
    $("#form_need .notcorrect").html("Текст не повинен перевищувати 500 символів!").show();
    return false;
	}
  $('#sendToAccept, #form_need .btn-send, #acceptData, #cancelData').prop('disabled', true);
  var dataForSend = {};
  if ($('#role').val() === 'k') {
    dataForSend = {
      kurinid: $('#kurinid').val(),
      btn: 'sendToZv',
      need: need
		};
  } else if ($('#role').val() === 'z'){
    dataForSend = {
      kurinid: $('#kurinid').val(),
      zvyazid: $('#idzvyaz').val(),
      btn: 'acceptDate',
      need: need
		};
	}
  $.get('quarterlyreport.html', dataForSend, function(data) {
    if (data.status == '1:acceptKurin') {
      $('.status-label .mark').addClass('warning');
      $('.status-label .status-text').text('Очікується підтвердження');
    } else if (data.status == '3:acceptDateZV') {
      $('.status-label .mark').addClass('success').removeClass('warning');
      $('.status-label .status-text').text('Затверджено');
      $('.zvjazkovyy').hide();
    }
    $('#form_need').modal('hide');
  }, 'json');
}

function acceptData() {
	if ($('#text_need').text().length) {
    $('#form_need #label_need').html('Твій курінний вказав наступні потреби. Доповни, якщо потрібно.');
    $('#need').val($('#text_need').text());
	} else {
    $('#form_need #label_need').text('Твій курінний не вказав жодних потреб для куреня. Якщо необхідно, то ти можеш вказати:');
  }
  $('#form_need').modal();
}

function cancelData() {
  const reason = $('#reason').val();
  if (reason.length > 500) {
    $("#reason_cancel .notcorrect").html("Текст не повинен перевищувати 500 символів!").show();
    return false;
  }
  $('#reason_cancel').modal('hide');
  $('#acceptData, #cancelData').prop('disabled', true);
  $.get('quarterlyreport.html', {
    kurinid: $('#kurinid').val(),
    zvyazid: $('#idzvyaz').val(),
    btn: 'cancelDate',
    reason: reason,
  }, function(data) {
    if (data.status == '2:cancelDateZV') {
      $('.status-label .mark').addClass('danger').removeClass('warning');
      $('.status-label .status-text').text('Відхилено ');
      $('.status-label .status-text').append('<span class="glyphicon glyphicon-info-sign info-reason" aria-hidden="true"></span>');
      const date = new Date();
      const nowDate = date.getDate().toString() + '.' + (date.getMonth()+1).toString() + '.' + date.getFullYear().toString();
      $('.table-reason tbody').append('<tr><td>' + nowDate +'</td><td>' + reason + '</td></tr>');
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
    if ($('#role').val() === 'k') {
      $('.kurinnyj').show();
    } else if ($('#role').val() === 'z') {
      $('.zvjazkovyy').show();
    }
  }
}