$(document).ready(function(){
	$("#adddilvhur").on('click', function(){
		$("#dialog_dilovedennya_hurtka").modal();
	});

	$('body').on('click', '#dialog_dilovedennya_hurtka .btn-save', function(){
		var namedilhurt = $("#namediloved").val().trim(),
			characterReg = /^\s*[а-яА-ЯїЇіІєЄ"'a-zA-Z0-9,\-\s]+\s*$/,
			i = 0,
			txt;
		var values = $("#idseldilhurt>option").map(function() {
			txt = $(this).text();
				if(txt.trim().toLowerCase() == namedilhurt.trim().toLowerCase()){
					i++;
				}
		});
		if(i>0) {
			$("#dialog_dilovedennya_hurtka .notcorrect").empty();
			$("#dialog_dilovedennya_hurtka .notcorrect").append("<p id=\"notcor\">Таке діловедення вже є в списку!</p>").show();
		} else {
			if(namedilhurt == "" || !characterReg.test(namedilhurt)){
				$("#dialog_dilovedennya_hurtka .notcorrect").empty();
				$("#dialog_dilovedennya_hurtka .notcorrect").append("<p id=\"notcor\">Поля повинні бути обов'язково заповнені коректно!</p>").show();
			} else {
				$("#idseldilhurt").empty();
				$.ajax({
					type: "get",
					url:  "dilovedennya.html",
					data: "namehurtokdil="+namedilhurt,
					cach: false,
					success: function(xml){
						$(xml).find("dilovod").each(function(){
							var id = $(this).find("id").text();
							var namedilovod = $(this).find("namedilovod").text();
							var alldilovod = "<option value="+id+">"+namedilovod+"</option>";
							$("#idseldilhurt").append(alldilovod);
						});

					}
				});
				$('#dialog_dilovedennya_hurtka').modal('hide');
			}
		}
	})

	$("#adddilvkur").on('click', function(){
		$("#dialog_dilovedennya_kurin").modal();
	})

	$('body').on('click', '#dialog_dilovedennya_kurin .btn-save', function(){
		var namedilkur = $("#namedilovedk").val().trim();
		var characterReg = /^\s*[а-яА-ЯїЇіІєЄ"'a-zA-Z0-9,\-\s]+\s*$/;
		var i = 0;
		var txt;
		var values = $("#idseldilkurin>option").map(function() {
			txt = $(this).text();
				if(txt.trim().toLowerCase() == namedilkur.trim().toLowerCase()){
					i++;
				}
		});
		if(i>0) {
			$("#dialog_dilovedennya_kurin .notcorrect").empty();
			$("#dialog_dilovedennya_kurin .notcorrect").append("<p id=\"notcor\">Таке діловедення вже є в списку!</p>").show();
		} else {
			if(namedilkur == "" || !characterReg.test(namedilkur)){
				$("#dialog_dilovedennya_kurin .notcorrect").empty();
				$("#dialog_dilovedennya_kurin .notcorrect").append("<p id=\"notcor\">Поля повинні бути обов'язково заповнені коректно!</p>").show();
			} else {
				$("#idseldilkurin").empty();
				$.ajax({
					type: "get",
					url:  "dilovedennya.html",
					data: "namekurindil="+namedilkur,
					cach: false,
					success: function(xml){
						$(xml).find("dilovodkurennya").each(function(){
							var id = $(this).find("id").text();
							var namedilovod = $(this).find("namedilovodkurin").text();
							var alldilovod = "<option value="+id+">"+namedilovod+"</option>";
							$("#idseldilkurin").append(alldilovod);
						});

					}
				});
				$('#dialog_dilovedennya_kurin').modal('hide');
			}
		}
	});
});