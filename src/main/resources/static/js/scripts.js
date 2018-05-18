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
}