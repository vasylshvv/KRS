$(document).ready(function(){

	/* GLOBAL FUNCTION */
	$.fn.datepicker.defaults.language = 'ua';
	$('.datepicker').datepicker({format: "dd.mm.yyyy", autoclose: true, allowInputToggle: true});
	$('.select2select').select2();
	$('body').on('click', '.showDialog', showDialog);
	$("#phonenumber").mask("38(999)999-99-99");
	$("#phonenumberp").mask("38(999)999-99-99");

	$('input[type=file]').on('change', function (e) {
		var file = e.target.files[0];
		$(this).closest('.input-group').find('.name-file').text(file.name);
  });

  $('.input-group-addon').on('click', function () { $(this).closest('.input-group').find('input').focus(); });

	/* STANYTSYA */
	$('#idselstan').change(function () {
		if ($('#idselstan').val() == "0") {
			$('#kurinupu, #listkurin, #listsh, #registercode').fadeOut();
			$('.selradio').removeProp('checked');
			$('#listkurin select, #listsh select, #registercode input').val('');
		} else {
			$('#registercode').fadeOut().find('input').val('');
			$("#kurinupu").fadeIn();
			value = $("#idselstan").val();
			var kurinORsh = $(".selradio:checked").val();
			if (kurinORsh == undefined || kurinORsh == 0) {
				getListKureniv(value, $(this).closest('#formreg'));
			} else {
				getListHurtky(value, $(this).closest('#formreg'));
			}
		}
	});

	$('body').on('click', '#dialog_stanyt .btn-save', saveStanycya); /* add new */

	function showDialog(){
		var selector = $(this).attr('data-selector');
		$(selector).modal();
	}

	function saveStanycya(){
    $('#dialog_stanyt .btn-save').prop('disabled', 'true');
    var stan = $('#stan').val().trim(),
			characterReg = /^\s*[а-яА-ЯїЇіІєЄ"'a-zA-Z0-9,\-\s]+\s*$/,
			checkAlready = false,
			checkValidation = false;

		$('#idselstan>option').each(function(index, val) {
			if ($(this).text().trim().toLowerCase() === stan.toLowerCase()) {
				checkAlready = true;
				return false;
			}
		});
		$('#idselstanp>option').each(function(index, val) {
			if ($(this).text().trim().toLowerCase() === stan.toLowerCase()) {
				checkAlready = true;
				return false;
			}
		});

		if ($('#selectokruha').val() === '0') checkValidation = true;

    if (checkValidation) {
      isSubmitting('#dialog_stanyt', "Потрібно вибрати округу!");
    } else if (checkAlready) {
      isSubmitting('#dialog_stanyt', "Така станиця є в списку!");
    } else {
			if (!stan.length || !characterReg.test(stan)){
        isSubmitting('#dialog_stanyt', "Поля повинні бути обов\'язково коректно заповнені!");
			} else {
				$('#idselstan').empty();
				$('#dialog_stanyt .notcorrect').empty();
				$.get('addnewstan.html', {'idokruha': $('#selectokruha').val(), 'namestan': stan}, function(xml) {
          $('#dialog_stanyt .btn-save').removeProp('disabled');
          $(xml).find('stanytsya').each(function(){
						var id = $(this).find('id').text(),
							namestan = $(this).find('namestan').text(),
							stanytsya = '<option value="'+id+'">' + namestan + '</option>';
						$('#idselstan').append(stanytsya);
						$('#idselstanp').append(stanytsya);
						$('#kurinupu, #listkurin, #listsh, #registercode').fadeOut();
						$('.selradio').removeProp('checked');
						$('#listkurin select, #listsh select, #registercode input').val('');
					});
				});
				$('#dialog_stanyt').modal('hide');
			}
		}
	}

	$('.seluladradio').on('change', changeUlad);
	$(".liststan").on('change', changeStan);
	$('.selectkurin').on('change', changeUpuKurin);

	function changeUlad(){
		var that = $(this).closest('.modal-body'),
			idselstan = $('.liststan').val();

		if ($(that).find('.seluladradio:checked').val() == '1'){
			$(that).find('.kurinUPU').show();

			if (idselstan != '0') {
				getListKureniv(idselstan, that);
				$(that).find('.idperson').html('<option value="0">Виберіть з списку</option>').select2();
			}

		} else {
			$(that).find('.kurinUPU').hide();

			if (idselstan != '0') {
				getListPersons(idselstan, '', that);
				$(that).find('.selectkurin').empty().append('<option value="0">Виберіть курінь</option>').select2();
			}
		}
	}

	function changeStan(){
		var that = $(this).closest('.modal-body'),
			idselstan = $(this).val();

		if (idselstan == '0') {
			$(that).find('.notcorrect').html('Потрібно вибрати станицю!').show();
			$(that).find('.selectkurin').empty().append('<option value="0">Виберіть курінь</option>');
			$(that).find('.idperson').html('<option value="0">Виберіть з списку</option>').select2();
		} else {
			$(that).find('.notcorrect').empty().hide();
			if ($(that).find('.seluladradio:checked').val() == '0') {
				getListPersons(idselstan, '', that);
			} else {
				getListKureniv(idselstan, that);
			}
		}
	}

	function changeUpuKurin(){
		var that = $(this).closest('.modal-body'),
			kurinUpu = $(this).val();

		if (kurinUpu == '0') {
			$(that).find('.notcorrect').html('Потрібно вибрати курінь!').show();
			$(that).find('.idperson').html('<option value="0">Виберіть з списку</option>').select2();
		} else {
			getListPersons(0, kurinUpu, that);
		}
	}

	function getListKureniv(idstan, that) {
		$.get('addkurinupu.html', {idselstan: idstan, kurinsh: 0}, function(result) {
			$(that).find('.selectkurin').empty().append('<option value="0">Виберіть курінь</option>');
			window.kureni = result;
			$.each(result, function(index, val) {
				const name = (val.number > 0 ? 'к. ч. ' + val.number : 'підг. к.') + ' ім. ' + val.namekurin ;
				$('.selectkurin').append('<option value="' + val.key + '">' + name + '</option>');
			});
		}, 'json');
	}

	function getListHurtky(idstan, that){
		$.get('addkurinupu.html', {'idselstan': value, 'kurinsh': 1}, function(result) {
			$(that).find('#selecthurtok').empty().append('<option value="0">Виберіть самостійний гурток</option>');
			$.each(result, function(index, val) {
				$(that).find('#selecthurtok').append('<option value="' + val.key + '">' + val.namesamhurtok + '</option>');
			});
		}, 'json');
	}

	function getListPersons(idstan, kurin, that) {
		if (idstan == '0') {
			var data = { kurinid: kurin};
		} else {
			var data = { idselstan: idstan};
		}

		$.get('selectstanpersons.html', data, function(xml) {
			$(that).find('.idperson').empty();
			$(xml).find("personsuspups").each(function(){
				var id = $(this).find("id").text(),
					fullnamepersons = $(this).find("fullnamepersons").text(),
					stupin = $(this).find("stupin").text();
				$(that).find('.idperson').append('<option value="' + id + '">' + stupin + ' ' + fullnamepersons + '</option>');
			});
		});
	}

	$(document).on('hidden.bs.modal', '.modal', function (e) {
		$('.modal:visible').length && $(document.body).addClass('modal-open');
		$(e.target).find('input[type=text], input[type=email], textarea').val('');
		$(e.target).find('input[type=checkbox]').removeProp('checked');
		$(e.target).find('select').val('0');
		$(e.target).find('select option').removeProp('disabled');
		$(e.target).find('.notcorrect').empty().hide();
		$(e.target).find('.datepicker').datepicker('update', '');
	});
});
function isSubmitting(id, error) {
  $(id + ' .btn-save').removeProp('disabled');
  $(id + ' .notcorrect').html(error).show();
}
