$(document).ready(function(){

	$.fn.datepicker.defaults.language = 'ua';
	var date = new Date(),
		today = date.getDate() + '.' + (date.getMonth() + 1) + '.' + date.getFullYear();
	$('.datepicker').datepicker({format: "dd.mm.yyyy", autoclose: true});
	$('.datepicker').datepicker('setDate', today);

	$(".clickdetailkurin").click(function(){
    	$("#detailkurin input[name=detail]").val($(this).val());
			$("#detailkurin").submit();
	});

  $(".clickdetailsamhurt").click(function(){
    $("#detailsamhurt input[name=detail]").val($(this).val());
    $("#detailsamhurt").submit();
  });

	if (!$('#zvfullname').text().trim().length) {
		$('#detailzvyazkovyy').hide();
	}

	$('.vp ').each(function () {
		if ($(this).find('.value').text().trim().length) {
			$(this).next('.btns').hide();
    }
  });
  $('body').on('click', '.showDialog', showDialog);

  $('.input-group-addon').on('click', function () { $(this).closest('.input-group').find('input').focus(); });

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
			var table = "<table border='1'>";
			var tableclose = "</table>";
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
			$("#commoninfor").empty();
			$("#stupinperson").empty();
			$("#dilovod_hurtok").empty();
			$("#dilovod_kurin").empty();
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

						//var buttonchange = "<button id='change' value='"+id+"'>Змінити</button>";
						//var commoninfo = "<p>"+lastname+" "+firstname+"</p>";
						$("#commoninfor").append(commoninfo);//+"<br><br>"+buttonchange);
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
						$("#iddatesworn").append("<lable>Дата заприсяження:<b>"+datesworn+"</b> </lable>");
					});

					// Перехід по ступенях
					var tablestupin =
						tr + th + "дата з" + thclose +
						 th + "дата по" + thclose +
						 th + "ступінь" + thclose +
						// th + "змінити" + thclose +
					trclose;
					$(xml).find("changestupin").each(function(){
						var id = $(this).find("id").text();
						var begindate = $(this).find("begindate").text();
						var enddate = $(this).find("enddate").text();
						var stupin = $(this).find("stupin").text();

						//var butchangestup = "<button value="+id+" class='changestup'>змінити</button>";

					allstupin = allstupin+
							tr + td + begindate + tdclose +
							     td + enddate + tdclose +
							     td + stupin + tdclose +
							    // td + butchangestup + tdclose +
							trclose;
					//$("#stupinperson").append(allstupin);

					});
					var stupinpersons = table + tablestupin + allstupin + tableclose;

					$("#stupinperson").append(stupinpersons);//+"<br><button value='"+idperson+"' id='addstupin'>додати</button>");


					//Діловеденя в гуртку
					var tabledilivod = tr + th + "дата з" + thclose +
				        th + "дата по" + thclose +
				        th + "діловедення" + thclose +
				      //  th + "змінити" + thclose +
				        trclose;
					$(xml).find("dilovodhurtok").each(function(){
						var id = $(this).find("id").text();
						var begindate = $(this).find("begindate").text();
						var enddate = $(this).find("enddate").text();
						var hurtokid = $(this).find("hurtokid").text();
						var namehurtok = $(this).find("namehurtok").text();
						var personid = $(this).find("personid").text();
						var kurinid = $(this).find("kurinid").text();
						var dilovedennyaid = $(this).find("dilovedennyaid").text();
						var dilovedennya = $(this).find("dilovedennya").text();

					//	var butchangedilovod = "<button value="+id+" class='changedilovod'>змінити</button>";

						alldilovod = alldilovod + tr +
									 td + begindate + tdclose+
									 td + enddate + tdclose+
									 td + dilovedennya + tdclose+
						//			 td + butchangedilovod + tdclose+
									 trclose
					});
					var dilovodhurtok = table + tabledilivod + alldilovod + tableclose;
					$("#dilovod_hurtok").append(dilovodhurtok);//+"<br><button value='"+idperson+"' id='adddilovodhurt'>додати</button>");

					//Діловеденя в курені

					var tabledilivodkur = tr + th + "дата з" + thclose +
			        th + "дата по" + thclose +
			        th + "діловедення" + thclose +
			     //   th + "змінити" + thclose +
			        trclose;

					$(xml).find("dilovodkurin").each(function(){
						var id = $(this).find("id").text();
						var begindate = $(this).find("begindate").text();
						var enddate = $(this).find("enddate").text();
						var personid = $(this).find("personid").text();
						var kurinid = $(this).find("kurinid").text();
						var dilovedennyaid = $(this).find("dilovedennyaid").text();
						var dilovedennya = $(this).find("dilovedennya").text();

					//	var butchangedilovod = "<button value="+id+" class='changedilovodkur'>змінити</button>";

						alldilovodkur = alldilovodkur + tr +
									 td + begindate + tdclose+
									 td + enddate + tdclose+
									 td + dilovedennya + tdclose+
							//		 td + butchangedilovod + tdclose+
									 trclose
					});

					var dilovodkurin = table + tabledilivodkur + alldilovodkur + tableclose;
					$("#dilovod_kurin").append(dilovodkurin);//+"<br><button value='"+idperson+"' id='adddilovodkurin'>додати</button>");
				}

			});
		}

	//	alert(idperson);

		$("#dialog_unak_detail").modal();

	});


  function showDialog(){
    var selector = $(this).attr('data-selector');
    $(selector).modal();
  }

});