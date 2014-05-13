jQuery(document).ready(function($){
	var files;
	var listId   = 0;
	var fileName = "";
	
	function getURLParameter(sParam)
	{
	    var sPageURL = window.location.search.substring(1);
	    var sURLVariables = sPageURL.split('&');
	    for (var i = 0; i < sURLVariables.length; i++) 
	    {
	        var sParameterName = sURLVariables[i].split('=');
	        if (sParameterName[0] == sParam) 
	        {
	            return sParameterName[1];
	        }
	    }
	    return null;
	}
	
	$('body.importcsv').on('change', 'input[type=file]', function(e) {
		files = e.target.files;
	});
	
	// Upload file
	$('body.importcsv').on('submit', '#uploadCsv', function(e){
		e.preventDefault();
		if (getURLParameter("list") != null) {
			listId = getURLParameter("list");
		}
		var formData = new FormData();
		// Check file size (20Mo max)
		if (files[0].size > 20971520) {
			$('.alert-danger').html("<h4>Erreur!</h4><p>Le fichier est trop volumineux</p>");
			$('.alert-danger').removeClass('hidden').show();
		} else {
			formData.append(0, files[0]);
			$.ajax({
				url:         $('#uploadCsv').attr('action'),
				type:        $('#uploadCsv').attr('method'),
				data:        formData,
				cache:       false,
				dataType:    'json',
				processData: false, // Don't process the files
				contentType: false, // Set content type to false as jQuery will tell the server its a query string request
			})
			.done(function(datas) {
				if (datas['msg'] == 'OK') {
					console.log("headers: "); console.log(datas['headers']);
					$('.content-examples').html(datas['contentExamples']);
					fileName = datas['file'];
				} else {
					$('.alert-danger').html('<h4>Erreur!<h4><p>' + datas['msg'] + '</p>');
					$('.alert-danger').show();
				}
			})
			.fail(function(datas) {
				$('.alert-danger').html('<h4>Erreur!<h4><p>Une erreur s\'est produite. Veuillez réessayer</p>');
				$('.alert-danger').show();
			});
		}
		return false;
	});
	
	// Run the import
	$('body.importcsv').on('submit', '#importCsv', function(e){
		e.preventDefault();
		var form = $('#importCsv');
		var name = getURLParameter('name');
		var headers = [];
		$('select.contentpreview-headers').each(function() {
			headers.push($(this).val());
		});

		$.ajax({
			url:         form.attr('action'),
			type:        form.attr('method'),
			data:        "headers=" + headers + "&file=" + fileName + "&list=" + listId + "&name=" + name,
			dataType:    'json',
		})
		.done(function(datas) {
			if (datas['msg'] == 'OK') {
				if (datas['warning'] != null) {
					$('.alert-warning').removeClass('hidden').html('<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>');
					$.each(datas['warning'].replace('[', '').replace(']', '').split(','), function(index, value) {
						$('.alert-warning').append(value + "<br />");
					});
					$('.alert-warning').show();
				}
				
				$('.alert-success').removeClass('hidden').show();
			} else {
				$('.alert-danger').removeClass('hidden').html('<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button><h4>Erreur!<h4><p>' + datas['msg'] + '</p>');
				$('.alert-danger').show();
			}
		})
		.fail(function(datas) {
			$('.alert-danger').removeClass('hidden').html('<h4>Erreur!<h4><p>Une erreur s\'est produite. Veuillez réessayer</p>');
			$('.alert-danger').show();
		});
	});
});