$(".comment-write input[type=submit]").click(addComment);

function addComment(e) {
	e.preventDefault();
	console.log("click!");
	
   var queryString = $(".comment-write").serialize();
   console.log("query : " + queryString);
   
   var url = $(".comment-write").attr("action");
   console.log("url : " + url);
   
   $.ajax({
	    type : 'post',
	    url : url,
	    data : queryString,
	    dataType : 'json',
	    error : onError,
	    success : onSuccess});
	
}

function onError(){ 
	
}

function onSuccess(data, status){
	console.log(data);
	var commentTemplate = $("#answerTemplate").html();
	var template = commentTemplate.format(data.writer.userId, data.formatterCreateDate
, data.contents, data.id, data.id)

   $(".qna-comment-slipp-articles").prepend(template);
	
   $(".comment-write textarea").val("");
} 



$(".link-delete-article").click(deleteComment);

function deleteComment(e){
	e.preventDefault();
	
	var deleteBtn = $(this);
	var url = deleteBtn.attr("href");
	console.log(url);
	
	$.ajax({
		type : 'delete',
		url : url,
		dataType = 'json',
		error : function(xhr, status){
			console.log("error");
		},
		success : function(data, status){
			console.log(data);
			if(data.valid){
				$deleteBtn.closest("article").remove();
			} else{
				alert(data.errorMessage);
			}
		}
	});
}

String.prototype.format = function() {
	var args = arguments;
	return this.replace(/{(\d+)}/g, function(match, number){
		return typeof args[number] != 'undefined'
			? args[number]
		    : match
		    ;
	});
};