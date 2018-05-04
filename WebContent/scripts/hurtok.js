$(document).ready(function(){
	$('body').on('click', '#dialog_hurtok .btn-save', function() {
    $('#dialog_hurtok .btn-save').prop('disabled', 'true');

    var numbhurt = $("#numbh").val().trim();
		var kurinid = $("#kurinid").val();
		var datereg = $("#dateregister").val().trim();
		var dateend = $("#dateend").val().trim();
		var hurtokname = $("#hurtokname").val().trim();
		var vidznaka = $("#vidznaka").val();
		var characterReg = /^\s*[а-яА-ЯїЇіІєЄ"'a-zA-Z0-9,\-\s]+\s*$/;
		var rgexpDate = /(^(((0[1-9]|1[0-9]|2[0-8])[-/.](0[1-9]|1[012]))|((29|30|31)[-/.](0[13578]|1[02]))|((29|30)[-/.](0[4,6,9]|11)))[-/.](19|[2-9][0-9])\d\d$)|(^29[-/.]02[-/.](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)/;

    var today = new Date();
    var dateRegister = datereg.split('.');

		if(numbhurt == "" || numbhurt == 0){
      isSubmitting('#dialog_hurtok', "Число гуртка повинно бути обов'язково заповнено!");
    } else if(hurtokname == "" || !characterReg.test(hurtokname)){
			isSubmitting('#dialog_hurtok', "Назва гуртка може містити тільки букви, цифри, дефіс та пробіли!");
    } else if(datereg == "" || !rgexpDate.test(datereg)){
      isSubmitting('#dialog_hurtok', "Заповніть коректно дату створення гуртка гуртка!");
		} else if (today < new Date(dateRegister[2], dateRegister[1] - 1, dateRegister[0])) {
      isSubmitting('#dialog_hurtok', "Дата створення гуртка не може бути більша за сьогоднішню!");
    }else {
			var formData = new FormData();
            formData.append( 'vidznaka', $('input[type=file]')[0].files[0]);

			$.ajax({
				url: "addhurtokupu.html?numbhurt="+numbhurt+
					  "&kurinid="+kurinid+
					  "&datereg="+datereg+
					  "&dateend="+dateend+
					  "&hurtokname="+hurtokname,
			    cach: false,
			    data: formData,
			    type : 'POST',
			    enctype: 'multipart/form-data',
			    processData: false,
			    contentType: false,
			    success: function(xml){
            $('#dialog_hurtok .btn-save').removeProp('disabled');
            window.location.reload();
			    }
			});
			$(this).modal('hide');
		}
	});
});