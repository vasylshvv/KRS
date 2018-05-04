$(document).ready(function(){
	$("#send").click(function(){
		alert(0);
		var id = $(this).val(); 
		var fromdata = new FromData();
		alert(id);
		//$("#form_test").submit(function(){
			//alert(1);
			$.ajax({
				url: "uploadFile.html?upload="+id,
			    cach: false,
			   // data: $("#form_test").submit(),
			    enctype: 'multipart/form-data',
			    processData: false,
			    contentType: false,
			    success: function(xml){						    	
			    //	window.location.reload();
			    }
			});
		//});
		//$("#form_test").submit();
		/*$.ajax({
			url: "uploadFile.html",
		    cach: false,
		    data: $("#form_add_hurtok").submit(),
		    enctype: 'multipart/form-data',
		    processData: false,
		    contentType: false,
		    success: function(xml){						    	
		    	window.location.reload();
		    }
		});*/
		alert(2);
	});
});