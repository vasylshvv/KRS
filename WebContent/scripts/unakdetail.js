$(document).ready(function(){

	var idperson;
	var changestupin;
	var changedilovod;
	var changebutton;
	/*var begindatestup;
	var enddatestup;
	var uladstup;
	var stupinstup;
	*/

	$(".detailunak").on('click', function(){
		idperson = $(this).val();

		if(idperson!= 0 ){
			var table = "<table class='table table-border'><tbody>";
			var tableclose = "</tbody></table>";
			var tr = "<tr>";
			var trclose = "</tr>";
			var th = "<th align='left'>";
			var thclose = "</th>";
			var td = "<td>";
			var tdclose = "</td>";
			var allstupin = "";
			var alldilovod = "";
			var alldilovodkur = "";
			var dates=[];
			$("#commoninfor .panel-body").empty();
			$("#stupinperson").empty();
			$("#dilovod_hurtok .panel-body").empty();
			$("#dilovod_kurin .panel-body").empty();
			$("#iddatesworn").empty();
			$.ajax({
				type: "get",
				url:  "yunakdetail.html",
				data: "idperson="+idperson+"&kurinorsmhurt=0",
				cach: false,
				success: function(xml){
					// Основна інформація
					$(xml).find("person").each(function(){
						var id = $(this).find("id").text();
						var lastname = $(this).find("lastname").text();
						var firstname = $(this).find("firstname").text();
						var birthday = $(this).find("birthday").text();
						var address = $(this).find("address").text();
						var education = $(this).find("education").text();
						var phone = $(this).find("phone").text().trim();
						var email = $(this).find("email").text().trim();
						var datesworn = $(this).find("datesworn").text();
						var hurtok = $(this).find("hurtok").text();
						var startdate = $(this).find("startdate").text();

						var commoninfo = table +
								tr + th + "Прізвище"+ thclose+ td +" "+lastname+tdclose + trclose +
								tr + th + "Ім'я"+ thclose + td + " "+firstname+tdclose+trclose+
								tr + th + "Дата народження"+ thclose + td + " "+birthday + tdclose + trclose +
								tr + th + "Місце проживання"+ thclose + td + " "+address + tdclose + trclose +
								tr + th + "Місце навчання"+ thclose + td + " "+education + tdclose + trclose +
								tr + th + "Телефон"+ thclose + td + " "+phone + tdclose + trclose +
								tr + th + "e-mail"+ thclose + td + " "+email + tdclose + trclose +
										 tableclose;

						var buttonchange = "<button id='change' class='btn btn-sm btn-cancel' value='"+id+"'><span class='glyphicon glyphicon-pencil' aria-hidden='true'></span> Редагувати</button>";
						//var commoninfo = "<p>"+lastname+" "+firstname+"</p>";
						$("#commoninfor .panel-body").append(commoninfo+buttonchange);
						//$("#idselhurt").val(id);
						$("#lastname").val(lastname);
						$("#firstname").val(firstname);
						$("#datebirthday").val(birthday);
						$("#addressYu").val(address);
						$("#ideducation").val(education);
						$("#phonenumber").val(phone);
						$("#email").val(email);
						$("#datesworn").val(datesworn);
						$("#idselhurt").val(hurtok);
						$("#dateregistr").val(startdate);
						$("#iddatesworn").append("<lable>Дата заприсяження: <b>"+datesworn+"</b> </lable>");
					});

					// Перехід по ступенях
					var tablestupin =
						tr + th + "дата з" + thclose +
						 th + "дата по" + thclose +
						 th + "ступінь" + thclose +
						 th + "змінити" + thclose +
					trclose;
					$(xml).find("changestupin").each(function(){
						var id = $(this).find("id").text();
						var begindate = ($(this).find("begindate").text() !== 'null') ? $(this).find("begindate").text() : '-';
						var enddate = ($(this).find("enddate").text() !== 'null') ? $(this).find("enddate").text() : '-';
						var stupin = $(this).find("stupin").text();

						var butchangestup = "<button value="+id+" class='changestup btn btn-xs btn-cancel'><span class='glyphicon glyphicon-pencil' aria-hidden='true'></span> змінити</button>";

					allstupin = allstupin+
							tr + td + begindate + tdclose +
							     td + enddate + tdclose +
							     td + stupin + tdclose +
							     td + butchangestup + tdclose +
							trclose;
					//$("#stupinperson").append(allstupin);

					});
					var stupinpersons = table + tablestupin + allstupin + tableclose;

					$("#stupinperson").append(stupinpersons+"<button value='"+idperson+"' id='addstupin' class='btn btn-sm btn-black'>Додати</button>");


					//Діловеденя в гуртку
					var tabledilivod = tr + th + "дата з" + thclose +
				        th + "дата по" + thclose +
				        th + "діловедення" + thclose +
				        th + "змінити" + thclose +
				        trclose;
					$(xml).find("dilovodhurtok").each(function(){
						var id = $(this).find("id").text();
            var begindate = ($(this).find("begindate").text() !== 'null') ? $(this).find("begindate").text() : '-';
            var enddate = ($(this).find("enddate").text() !== 'null') ? $(this).find("enddate").text() : '-';
						var hurtokid = $(this).find("hurtokid").text();
						var namehurtok = $(this).find("namehurtok").text();
						var personid = $(this).find("personid").text();
						var kurinid = $(this).find("kurinid").text();
						var dilovedennyaid = $(this).find("dilovedennyaid").text();
						var dilovedennya = $(this).find("dilovedennya").text();

						var butchangedilovod = "<button value="+id+" class='changedilovod btn btn-xs btn-cancel'><span class='glyphicon glyphicon-pencil' aria-hidden='true'></span> змінити</button>";

						alldilovod = alldilovod + tr +
									 td + begindate + tdclose+
									 td + enddate + tdclose+
									 td + dilovedennya + tdclose+
									 td + butchangedilovod + tdclose+
									 trclose
					});
					var dilovodhurtok = table + tabledilivod + alldilovod + tableclose;
					$("#dilovod_hurtok .panel-body").append(dilovodhurtok+"<button value='"+idperson+"' id='adddilovodhurt' class='btn btn-sm btn-black'>Додати</button>");

					//Діловеденя в курені

					var tabledilivodkur = tr + th + "дата з" + thclose +
			        th + "дата по" + thclose +
			        th + "діловедення" + thclose +
			        th + "змінити" + thclose +
			        trclose;

					$(xml).find("dilovodkurin").each(function(){
						var id = $(this).find("id").text();
            var begindate = ($(this).find("begindate").text() !== 'null') ? $(this).find("begindate").text() : '-';
            var enddate = ($(this).find("enddate").text() !== 'null') ? $(this).find("enddate").text() : '-';
						var personid = $(this).find("personid").text();
						var kurinid = $(this).find("kurinid").text();
						var dilovedennyaid = $(this).find("dilovedennyaid").text();
						var dilovedennya = $(this).find("dilovedennya").text();

						var butchangedilovod = "<button value="+id+" class='changedilovodkur btn btn-xs btn-cancel'><span class='glyphicon glyphicon-pencil' aria-hidden='true'></span> змінити</button>";

						alldilovodkur = alldilovodkur + tr +
									 td + begindate + tdclose+
									 td + enddate + tdclose+
									 td + dilovedennya + tdclose+
									 td + butchangedilovod + tdclose+
									 trclose
					});
					var dilovodkurin = table + tabledilivodkur + alldilovodkur + tableclose;
					$("#dilovod_kurin .panel-body").append(dilovodkurin+"<button value='"+idperson+"' id='adddilovodkurin' class='btn btn-sm btn-black'>Додати</button>");
				}
			});
		}
		$("#dialog_unak_detail").modal();
	});

	$('body').on('click', '#dialog_unak_detail .btn-ok', saveUnakDetail);
	function saveUnakDetail(){
		window.location.reload();
		$('#dialog_unak_detail').modal('hide');
	}

	$('body').on('click','#change',function(){
		$("#dialog_unak").modal();
	});

	$('body').on('click','.changestup',function(){
		changestupin = $(this).val();
		changebutton = 0;
		$(".verifidate").empty();
		$("#unakstupin").empty();

		$.get('yunakstupinchange.html', {changestupin: changestupin, changebutton: changebutton}, function(xml) {
			$(xml).find("stupins").each(function(){
				var id = $(this).find("id").text();
				var stupinname = $(this).find("stupinname").text();
				var allstupin = "<option value="+id+">"+stupinname+"</option>";
				$("#unakstupin").append(allstupin);
			});
			$(xml).find("onlyonestupin").each(function(){
				var id = $(this).find("id").text();
        var begindate = ($(this).find("begindate").text() !== 'null') ? $(this).find("begindate").text() : '-';
        var enddate = ($(this).find("enddate").text() !== 'null') ? $(this).find("enddate").text() : '-';
				var stupin = $(this).find("stupin").text();
				var ulad = $(this).find("ulad").text();
				$("#datebegstup").val(begindate);
				$("#dateendstup").val(enddate);
				$("#unakulad").val(ulad);
				$("#unakstupin").val(stupin);
			});
			$("#dialog_move_stupin").modal();
		});

	});

	$('body').on('click','#addstupin',function(){
    selectulad(2);
		$(".verifidate").empty();
		$(".verifidate").append("<p id=\"notcor\" class=\"help-block red\">При додаванні нового ступеня перевірте чи попередній ступінь є ЗАКРИТИМ!</p>");
		changestupin = -1;
		changebutton = -1;
		$(".notcorrect").empty();

		$("#dialog_move_stupin").modal();
	});

	$('body').on('click', '#dialog_move_stupin .btn-save', saveMoveStupin);

	function saveMoveStupin(){
		var table = "<table class='table table-border'><tbody>",
			tableclose = "</tbody></table>",
			tr = "<tr>",
			trclose = "</tr>",
			th = "<th align='left'>",
			thclose = "</th>",
			td = "<td>",
			tdclose = "</td>",
			allstupin = "",
			ulad = $("#unakulad").val(),
			stupin = $("#unakstupin").val(),
			personid = $("#addstupin").val(),
			datefrom = $("#datebegstup").val(),
			
			dateto = $("#dateendstup").val(),
			rgexpDate = /(^(((0[1-9]|1[0-9]|2[0-8])[-/.](0[1-9]|1[012]))|((29|30|31)[-/.](0[13578]|1[02]))|((29|30)[-/.](0[4,6,9]|11)))[-/.](19|[2-9][0-9])\d\d$)|(^29[-/.]02[-/.](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)/;
			changebutton = -1;

			
			var df = toDate(datefrom);			
			var dt;
			if(dateto!=''){				
				dt = toDate(dateto);
			} else {
				dt = '';
			}
			
		
		if(datefrom == ""){
			$("#dialog_move_stupin .notcorrect").empty();
			$("#dialog_move_stupin .notcorrect").append("<p id=\"notcor\">Дата не може бути порожньою!</p>").show();
		} else if(!rgexpDate.test(datefrom)){
			$("#dialog_move_stupin .notcorrect").empty();
			$("#dialog_move_stupin .notcorrect").append("<p id=\"notcor\">Введіть коректно дату!</p>").show();
		} else if(dateto!="" && !rgexpDate.test(dateto)){
				$("#dialog_move_stupin .notcorrect").empty();
				$("#dialog_move_stupin .notcorrect").append("<p id=\"notcor\">Введіть коректно дату!</p>").show();
		} else if(ulad == 0){
			$("#dialog_move_stupin .notcorrect").empty();
			$("#dialog_move_stupin .notcorrect").append("<p id=\"notcor\">Виберіть УЛАД!</p>").show();
		} else if (stupin == 0) {
      $("#dialog_move_stupin .notcorrect").empty();
      $("#dialog_move_stupin .notcorrect").append("<p id=\"notcor\">Виберіть Ступінь!</p>").show();
    } else if (dt != '' && df > dt) {
      $("#dialog_move_stupin .notcorrect").empty();
      $("#dialog_move_stupin .notcorrect").append("<p id=\"notcor\">Початкова дата не може бути більше, ніж кінцева!</p>").show();
		} else {
			$("#stupinperson").empty();
			$.ajax({
				type: "get",
				url:  "yunakstupinchange.html",
				data: "idperson="+personid+
					  "&begindate="+datefrom+
					  "&enddate="+dateto+
					  "&ulad="+ulad+
					  "&stupin="+stupin+
					  "&changestupin="+changestupin+
					  "&changebutton="+changebutton,
				cach: false,
				success: function(xml){
					var tablestupin =
						tr + th + "дата з" + thclose +
						 th + "дата по" + thclose +
						 th + "ступінь" + thclose +
						 th + "змінити" + thclose +
					trclose;
					$(xml).find("changestupin").each(function(){
						var id = $(this).find("id").text();
            var begindate = ($(this).find("begindate").text() !== 'null') ? $(this).find("begindate").text() : '-';
            var enddate = ($(this).find("enddate").text() !== 'null') ? $(this).find("enddate").text() : '-';
						var stupin = $(this).find("stupin").text();

						var butchangestup = "<button value="+id+" class='changestup btn btn-xs btn-cancel'><span class='glyphicon glyphicon-pencil' aria-hidden='true'></span> змінити</button>";

					allstupin = allstupin+
							tr + td + begindate + tdclose +
							     td + enddate + tdclose +
							     td + stupin + tdclose +
							     td + butchangestup + tdclose +
							trclose;

					//$("#stupinperson").append(allstupin);

					});
					var stupinpersons = table + tablestupin + allstupin + tableclose;

					$("#stupinperson").append(stupinpersons+"<br><button class='btn btn-sm btn-black' value='"+idperson+"' id='addstupin'>додати</button>");

				}
			});
			$(".verifidate").empty();
			$('#dialog_move_stupin').modal('hide');
		}
	}
	//Діловедення

	$('body').on('click', '#adddilovodhurt',function(){
		idperson = $(this).val();
		changedilovod = -1;
		changebutton = -1;
		$(".verifidate").empty();
		$(".verifidate").append("<p id=\"notcor\" class=\"help-block\">При додаванні нового діловедення перевірте чи попередній є ЗАКРИТИМ!</p>");
		$("#dialog_dilovod_hurtok").modal();
	});

	$('body').on('click', '#dialog_dilovod_hurtok .btn-save', function(){
		var table = "<table class='table table-border'><tbody>",
			tableclose = "</tbody></table>",
			tr = "<tr>",
			trclose = "</tr>",
			th = "<th align='left'>",
			thclose = "</th>",
			td = "<td>",
			tdclose = "</td>",
			alldilovod = "",
			changebutton =-1;

		var datebegdildh = $("#datebegdildh").val(),
			dateenddildh = $("#dateenddildh").val(),
			idseldilhurt = $("#idseldilhurt").val(),
			rgexpDate = /(^(((0[1-9]|1[0-9]|2[0-8])[-/.](0[1-9]|1[012]))|((29|30|31)[-/.](0[13578]|1[02]))|((29|30)[-/.](0[4,6,9]|11)))[-/.](19|[2-9][0-9])\d\d$)|(^29[-/.]02[-/.](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)/;
		
		var dfdh = toDate(datebegdildh);			
		var dtdh;
		if(dateenddildh!=''){				
			dtdh = toDate(dateenddildh);
		} else {
			dtdh = '';
		}

		if(datebegdildh == ""){
			$("#dialog_dilovod_hurtok .notcorrect").empty();
			$("#dialog_dilovod_hurtok .notcorrect").append("<p id=\"notcor\">Заповніть дату початку!</p>").show();
		} else if(!rgexpDate.test(datebegdildh)){
			$("#dialog_dilovod_hurtok .notcorrect").empty();
			$("#dialog_dilovod_hurtok .notcorrect").append("<p id=\"notcor\">Заповніть коректно дату початку!</p>").show();
		} else if(dateenddildh!="" && !rgexpDate.test(dateenddildh)){
				$("#dialog_dilovod_hurtok .notcorrect").empty();
				$("#dialog_dilovod_hurtok .notcorrect").append("<p id=\"notcor\">Заповніть коректно дату кінця!</p>").show();
		} else if(idseldilhurt ==0){
			$("#dialog_dilovod_hurtok .notcorrect").empty();
			$("#dialog_dilovod_hurtok .notcorrect").append("<p id=\"notcor\">Виберіть діловедення зі списку!</p>").show();
    } else if (dtdh != '' && dfdh > dtdh) {
      $("#dialog_dilovod_hurtok .notcorrect").empty();
      $("#dialog_dilovod_hurtok .notcorrect").append("<p id=\"notcor\">Початкова дата не може бути більше, ніж кінцева!</p>").show();
		} else {
		var tabledilivod = tr + th + "дата з" + thclose +
						        th + "дата по" + thclose +
						        th + "діловедення" + thclose +
						        th + "змінити" + thclose +
						   trclose;
		$("#dilovod_hurtok .panel-body").empty();
			$.ajax({
				type: "get",
				url:  "yunakdilovodhurtchange.html",
				data: "idperson="+idperson+
					  "&begindate="+datebegdildh+
					  "&enddate="+dateenddildh+
					  "&seldilhurt="+idseldilhurt+
					  "&changedilovod="+changedilovod+
					  "&changebutton="+changebutton+
					  "&kurinorsmhurt=0",
				cach: false,
				success: function(xml){
					$(xml).find("dilovodhurtok").each(function(){
						var id = $(this).find("id").text();
            var begindate = ($(this).find("begindate").text() !== 'null') ? $(this).find("begindate").text() : '-';
            var enddate = ($(this).find("enddate").text() !== 'null') ? $(this).find("enddate").text() : '-';
						var hurtokid = $(this).find("hurtokid").text();
						var namehurtok = $(this).find("namehurtok").text();
						var personid = $(this).find("personid").text();
						var kurinid = $(this).find("kurinid").text();
						var dilovedennyaid = $(this).find("dilovedennyaid").text();
						var dilovedennya = $(this).find("dilovedennya").text();

						var butchangedilovod = "<button value="+id+" class='changedilovod btn btn-xs btn-cancel'><span class='glyphicon glyphicon-pencil' aria-hidden='true'></span> змінити</button>";

						alldilovod = alldilovod + tr +
									 td + begindate + tdclose+
									 td + enddate + tdclose+
									 td + dilovedennya + tdclose+
									 td + butchangedilovod + tdclose+
									 trclose
					});
					var dilovodhurtok = table + tabledilivod + alldilovod + tableclose;
					$("#dilovod_hurtok .panel-body").append(dilovodhurtok+"<button value='"+idperson+"' id='adddilovodhurt' class='btn btn-sm btn-black'>Додати</button>");

				}
			});
			$('#dialog_dilovod_hurtok').modal('hide');
		}

	});

	$('body').on('click',".changedilovod",function(){
		    changedilovod = $(this).val();
			changebutton = 0;
		$(".verifidate").empty();
		$.ajax({
			type: "get",
			url:  "yunakdilovodhurtchange.html",
			data: "changedilovod="+changedilovod+
				  "&changebutton="+changebutton,
			cach: false,
			success: function(xml){
				$(xml).find("dilovodhurtok").each(function(){
					var id = $(this).find("id").text();
          var begindate = ($(this).find("begindate").text() !== 'null') ? $(this).find("begindate").text() : '-';
          var enddate = ($(this).find("enddate").text() !== 'null') ? $(this).find("enddate").text() : '-';
					var dilovodid = $(this).find("dilovodid").text();
					$("#datebegdildh").val(begindate);
					$("#dateenddildh").val(enddate);
					$("#idseldilhurt").val(dilovodid);
				});

			}
		});
		$("#dialog_dilovod_hurtok").modal();
	});
	$('body').on('click','#adddilovodkurin',function(){
		idperson = $(this).val();
		changedilovod = -1;
		changebutton = -1;
		$(".verifidate").empty();
		$(".verifidate").append("<p id=\"notcor\" class=\"help-block\">При додаванні нового діловедення перевірте чи попередній є ЗАКРИТИМ!</p>");
		$("#dialog_dilovod_kurin").modal();
	});

	$('body').on('click', '#dialog_dilovod_kurin .btn-save', function(){
		var table = "<table class='table table-border'><tbody>",
			tableclose = "</tbody></table>",
			tr = "<tr>",
			trclose = "</tr>",
			th = "<th align='left'>",
			thclose = "</th>",
			td = "<td>",
			tdclose = "</td>",
			alldilovodkur = "",
			changebutton =-1,
			personid = idperson,
			datebegdildk = $("#datebegdildk").val(),
			dateenddildk = $("#dateenddildk").val(),
			idseldilkur = $("#idseldilkurin").val(),
			kurinid = $("#kurinid").val(),
			rgexpDate = /(^(((0[1-9]|1[0-9]|2[0-8])[-/.](0[1-9]|1[012]))|((29|30|31)[-/.](0[13578]|1[02]))|((29|30)[-/.](0[4,6,9]|11)))[-/.](19|[2-9][0-9])\d\d$)|(^29[-/.]02[-/.](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)/;
		
		var dfdk = toDate(datebegdildk);			
		var dtdk;
		if(dateenddildk!=''){				
			dtdk = toDate(dateenddildk);
		} else {
			dtdk = '';
		}
		
		if(datebegdildk == ""){
			$("#dialog_dilovod_kurin .notcorrect").empty();
			$("#dialog_dilovod_kurin .notcorrect").append("<p id=\"notcor\">Заповніть дату початку!</p>").show();
		} else if(!rgexpDate.test(datebegdildk)){
			$("#dialog_dilovod_kurin .notcorrect").empty();
			$("#dialog_dilovod_kurin .notcorrect").append("<p id=\"notcor\">Заповніть коректно дату початку!</p>").show();
		} else if(dateenddildk!="" && !rgexpDate.test(dateenddildk)){
			$("#dialog_dilovod_kurin .notcorrect").empty();
			$("#dialog_dilovod_kurin .notcorrect").append("<p id=\"notcor\">Заповніть коректно дату закінчення!</p>").show();
		} else if(idseldilkur == 0){
			$("#dialog_dilovod_kurin .notcorrect").empty();
			$("#dialog_dilovod_kurin .notcorrect").append("<p id=\"notcor\">Виберіть діловедення зі списку!</p>").show();
    } else if (dtdk != '' && dfdk > dtdk) {
      $("#dialog_dilovod_kurin .notcorrect").empty();
      $("#dialog_dilovod_kurin .notcorrect").append("<p id=\"notcor\">Початкова дата не може бути більше, ніж кінцева!</p>").show();
		} else {
			var tabledilivodkur = tr + th + "дата з" + thclose +
	        th + "дата по" + thclose +
	        th + "діловедення" + thclose +
	        th + "змінити" + thclose +
	        trclose;
			$("#dilovod_kurin .panel-body").empty();
			$.ajax({
				type: "get",
				url:  "yunakdilovodkurinchange.html",
				data: "idperson="+personid+
					  "&begindate="+datebegdildk+
					  "&enddate="+dateenddildk+
					  "&seldilkurin="+idseldilkur+
					  "&kurinid="+kurinid+
					  "&changedilovod="+changedilovod+
					  "&changebutton="+changebutton,
				cach: false,
				success: function(xml){
					$(xml).find("dilovodkurin").each(function(){
						var id = $(this).find("id").text(),
             	begindate = ($(this).find("begindate").text() !== 'null') ? $(this).find("begindate").text() : '-',
             	enddate = ($(this).find("enddate").text() !== 'null') ? $(this).find("enddate").text() : '-',
							personid = $(this).find("personid").text(),
							kurinid = $(this).find("kurinid").text(),
							dilovedennyaid = $(this).find("dilovedennyaid").text(),
							dilovedennya = $(this).find("dilovedennya").text();

						var butchangedilovod = "<button value="+id+" class='changedilovodkur btn btn-xs btn-cancel'><span class='glyphicon glyphicon-pencil' aria-hidden='true'></span> змінити</button>";

						alldilovodkur = alldilovodkur + tr +
									 td + begindate + tdclose+
									 td + enddate + tdclose+
									 td + dilovedennya + tdclose+
									 td + butchangedilovod + tdclose+
									 trclose
					});

					var dilovodkurin = table + tabledilivodkur + alldilovodkur + tableclose;
					$("#dilovod_kurin .panel-body").append(dilovodkurin+"<br><button class='btn btn-sm btn-black' value='"+idperson+"' id='adddilovodkurin'>Додати</button>");
				}
			});
			$('#dialog_dilovod_kurin').modal('hide');
		}
	});

	$('body').on('click', ".changedilovodkur",function(){
		changedilovod = $(this).val();
		changebutton = 0;
		$(".verifidate").empty();
		$.ajax({
			type: "get",
			url:  "yunakdilovodkurinchange.html",
			data: "changedilovod="+changedilovod+
				  "&changebutton="+changebutton,
			cach: false,
			success: function(xml){
				$(xml).find("dilovodkurin").each(function(){
					var id = $(this).find("id").text();
          var begindate = ($(this).find("begindate").text() !== 'null') ? $(this).find("begindate").text() : '-';
          var enddate = ($(this).find("enddate").text() !== 'null') ? $(this).find("enddate").text() : '-';
					var dilovodid = $(this).find("dilovodid").text();
					$("#datebegdildk").val(begindate);
					$("#dateenddildk").val(enddate);
					$("#idseldilkurin").val(dilovodid);
				});

			}
		});
		$("#dialog_dilovod_kurin").modal();
	});
});

function toDate(dateStr) {
    var parts = dateStr.split(".");
    return new Date(parts[2], parts[1] - 1, parts[0]);
}