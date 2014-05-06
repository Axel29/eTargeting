jQuery(document).ready(function($){
	var subscriberId = 0;
	$('body.subscribers').on('submit', '#add-subscriber', function(e){
		var form = $('#add-subscriber');
		// Prevent the form from reloading / changing the page
		e.preventDefault();
		console.log(form.serialize());
		// Insert new subscriber into database and refresh table's content 
		$.ajax({
			url:      form.attr('action'),
			type:     form.attr('method'),
			data:     form.serialize(),
			success:  function(data) {
				// Replace table's body with new values
				$('.subscribers-list').html(data);
			},
			fail: function() {
				alert('Une erreur s\'est produite. Veuillez réessayer.');
			}
		});
		return false;
	});
	
	// Delete subscriber
	$('body.subscribers').on('click', '.delete-subscriber', function(e){
		subscriberId = $(this).parents('tr').find('.table_user_id').text();
		$('#delete').modal();
	});	
	$('body.subscribers').on('click', '#confirm-deletion', function(e){
		// Prevent the form from reloading / changing the page
		e.preventDefault();
		console.log("subscriberId: " + subscriberId);
		// Insert new subscriber into database and refresh table's content 
		$.ajax({
			url:      "Subscribers",
			type:     "POST",
			data:     "subscriberId=" + subscriberId + "&confirm-deletion=true",
			success:  function(data) {
				// Replace table's body with new values
				$('.subscribers-list').html(data);
				$('#delete').modal('hide');
			},
			fail: function() {
				alert('Une erreur s\'est produite. Veuillez réessayer.');
			}
		});
		return false;
	});
	
	// Edit subscriber
	$('body.subscribers').on('click', '.edit-subscriber', function(e){
		var line = $(this).parents('tr');
		$('#user_id').val(line.find('.table_user_id').text());
		$('#user_email').val(line.find('.table_user_email').text());
		$('#user_first_name').val(line.find('.table_user_first_name').text());
		$('#user_last_name').val(line.find('.table_user_last_name').text());
		$('#user_age').val(line.find('.table_user_age').text());
		$('#user_gender option[value="' + line.find('.table_user_gender').text() + '"]').prop('selected', true);
		$('#edit').modal();
	});
	/*
	$('body.subscribers').on('click', '.remove-table-line', function(e){
		var line       = $(this).parents('tr');
		var id         = line.find('.id').text();
		var email      = line.find('.email').text();
		var first_name = line.find('.first_name').text();
		var last_name  = line.find('.last_name').text();
		var age        = line.find('.age').text();
		var gender     = line.find('.gender').text();
		// Prevent the form from reloading / changing the page
		e.preventDefault();

		// Insert new subscriber into database and refresh table's content 
		$.ajax({
			url:      "Subscribers",
			type:     "POST",
			data:     "id="+id+"&email="+email+"&first_name="+first_name+"&last_name="+last_name+"&age="+age+"&gender="+gender,
			success:  function(data) {
				// Replace table's body with new values
				//$('.subscribers-list').html(data);
			},
			fail: function() {
				alert('Une erreur s\'est produite. Veuillez réessayer.');
			}
		});
		return false;
	});
	*/
});