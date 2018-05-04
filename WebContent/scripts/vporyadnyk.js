$(document).ready(function(){
	var namebutton;
	$(".add_vporyad").on('click', function(){
		namebutton = $(this).text().trim();
		var idhurtok = $(this).val();
		if(namebutton == "Додати впорядника"){
			$("#vpdateto").closest('.form-group').fadeOut();
		} else {
			$("#vpdateto").closest('.form-group').fadeIn();
		}
		$("#dialog_vporyadnyk").modal();
		$("#idselhurtvporyad").val(idhurtok);
	});

	$('body').on('click', '#dialog_vporyadnyk .btn-save', function(){
    $('#dialog_vporyadnyk .btn-save').prop('disabled', 'true');

    var that = $(this).closest('.modal-dialog');
		var rgexpDate = /(^(((0[1-9]|1[0-9]|2[0-8])[-/.](0[1-9]|1[012]))|((29|30|31)[-/.](0[13578]|1[02]))|((29|30)[-/.](0[4,6,9]|11)))[-/.](19|[2-9][0-9])\d\d$)|(^29[-/.]02[-/.](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)/;

		var kurinupuid = $("#kurinid").val();
		var hurtokid = $("#idselhurtvporyad").val();
		var newvporyadnyk = $(that).find(".idperson").val();
		var olddatetovp = $(that).find("#vpdateto").val();
		var newdatefromvp = $(that).find("#vpdatefrom").val();
		var addupdate;

		if(olddatetovp!=""){
			addupdate = 1
		} else {
			addupdate = 0;
		}
		var fromdate = newdatefromvp.split(".");
		var todate = olddatetovp.split(".");

		fdate = new Date(fromdate[2], fromdate[1] - 1, fromdate[0]);
		tdate = new Date(todate[2], todate[1] - 1, todate[0]);
		var nfromdate = Number(fdate);
		var ntodate = Number(tdate);

    var today = new Date();

		if(hurtokid == 0){
			isSubmitting('#dialog_vporyadnyk', "Виберіть гурток зі списку !");
		} else if (olddatetovp=="" && namebutton=="Змінити впорядника"){
			isSubmitting('#dialog_vporyadnyk', "Заповніть кінцеву дату попереднього впорядника !");
		} else if(!rgexpDate.test(olddatetovp) && namebutton=="Змінити впорядника"){
			isSubmitting('#dialog_vporyadnyk', "Заповніть КОРЕКТНО кінцеву дату попереднього впорядника !");
		} else if(newdatefromvp==""){
			isSubmitting('#dialog_vporyadnyk', "Заповніть початкову дату впорядника !");
		} else if(!rgexpDate.test(newdatefromvp)){
			isSubmitting('#dialog_vporyadnyk', "Заповніть КОРЕКТНО початкову дату впорядника !");
		} else if (olddatetovp!= "" && (nfromdate < ntodate)){
			isSubmitting('#dialog_vporyadnyk', "Дата закінчення має бути меншою за дату початку !");
		} else if (today < new Date(fromdate[2], fromdate[1] - 1, fromdate[0])) {
      isSubmitting('#dialog_vporyadnyk', "Дата кінцеву не може бути більша за сьогоднішню!");
    } else if (today < new Date(todate[2], todate[1] - 1, todate[0])) {
      isSubmitting('#dialog_vporyadnyk', "Дата початку не може бути більша за сьогоднішню!");
    } else if(newvporyadnyk == 0){
			isSubmitting('#dialog_vporyadnyk', "Виберіть зі списку впорядника !");
		} else {
			$.ajax({
				type: "get",
				url:  "addnewvporyadnyk.html",
				data: "kurinorsmhurt=0"+
					  "&kurinupuid="+kurinupuid+
					  "&hurtokid="+hurtokid+
					  "&olddatetovp="+olddatetovp+
					  "&newdatefromvp="+newdatefromvp+
					  "&newvporyadnyk="+newvporyadnyk+
					  "&addupdate="+addupdate,
				cach: false,
				success: function(xml){
          $('#dialog_vporyadnyk .btn-save').removeProp('disabled');
          window.location.reload();
					$('#dialog_vporyadnyk').modal('hide');
				}
			});
		}
	});

	$(".info_vporyad").on('click', function(){
		var idhurtokvpor = $(this).val(),
			idperson = $(this).closest('.value').find('.personvp').val(),
			kurinupuid = $("#kurinid").val(),
			marker=-1;
		$.ajax({
			type: "get",
			url:  "addnewvporyadnyk.html",
			data: "kurinupuid="+kurinupuid+
				  "&hurtokid="+idhurtokvpor+
				  "&addupdate="+marker+
				  "&kurinorsmhurt=0"+
				  "&idperson="+idperson,
			cach: false,
			success: function(data){
				var allJson = jQuery.parseJSON(data.trim());
				$("#vpfull").text(allJson.fullname);
				$("#datestartvp").val(allJson.datebegin);
				$("#vpordetail").val(allJson.idperson);
				//alert(allJson.datebegin+ " "+allJson.idperson+" "+allJson.fullname);
			}
		});

		$("#about_sel_hurt_vp").val(idhurtokvpor);
		$("#about_vporydnyk").modal();
	});

	$('body').on('click', '#about_vporydnyk .btn-save', function(){
		var kurinupuid = $("#kurinid").val();
		var hurtokid = $("#about_sel_hurt_vp").val();
		var idperson = $("#vpordetail").val();
		var datestart = $("#datestartvp").val();
		var addupdate = 2;
		$.ajax({
			type: "get",
			url:  "addnewvporyadnyk.html",
			data: "kurinupuid="+kurinupuid+
				  "&hurtokid="+hurtokid+
				  "&datestart="+datestart+
				  "&idperson="+idperson+
				  "&kurinorsmhurt=0"+
				  "&addupdate="+addupdate,
			cach: false,
			success: function(xml){
				window.location.reload();
				$('#about_vporydnyk').modal('hide');
			}
		});
	});
});