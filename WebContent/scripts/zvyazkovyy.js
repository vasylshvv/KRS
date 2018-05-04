$(document).ready(function(){
	checkZvyazkovyy();

	$('#addzvyazkovyy').on('click', showAddZvyazkovyy); /* show Dialog */
	$('#addzvyazkovyymodal').on('click', showAddZvyazkovyyModal); /* show Dialog */
	$('body').on('click', '#dialog_vykhovnyk .btn-save', saveZvyazkovyy); /* add new */

	$("#updatezvyazkovyy").on("click",function(){
		var zvyzkovyy = $("#idzvyaz").val();
		var datebegzv = $("#datebegzv").text().trim();
		$("#datestartzv").val(datebegzv);
		$("#about_zvyaz").modal();
	});

	$('body').on('click', '#about_zvyaz .btn-save', saveAboutZvyaz);
});


function checkZvyazkovyy(){
	if (!$("#zvfullname").text().trim().length){
		$("#addzvyazkovyy").val("Додати зв'язкового");
		$('#updatezvyazkovyy').hide();
	} else {
		$("#addzvyazkovyy").val("Змінити зв'язкового");
	}
}

function showAddZvyazkovyy(){
	if (!$("#zvfullname").text().trim().length){
		$("#datetozv").closest('.form-group').fadeOut();
	} else {
		$("#datetozv").closest('.form-group').fadeIn();
	}
	$("#dialog_vykhovnyk").modal();
}

function showAddZvyazkovyyModal(){
	idpersonusp = 0;
	$('#dialog_person .selectuladp option[value=2]').prop('disabled', 'disabled');
	$('#dialog_person .selectuladp option[value=1]').prop('disabled', 'disabled');
	$("#dialog_person").modal();
}

function saveZvyazkovyy(){
  $('#dialog_vykhovnyk .btn-save').prop('disabled', 'true');

  var data = {
			kurinupuid: $("#kurinid").val(),
			olddateto: $("#zvdateto").val(),
			newdatefrom: $("#zvdatefrom").val(),
			newzvyazkovyy: $(".idperson").val(),
			oldzvyazkovyy: $("#idzvyaz").val()
		},
		zv = $("#addzvyazkovyy").val().trim();

	data.oldzvyazkovyy = (data.oldzvyazkovyy == undefined) ? 0 : data.oldzvyazkovyy;

	var rgexpDate = /(^(((0[1-9]|1[0-9]|2[0-8])[-/.](0[1-9]|1[012]))|((29|30|31)[-/.](0[13578]|1[02]))|((29|30)[-/.](0[4,6,9]|11)))[-/.](19|[2-9][0-9])\d\d$)|(^29[-/.]02[-/.](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)/;

	var fromdate = data.newdatefrom.split("."),
		todate = data.olddateto.split(".");

	fdate = new Date(fromdate[2], fromdate[1] - 1, fromdate[0]);
	tdate = new Date(todate[2], todate[1] - 1, todate[0]);
	var nfromdate = Number(fdate);
	var ntodate = Number(tdate);

  var today = new Date();

	if (!data.olddateto.length && zv == "Змінити зв'язкового"){
		isSubmitting('#dialog_vykhovnyk', "Виберіть кінцеву дату попереднього зв'язкового !");
	} else if(!rgexpDate.test(data.olddateto) && zv == "Змінити зв'язкового"){
		isSubmitting('#dialog_vykhovnyk', "Заповніть коректно кінцеву дату попереднього зв'язкового !");
	} else if(!data.newdatefrom.length){
		isSubmitting('#dialog_vykhovnyk', "Виберіть початкову дату нового зв'язкового !");
	} else if(!rgexpDate.test(data.newdatefrom)){
		isSubmitting('#dialog_vykhovnyk', "Виберіть коректно початкову дату нового зв'язкового !");
	} else if(data.olddateto.length && (nfromdate < ntodate)){
		isSubmitting('#dialog_vykhovnyk', "Дата закінчення має бути меншою за дату початку !");
	}  else if (today < new Date(fromdate[2], fromdate[1] - 1, fromdate[0])) {
    isSubmitting('#dialog_vporyadnyk', "Дата кінцеву не може бути більша за сьогоднішню!");
  } else if (today < new Date(todate[2], todate[1] - 1, todate[0])) {
    isSubmitting('#dialog_vporyadnyk', "Дата початку не може бути більша за сьогоднішню!");
  } else if(data.newzvyazkovyy == 0){
		isSubmitting('#dialog_vykhovnyk', "Виберіть зі списку зв'язкового !");
	} else {
		$.get('addnewzvyazkovyy.html', data, function(xml) {
      $('#dialog_vykhovnyk .btn-save').removeProp('disabled');
      window.location.reload();
			$('#dialog_vykhovnyk').modal('hide');
		});
	}
}

function saveAboutZvyaz(){
	var idzvyazkovyy = $("#idzvyaz").val(),
		datebegzv = $("#datestartzv").val().trim();

		$.get('addnewzvyazkovyy.html', {idzvyazkovyy: idzvyazkovyy, datebegzv: datebegzv}, function(xml) {
			window.location.reload();
		});

	$('#about_zvyaz').modal('hide');
}