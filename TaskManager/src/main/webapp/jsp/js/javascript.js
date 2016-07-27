// Edit comment
function editComment(comment_id) {
	
	
	var id = comment_id;
	var login = $('#ed-login').val();
	var date = $('#ed-date').val();
	var taskId = $('#ed-taskId').val();
	var textComment = $('#ed-comment').val();
	alert(id + "	" + login + "	" + date + "	" + taskId + "	" + textComment);
	$.ajax({
		url : 'edit_comment',
		data : ({
			id : comment_id,
			login : login,
			date : date,
			taskId : taskId,
			text : textComment
		}),
		success : function(result) {
			window.location.reload();
		}
	});
}

/* Show add div when clicked href add */
$(document).ready(function() {
	$('#add').click(function(event) {
		event.preventDefault();
		$('#add-project').slideToggle();
	});
});

/*Show edit project*/
$(document).ready(function() {
	$('#view-edit').click(function(event) {
		event.preventDefault();
		$('#view-edit-container').slideToggle();
	});
});

/*Show edit comment container*/
function showEdit(id) {
	$('#edit-comment-container' + id).show();
}
