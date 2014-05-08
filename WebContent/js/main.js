jQuery(document).ready(function($){
	var subscriberId = 0;
	// Insert subscriber
	$('body.subscribers').on('submit', '#add-subscriber', function(e){
		var form = $('#add-subscriber');
		// Prevent the form from reloading / changing the page
		e.preventDefault();
		console.log(form.serialize());
		// Insert new subscriber into database and refresh table's content 
		$.ajax({
			url:      form.attr('action'),
			type:     form.attr('method'),
			data:     form.serialize()
		})
		.done(function(data) {
			// Replace table's body with new values if there was no error
			if (data.slice(0, data.indexOf(" ")) == "Erreur" 
				|| data.slice(0, data.indexOf("\r")) == "Erreur" 
				|| data.slice(0, data.indexOf("\n")) == "Erreur" 
				|| data.slice(0, data.indexOf("\r\r")) == "Erreur") {
				$('.alert-error').css('visibility','visible').hide().fadeIn().removeClass('hidden');
			} else {
				$('.subscribers-list').html(data);
			}
		})
		.fail(function() {
			$('.subscribers-list').html(data);
		});
		return false;
	});
	// End of insert
	
	// Edit subscriber
	$('body.subscribers').on('click', '.update-subscriber', function(e){
		var line = $(this).parents('tr');
		$('#subscriberId').val(line.find('.table_user_id').text());
		$('#user_email').val(line.find('.table_user_email').text());
		$('#user_first_name').val(line.find('.table_user_first_name').text());
		$('#user_last_name').val(line.find('.table_user_last_name').text());
		$('#user_age').val(line.find('.table_user_age').text());
		$('#user_gender option[value="' + line.find('.table_user_gender').text() + '"]').prop('selected', true);
		$('#edit').modal();
	});
	
	$('body.subscribers').on('click', '#confirm-update', function(e){
		var form = $('#update-subscriber');
		console.log(form.serialize());
		// Prevent the form from reloading / changing the page
		e.preventDefault();
		console.log(form.serialize());
		// Insert new subscriber into database and refresh table's content 
		$.ajax({
			url:      form.attr('action'),
			type:     form.attr('method'),
			data:     form.serialize()
		})
		.done(function(data) {
			// Replace table's body with new values if there was no error
			if (data.slice(0, data.indexOf(" ")) == "Erreur" 
				|| data.slice(0, data.indexOf("\r")) == "Erreur" 
				|| data.slice(0, data.indexOf("\n")) == "Erreur" 
				|| data.slice(0, data.indexOf("\r\r")) == "Erreur") {
				$('.alert-error').css('visibility','visible').hide().fadeIn().removeClass('hidden');
			} else {
				$('.subscribers-list').html(data);
			}
		})
		.fail(function() {
			$('.alert-error').css('visibility','visible').hide().fadeIn().removeClass('hidden');
		})
		.always(function() {
			$('#edit').modal('hide');
		});
		return false;
	});
	// End of update
	
	// Delete subscriber
	$('body.subscribers').on('click', '.delete-subscriber', function(e){
		subscriberId = $(this).parents('tr').find('.table_user_id').text();
		$('#delete').modal();
	});	
	$('body.subscribers').on('click', '#confirm-deletion', function(e){
		// Prevent the form from reloading / changing the page
		e.preventDefault();
		
		// Insert new subscriber into database and refresh table's content 
		$.ajax({
			url:      "Subscribers",
			type:     "POST",
			data:     "subscriberId=" + subscriberId + "&confirm-deletion=true"
		})
		.done(function(data) {
			// Replace table's body with new values if there was no error
			if (data.slice(0, data.indexOf(" ")) == "Erreur" 
				|| data.slice(0, data.indexOf("\r")) == "Erreur" 
				|| data.slice(0, data.indexOf("\n")) == "Erreur" 
				|| data.slice(0, data.indexOf("\r\r")) == "Erreur") {
				$('.alert-error').css('visibility','visible').hide().fadeIn().removeClass('hidden');
			} else {
				$('.subscribers-list').html(data);
			}
		})
		.fail(function() {
			$('.alert-error').css('visibility','visible').hide().fadeIn().removeClass('hidden');
		})
		.always(function() {
			subscriberId = 0;
			$('#delete').modal('hide');
		});
		return false;
	});
	// End of delete
});