function showbook(){
	
	
	$.ajax({
		url:"http://localhost:8088/Cloud/notebook/show.do",
		type:"post",
		data:{"id":id},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var books = result.data;
				for(var i=0;i<books.length;i++){
				var bookname = books[i].cn_notebook_name;
				var bookid = books[i].cn_notebook_id;
				
									var book = '<li class="online">';
									book+= '<a>';
									book+= '<i class="fa fa-book" title="online" rel="tooltip-bottom">';
									book += '</i>'+bookname+'<button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"></i></button></a></li>';
									
				var $book = $(book);
				$book.data("bookid",bookid);
				$("#booklist").append($book);
				
				}
				
			}
		
		}
		});
}


//重命名
function rename(){
	var $a = $("#booklist a.checked").parent(); 
	var bookname = $("#input_notebook_rename").val().trim();
	
	var bookid = $a.data("bookid");
	$.ajax({
		url:"http://localhost:8088/Cloud/notebook/updateBookName.do",
		type:"post",
		data:{"bookId":bookid,"bookName":bookname},
		dataType:"json",
		success:function(result){
			if(result.status==0){
			alert(result.msg);
			bookwindow();
			window.location.href="http://localhost:8088/Cloud/edit.html";
			}

		}

	});
	
}

function checkNoteBook(){
	
	var bookid = $("#booklist a.checked").parent().data("bookid");
	$("#booklist a .btn_delete").hide();
	$.ajax({
		url:"http://localhost:8088/Cloud/notebook/checkNoteBook.do",
		type:"post",
		data:{"notebookid":bookid},
		dataType:"json",
		success:function(result){
			
			if(result.status==0){
			$("#booklist a.checked .btn_delete").hide();
			}else{
			$("#booklist a.checked .btn_delete").show();
			}
		
		},error:function(){
		
		alert("系统错误");
		}
	
	
	});
	}
//删除笔记本
function delNoteBook(){
	var bookid = $("#booklist a.checked").parent().data("bookid");
	$.ajax({
		url:"http://localhost:8088/Cloud/notebook/deleteNoteBook.do",
		type:"post",
		data:{"notebookid":bookid},
		dataType:"json",
		success:function(result){
			if(result.status==0){
			alert(result.msg);
			}
			if(result.status==1){
			
			alert(result.msg);
			}
			
		
		},error:function(){
		
		alert("删除笔记本失败");
		}
	
	
	});
	bookwindow();
	$("#booklist a.checked").parent().hide();
	}
