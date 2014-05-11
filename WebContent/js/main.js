jQuery(document).ready(function($){
/********************************************************************************************************
 **************************************** SUBSCRIBERS ***************************************************
 ********************************************************************************************************/
	var subscriberId = 0;
	// Insert subscriber
	$('body.subscribers').on('submit', '#add-subscriber', function(e){
		var form = $('#add-subscriber');
		// Prevent the form from reloading / changing the page
		e.preventDefault();
		
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

		// Prevent the form from reloading / changing the page
		e.preventDefault();

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
	// End of update subscriber
	
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
	// End of delete subscriber
/********************************************************************************************************
 *************************************** END OF SUBSCRIBERS *********************************************
 ********************************************************************************************************/
	
	
	
/********************************************************************************************************
 ******************************************** LISTS *****************************************************
 ********************************************************************************************************/
	var listId = 0;
	// Disable sorting for the first header (checkbox "check all") to prevent it from blocking the click.
	$("body.addList #subscribers-list, body.editList #subscribers-list").tablesorter({ 
        headers: {
            0: {  
                sorter: false 
            }
        } 
    });
	// Checking every checkbox
	$('body.addList, body.editList').on('click', '#subscribers-list #checkall', function(e){
        if ($("#subscribers-list #checkall").is(':checked')) {
            $("#subscribers-list input[type=checkbox]").each(function () {
                $(this).prop("checked", true);
            });

        } else {
            $("#subscribers-list input[type=checkbox]").each(function () {
                $(this).prop("checked", false);
            });
        }
    });
	
	// Delete list
	$('body.lists').on('click', '.delete-list', function(e){
		listId = $(this).parents('tr').find('.table_list_id').text();
		$('#delete').modal();
	});	
	$('body.lists').on('click', '#confirm-deletion', function(e){
		// Prevent the form from reloading / changing the page
		e.preventDefault();

		if ($.isNumeric(listId)) { 
			// Insert new subscriber into database and refresh table's content 
			$.ajax({
				url:      "Lists",
				type:     "POST",
				data:     "listId=" + listId + "&confirm-deletion=true"
			})
			.done(function(data) {
				// Replace table's body with new values if there was no error
				if (data.slice(0, data.indexOf(" ")) == "Erreur" 
					|| data.slice(0, data.indexOf("\r")) == "Erreur" 
					|| data.slice(0, data.indexOf("\n")) == "Erreur" 
					|| data.slice(0, data.indexOf("\r\r")) == "Erreur") {
					$('.alert-error').css('visibility','visible').hide().fadeIn().removeClass('hidden');
				} else {
					$('.lists-list').html(data);
				}
			})
			.fail(function() {
				$('.alert-error').css('visibility','visible').hide().fadeIn().removeClass('hidden');
			})
			.always(function() {
				listId = 0;
				$('#delete').modal('hide');
			});
		} else {
			listId = 0;
			$('#delete').modal('hide');
		}
		return false;
	});
	
	// LIST EDITION
	var subscriberIds = [];
	// Push every existing ids to the array
	if ($("body").hasClass("editList")) {
		var listId = $('.listId').val();
		// Set subscriber ids to session
		$.ajax({
			url:      "ListsAjax",
			type:     "GET",
			dataType: "json",
			data:     "listId=" + listId
		})
		.done(function(datas) {
			var ids = datas['subscriberIds'].split(",");
			$.each(ids, function(i) {
				subscriberIds.push(ids[i]);
				$.unique(subscriberIds);
			});
		});
	}
	// Push every checkbox checked or remove them from the array on change
	$("body.editList, body.addList").on('change', '#subscribers-list input[type=checkbox]', function(e) {
		var value = e.target.value;
		if ($(e.target).is(":checked") && $(e.target).attr("id") != "checkall" ) {
			subscriberIds.push(value);
			$.unique(subscriberIds);
		} else if ($(e.target).attr("id") == "checkall" && $(e.target).is(":checked")) {
			$('.checkthis').each(function(){
				subscriberIds.push($(this).val());
				$.unique(subscriberIds);
			});
		} else if ($(e.target).attr("id") == "checkall" && !$(e.target).is(":checked")) {
			subscriberIds = [];
		} else {
			if ($.inArray(value, subscriberIds) >= 0) {
				subscriberIds.splice($.inArray(value, subscriberIds), 1);
			}
		}
		
		// Set subscriber ids to session
		$.ajax({
			url:      "ListsAjax",
			type:     "POST",
			dataType: "json",
			data:     "subscriberIds=" + subscriberIds
		});
	});
	$('body.editList').on('click', '.page-link', function(e){
		var page   = $(this).text();
		var listId = $('.listId').val();
		// Prevent the form from reloading / changing the page
		e.preventDefault();
		// Insert new subscriber into database and refresh table's content
		$.ajax({
			url:      "ListsAjax",
			type:     "POST",
			dataType: "json",
			data:     "page=" + page + "&listId=" + listId
		})
		.done(function(data) {
			// Replace table's body with new values if there was no error
			if (data['msg'] != "OK") {
				$('.alert-error').css('visibility','visible').hide().fadeIn().removeClass('hidden');
			} else {
				$('.subscribers-list').html(data['list']);
				$('.pagination').html(data['pagination']);
				window.history.pushState(data, "eTargeting - Editer la liste " + listId, "EditList?id=" + listId + "&page=" + page);
			}
		});
		return false;
	});
	$('body.addList').on('click', '.page-link', function(e){
		var page   = $(this).text();
		// Prevent the form from reloading / changing the page
		e.preventDefault();
		// Insert new subscriber into database and refresh table's content
		$.ajax({
			url:      "ListsAjax",
			type:     "POST",
			dataType: "json",
			data:     "page=" + page
		})
		.done(function(data) {
			// Replace table's body with new values if there was no error
			if (data['msg'] != "OK") {
				$('.alert-error').css('visibility','visible').hide().fadeIn().removeClass('hidden');
			} else {
				$('.subscribers-list').html(data['list']);
				$('.pagination').html(data['pagination']);
				window.history.pushState(data, "eTargeting - Ajouter une liste", "AddList?page=" + page);
			}
		});
		return false;
	});
	$('body.editList').on('submit', '#edit-list', function(e){
		// Disable every checkbox to lighten form params
		$('.checkthis').each(function(){
			$(this).attr("disabled", "disabled");
		});
		
		var form     = $('#edit-list');
		// Add subscriber ids checked in the form
		var inputIds = $("<input>").attr("type", "hidden").attr("name", "subscriberIds").val(subscriberIds.toString());
		form.append($(inputIds));
		// Prevent the form from reloading / changing the page
		e.preventDefault();
		// Insert new subscriber into database and refresh table's content
		$.ajax({
			url:      form.attr('action'),
			type:     form.attr('method'),
			data:     form.serialize()
		})
		.done(function(data) {
			if (data.slice(0, data.indexOf(" ")) == "Erreur" 
				|| data.slice(0, data.indexOf("\r")) == "Erreur" 
				|| data.slice(0, data.indexOf("\n")) == "Erreur" 
				|| data.slice(0, data.indexOf("\r\r")) == "Erreur") {
				$('.alert-error').css('visibility','visible').hide().fadeIn().removeClass('hidden');
			} else {
				$(location).attr('href', 'Lists');
			}
		})
		.fail(function() {
			$(location).attr('href', 'Lists');
		});
		return false;
	});
	$('body.addList').on('submit', '#add-list', function(e){
		// Disable every checkbox to lighten form params
		$('.checkthis').each(function(){
			$(this).attr("disabled", "disabled");
		});
		
		var form     = $('#add-list');
		// Add subscriber ids checked in the form
		var inputIds = $("<input>").attr("type", "hidden").attr("name", "subscriberIds").val(subscriberIds.toString());
		form.append($(inputIds));
		// Prevent the form from reloading / changing the page
		e.preventDefault();
		// Insert new subscriber into database and refresh table's content
		$.ajax({
			url:      form.attr('action'),
			type:     form.attr('method'),
			data:     form.serialize()
		})
		.done(function(data) {
			if (data['msg'] != "OK") {
				$('.alert-error').css('visibility','visible').hide().fadeIn().removeClass('hidden');
			} else {
				$(location).attr('href', 'Lists');
			}
		})
		.fail(function() {
			$(location).attr('href', 'Lists');
		});
		return false;
	});
	// END OF LIST EDITION
/********************************************************************************************************
 ******************************************* END OF LISTS ***********************************************
 ********************************************************************************************************/
});