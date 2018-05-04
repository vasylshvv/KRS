var idpersonusp;
$(document).ready(function(){

	$('body').on('click', '#dialog_unak .btn-save', saveUnak);
	function saveUnak(){
    $('#dialog_unak .btn-save').prop('disabled', 'true');
    var stanid = $("#idstan").val(),
			kurinid = $("#kurinid").val(),
			idselhurt = $("#idselhurt").val(),
			lastname = $("#lastname").val().trim(),
			firstname = $("#firstname").val().trim(),
			datebirthday = $("#datebirthday").val().trim(),
			phonenumber = $("#phonenumber").val().trim(),
			email = $("#email").val().trim(),
			dateregistr = $("#dateregistr").val().trim(),
			datesworn = $("#datesworn").val().trim(),
			selectulad = $("#selectulad").val(),
			idselstupin = $("#idselstupin").val(),
			address = $("#addressYu").val().trim(),
			education = $("#ideducation").val().trim();

    var today = new Date();
    var dateBirthday = datebirthday.split('.');
    var dateRegistr = dateregistr.split('.');
    var dateSworn = datesworn.split('.');

		var characterReg = /^\s*[а-яА-ЯїЇіІєЄ"'a-zA-Z0-9,\-\s]+\s*$/,
			characterReg1 = "";///^\s*[а-яА-ЯїЇіІєЄ"'/a-zA-Z0-9,\s]+\s*$/,
    		rgexpDate = /(^(((0[1-9]|1[0-9]|2[0-8])[-/.](0[1-9]|1[012]))|((29|30|31)[-/.](0[13578]|1[02]))|((29|30)[-/.](0[4,6,9]|11)))[-/.](19|[2-9][0-9])\d\d$)|(^29[-/.]02[-/.](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)/,
    		regexpemail = /[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/i,
			regexpnumber = /^38\(\d{3}\)\d{3}-\d{2}-\d{2}$/;

		var update = $("#change").text().trim();
		var personid,
			pers = $("#change").val();

		personid = (pers == undefined) ? '' : pers;

		if(idselhurt == 0) {
      isSubmitting('#dialog_unak', "Виберіть назву гуртка!");
		} else if (lastname == "" || !characterReg.test(lastname)){
      isSubmitting('#dialog_unak', "Прізвище може містити тільки букви, цифри, дефіс та пробіли!");
    } else if (firstname == "" || !characterReg.test(firstname)){
      isSubmitting('#dialog_unak', "Ім'я може містити тільки букви, цифри, дефіс та пробіли!");
    } else if (datebirthday == "" || !rgexpDate.test(datebirthday)){
      isSubmitting('#dialog_unak', "Заповніть коректно дату народження!");
    } else if (today < new Date(dateBirthday[2], dateBirthday[1] - 1, dateBirthday[0])) {
      isSubmitting('#dialog_unak', "Дата народження юнака не може бути більша за сьогоднішню!");
    } else if(phonenumber =="" || !regexpnumber.test(phonenumber)){
      isSubmitting('#dialog_unak', "Заповніть коректно номер телефону!");
    } else if(email == "" || !regexpemail.test(email)){
      isSubmitting('#dialog_unak', "Заповніть коректно email!");
    } else if(dateregistr == "" || !rgexpDate.test(dateregistr)){
      isSubmitting('#dialog_unak', "Заповніть коректно дату вступу!");
    } else if (today < new Date(dateRegistr[2], dateRegistr[1] - 1, dateRegistr[0])) {
      isSubmitting('#dialog_unak', "Дата вступу не може бути більша за сьогоднішню!");
    } else if(datesworn.length && !rgexpDate.test(datesworn)){
      isSubmitting('#dialog_unak', "Заповніть коректно дату заприсяження!");
    } else if (today < new Date(dateSworn[2], dateSworn[1] - 1, dateSworn[0])) {
      isSubmitting('#dialog_unak', "Дата заприсяження не може бути більша за сьогоднішню!");
    } else if(selectulad == 0){
      isSubmitting('#dialog_unak', "Виберіть улад!");
    } else if(idselstupin == 0){
      isSubmitting('#dialog_unak', "Виберіть ступінь!");
    } else {
			$("#dialog_unak .notcorrect").empty().hide();
			var table = "<table class='table table-border'><tbody>";
			var tableclose = "</tbody></table>";
			var tr = "<tr>";
			var trclose = "</tr>";
			var th = "<th align='left'>";
			var thclose = "</th>";
			var td = "<td>";
			var tdclose = "</td>";
			var allstupin = "";
			$("#commoninfor .panel-body").empty();
			$.ajax({
				type: "get",
				url:  "addpersons.html",
				data: "kurinid="+kurinid+
					  "&idselhurt="+idselhurt+
					  "&lastname="+lastname+
					  "&firstname="+firstname+
					  "&datebirthday="+datebirthday+
					  "&phonenumber="+phonenumber+
					  "&email="+email+
					  "&dateregistr="+dateregistr+
					  "&datesworn="+datesworn+
					  "&address="+address+
					  "&education="+education+
					  "&update="+update+
					  "&personid="+personid+
					  //"&stanid=3"+
					   "&stanid="+stanid+
					  "&kurinorsmhurt=0",
					  //+
					  //"&selectulad="+selectulad+
					  //"&idselstupin="+idselstupin,
				cach: false,
				success: function(xml){
          $('#dialog_unak .btn-save').removeProp('disabled');

          if(update==""){
						window.location.reload();
					} else {
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
							$('#dialog_unak').modal('hide');
						});
					}
				}
			});
		}
	}

	function addupdate(idperson){
    $('#dialog_person .btn-save').prop('disabled', 'true');
    var lastnamep = $("#lastnamep").val().trim();
		var firstnamep = $("#firstnamep").val().trim();
		var fullname = lastnamep+' '+firstnamep;

		var datebirthp = $("#datebirthdayp").val().trim();
		var selectuladp = $("#idselectuladp").val();
		var idselstupinp = $("#idselstupinp").val();
		var idselstanp = $("#idselstanp").val();
		var selkurinusp = $("#selkurinusp").val();
		var idstupkv = $("#idstupkv").val();

		var vyshkil = new Array();
        $('input[type=checkbox]:checked').each(function() {
        	vyshkil.push($(this).val());
        });

		var selvyshkiltabir = $("#idselvyshkiltab").val();
		var adressid = $("#adressid").val().trim();
		var phonenumberp = $("#phonenumberp").val();
		var emailp = $("#emailp").val().trim();
		var dateregistrp = $("#dateregistrp").val();
		var dateswornp = $("#dateswornp").val();
		var jobperson = $("#workid").val().trim();

    var today = new Date();
    var dateBirthday = datebirthp.split('.');
    var dateRegister = dateregistrp.split('.');
    var dateSworn = dateswornp.split('.');

		var characterReg = /^\s*[а-яА-ЯїЇіІєЄ"'a-zA-Z0-9,\-\s]+\s*$/;
		var characterReg1 = "";///^\s*[а-яА-ЯїЇіІєЄ"'/a-zA-Z0-9,\s]+\s*$/;
    	var rgexpDate = /(^(((0[1-9]|1[0-9]|2[0-8])[-/.](0[1-9]|1[012]))|((29|30|31)[-/.](0[13578]|1[02]))|((29|30)[-/.](0[4,6,9]|11)))[-/.](19|[2-9][0-9])\d\d$)|(^29[-/.]02[-/.](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)/;
    	var regexpemail = /[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/i;
		var regexpnumber = /^38\(\d{3}\)\d{3}-\d{2}-\d{2}$/;
		if(lastnamep == ""){
      isSubmitting('#dialog_person', "Заповніть прізвище!");
    } else if(!characterReg.test(lastnamep)){
			isSubmitting('#dialog_person', "Заповніть коректно прізвище!");
		} else if(firstnamep == ""){
			isSubmitting('#dialog_person', "Заповніть ім'я!");
		} else if(!characterReg.test(firstnamep)){
			isSubmitting('#dialog_person', "Заповніть коректно ім'я!");
		} else if(datebirthp == ""){
			isSubmitting('#dialog_person', "Заповніть дату народження!");
		} else if (today < new Date(dateBirthday[2], dateBirthday[1] - 1, dateBirthday[0])) {
      isSubmitting('#dialog_person', "Дата народження не може бути більша за сьогоднішню!");
    } else if(!rgexpDate.test(datebirthp)){
			isSubmitting('#dialog_person', "Заповніть коректно дату народження!");
		} else if(selectuladp == 0){
			isSubmitting('#dialog_person', "Виберіть улад!");
		} else if(idselstupinp == 0){
			isSubmitting('#dialog_person', "Виберіть ступінь!");
		} else if(idselstanp == 0){
			isSubmitting('#dialog_person', "Виберіть станицю!");
		} else if(adressid == ""){
			isSubmitting('#dialog_person', "Заповніть адресу!");
		} /*else if(!characterReg1.test(adressid)){
			     isSubmitting('#dialog_person', "Заповніть коректно адресу!");
		} */else if(jobperson == ""){
			isSubmitting('#dialog_person', "Заповніть місце праці або навчання!");
		} /*else if(!characterReg.test(jobperson)){
			     isSubmitting('#dialog_person', "Заповніть коректно місце праці або навчання!");
		} */else if(phonenumberp == 0){
			isSubmitting('#dialog_person', "Заповніть номер телефону!");
		} else if(!regexpnumber.test(phonenumberp)){
			isSubmitting('#dialog_person', "Заповніть коректно номер телефону!");
		} else if(emailp == ""){
			isSubmitting('#dialog_person', "Заповніть e-mail!");
		} else if(!regexpemail.test(emailp)){
			isSubmitting('#dialog_person', "Заповніть коректно e-mail!");
		} else if(dateregistrp == ""){
			isSubmitting('#dialog_person', "Заповніть дату вступу в Пласт!");
		} else if (today < new Date(dateRegister[2], dateRegister[1] - 1, dateRegister[0])) {
      isSubmitting('#dialog_person', "Дата вступу не може бути більша за сьогоднішню!");
    } else if(!rgexpDate.test(dateregistrp)){
			isSubmitting('#dialog_person', "Заповніть коректно дату вступу в Пласт!");
		} else if (today < new Date(dateSworn[2], dateSworn[1] - 1, dateSworn[0])) {
      isSubmitting('#dialog_person', "Дата заприсяження не може бути більша за сьогоднішню!");
    } else {
			$("#idperson").empty();
			$("#idpersonvp").empty();
			$.ajax({
				type: "get",
				url:  "addpersonsusp.html",
				data: "lastname="+lastnamep+
					  "&firstname="+firstnamep+
					  "&datebirth="+datebirthp+
					  "&selectulad="+selectuladp+
					  "&selectstupin="+idselstupinp+
					  "&stanytsya="+idselstanp+
					  "&selkurinusp="+selkurinusp+
					  "&stupinkv="+idstupkv+
					  "&vyshkil="+vyshkil+
					  "&selvyshkiltabir="+selvyshkiltabir+
					  "&address="+adressid+
					  "&phonenumber="+phonenumberp+
					  "&email="+emailp+
					  "&dateregistr="+dateregistrp+
					  "&datesworn="+dateswornp+
					  "&jobeducat="+jobperson+
					  "&idperson="+idperson,
				cach: false,
				success: function(xml){
					//window.location.reload();
          $('#dialog_person .btn-save').removeProp('disabled');
          $(xml).find("personsuspups").each(function(){
						var id = $(this).find("id").text();
						var stupin = $(this).find("stupin").text();
						var fullname = $(this).find("fullnamepersons").text();
						var fullperson = "<option value='"+id+"'>" + stupin + " " + fullname + "</option>";
						$("#idperson").append(fullperson);
						$("#idpersonvp").append(fullperson);

						$("#dialog_person .notcorrect").empty().hide();
						$('#dialog_person').modal('hide');
					})

				}
			});

		}
	}

	$(".addpersonsvykh").click(function(){
		idpersonusp = 0;
		$("#dialog_person").dialog("open");
	});

	$('body').on('click', '#dialog_person .btn-save', savePerson);

	function savePerson(){
		addupdate(idpersonusp);
	}

	$("#addkurinp").click(function(){
		$("#dialog_kurin").modal();
	});

	$('body').on('click', '#dialog_kurin .btn-save', function(){
		$('#dialog_kurin').modal('hide');
	});

	function detailperson(idperson){
		$.get('detailperson.html', {personid: idperson}, function(xml) {
			$(xml).find("person").each(function(){
				var id = $(this).find("id").text();
				var lastname = $(this).find("lastname").text();
				var firstname = $(this).find("firstname").text();
				var datebirth = $(this).find("datebirth").text();
				var idulad = $(this).find("uladid").text();
				var stupinid =$(this).find("stupinid").text();
				var stanytsyaid = $(this).find("stanytsyaid").text();
				var kurinuspid = $(this).find("kurinuspid").text();
				var kvid = $(this).find("kvid").text();
				var vyshkiltabirid = $(this).find("vyshkiltabirid").text();
				var adress = $(this).find("adress").text();
				var jobeduc = $(this).find("jobeduc").text();
				var phone = $(this).find("phone").text().trim();
				var email = $(this).find("email").text().trim();
				var datestart = $(this).find("datestart").text();
				var datesworn = $(this).find("datesworn").text();
				if(stanytsyaid == "null"){
					stanytsyaid = 0;
				}
				if(kurinuspid == "null"){
					kurinuspid = 0;
				}
				if(kvid == "null"){
					kvid = 0;
				}
				if(vyshkiltabirid == "null"){
					vyshkiltabirid = 0;
				}
				selectulad(idulad, stupinid);

				$("#lastnamep").val(lastname);
				$("#firstnamep").val(firstname);
				$("#datebirthdayp").val(datebirth);
				$("#idselectuladp").val(idulad);


				$("#idselstanp").val(stanytsyaid);
				$("#selkurinusp").val(kurinuspid);
				$("#idstupkv").val(kvid);
				$("#idselvyshkiltab").val(vyshkiltabirid);
				$("#adressid").val(adress);
				$("#workid").val(jobeduc);
				$("#phonenumberp").val(phone);
				$("#emailp").val(email);
				$("#dateregistrp").val(datestart);
				$("#dateswornp").val(datesworn);
			});
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
	}

	$("#detilzv").on("click",function(){
		var idperson = $(this).val();
		idpersonusp = idperson;
		$("#dialog_person").modal();
		detailperson(idperson);
	});

	$("#viewpers").click(function(){
		var idperson = $("#idperson").val();
		idpersonusp = idperson;
		if (idperson == 0){
			$("#dialog_vykhovnyk .notcorrect").empty();
			$("#dialog_vykhovnyk .notcorrect").append("<p id=\"notcor\">Виберіть зі списку!</p>").show();
		} else {
			$("#dialog_vykhovnyk .notcorrect").empty().hide();
			$('#dialog_person .selectuladp option[value=2]').prop('disabled', 'disabled');
      $('#dialog_person .selectuladp option[value=1]').prop('disabled', 'disabled');
			$("#dialog_person").modal();
			detailperson(idpersonusp);
		}
	});

	$('#addpersvp').on('click', function(){
		$('#dialog_person input').val('');
		$('#dialog_person select option:selected').removeAttr('selected');
    $('#dialog_person .selectuladp option[value=1]').prop('disabled', 'disabled');
	});

	$("#vpordetail").on("click",function(){
		var idperson = $(this).val();
		idpersonusp = idperson;
		$("#dialog_person").modal();
		detailperson(idperson);
	});

	$("#viewpersvp").on('click', function(){
    $('#dialog_person .selectuladp option[value=1]').prop('disabled', 'disabled');
    var idperson = $(this).closest('.modal-body').find('.idperson').val();
    idpersonusp = idperson;
		if(idperson == 0){
			$("#dialog_vporyadnyk .notcorrect").empty();
			$("#dialog_vporyadnyk .notcorrect").append("<p id=\"notcor\">Виберіть зі списку!</p>").show();
		} else {
			$("#dialog_vporyadnyk .notcorrect").empty();
			$("#dialog_person").modal();
			detailperson(idpersonusp);
		}
	});

	function selectulad(idulad, stupinid) {
		var stupin;
		if (idulad == 0) {
			$(".notcorrect").empty();
			$(".notcorrect").append("<p id=\"notcor\">Виберіть улад !</p>").show();
		} else {
			$("#idselstupinp").empty();
			$.ajax({
				type : "get",
				url : "selectstupin.html",
				data : "ulad=" + idulad,
				cach : false,
				success : function(xml) {
					$(xml).find("stupin").each(function() {
					var id = $(this).find("id").text();
					var namestupin = $(this).find("namestupin").text();
					if(id == stupinid){
						stupin = "<option value=" + id	+ " selected>" + namestupin	+ "</option>";;
					} else {
						stupin = "<option value=" + id	+ ">" + namestupin	+ "</option>";
					}

					$("#idselstupinp").append(stupin);

				});
				}
			});
		}

	}

});



