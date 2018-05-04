function selectulad(idulad) {
  var alert = $(this).closest('.modal-body').find(".notcorrect");
  if (idulad == 0) {
    $(alert).empty();
    $(alert).append("<p id=\"notcor\">Виберіть улад !</p>");
  } else {
    $(".selstupinp").empty();
    $.ajax({
      type : "get",
      url : "selectstupin.html",
      data : "ulad=" + idulad,
      cach : false,
      success : function(xml) {
        $(xml).find("stupin").each(
          function() {
            var id = $(this).find("id").text();
            var namestupin = $(this).find("namestupin").text();
            var stupin = "<option value='" + id	+ "'>" + namestupin	+ "</option>";
            $(".selstupinp").append(stupin);
          });
      }
    });
  }
}

$(document).ready(function() {
	var selulad = $("#idselectuladp").val();

	$(".selectuladp").on('change', function() {
		var idulad = $(this).val();
		selectulad(idulad);
	});
});