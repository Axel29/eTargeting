jQuery(document).ready(function($){
	$('body.subscribers').on('submit', '#add-subscriber', function(e){
		var form = $('#add-subscriber');
		// Prevent the form from reloading / changing the page
		e.preventDefault();
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
});