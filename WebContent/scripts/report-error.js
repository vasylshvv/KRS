$(document).ready(function() {
	$('#dialog_report_error').on('click', '.btn-save', sendComplaint);

  $("#menu").css({ 'visibility': 'visible', 'height': 'auto' });
  $("#menu").mmenu({
		navbar: { title: 'Меню'}
	}, {
		offCanvas: {
			pageSelector: "#wrapper"
		}
	});
	var $icon = $("#menu-icon");
	var API = $("#menu").data( "mmenu" );

	$("#hamburger").click(function() {
		API.open();
	});

	API.bind( "open:finish", function() {
		setTimeout(function() {
			$icon.addClass( "is-active" );
		}, 100);
	});
	API.bind( "close:finish", function() {
		setTimeout(function() {
			$icon.removeClass( "is-active" );
		}, 100);
	});
});

function sendComplaint(){
  $('#dialog_report_error .btn-save').prop('disabled', 'true');
  var data = new FormData();
	data.append('sfullname', $('#report_fullname').val().trim());
	data.append('email', $('#report_email').val().trim());
	data.append('textrequest', $('#report_text').val().trim());
	data.append('file', $('#report_file')[0].files[0]);

	var characterReg = /^\s*[а-яА-ЯїЇіІєЄ"'a-zA-Z0-9,\-\s]+\s*$/,
		regexpemail = /[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/i;

	if (!$('#report_fullname').val().trim().length) {
		isSubmitting('#dialog_report_error', "Ви не ввели ім\'я та прізвище!");
	} else if (!characterReg.test($('#report_fullname').val().trim())) {
		isSubmitting('#dialog_report_error', "Введіть коректне ім\'я та прізвище!");
	} else if (!$('#report_email').val().trim().length) {
		isSubmitting('#dialog_report_error', "Ви не ввели email!");
	} else if (!regexpemail.test($('#report_email').val().trim())) {
		isSubmitting('#dialog_report_error', "Введіть коректний email!");
	} else if (!$('#report_text').val().trim().length) {
		isSubmitting('#dialog_report_error', "Ви не ввели повідомлення!");
	} else if ($('#report_text').val().trim().length > 500) {
		isSubmitting('#dialog_report_error', "Максимальна к-сть символів в повідомленні - 500!");
	} else {
		$('#dialog_report_error .notcorrect').empty().hide();
		$.ajax({
			url: 'addrequest.html',
			data: data,
			processData: false,
			contentType: false,
			type: 'POST',
			success: function(result){
        $('#dialog_report_error').modal('hide');
				if (result.trim() == 'success') {
					$('#dialog_report_text .modal-body').html('Дякуємо! Твоє звернення успішно надіслане.');
				} else {
					$('#dialog_report_text .modal-body').html('Упс! Щось пішло не так :( <br> Спробуй ще раз.');
        }
        $('#dialog_report_error .btn-save').removeProp('disabled');
				$('#report_file').val('');
				$('.name-file').text('');
				$('#dialog_report_text').modal();
			}
		});
	}
}

