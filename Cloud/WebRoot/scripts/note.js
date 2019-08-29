//显示全部笔记
function booklist(){
	$("#input_note_title").val("");
	um.setContent("");
	$("#pc_part_5").hide();
	$("#pc_part_3").show();
	$("#pc_part_4").hide();
	$("#pc_part_6").hide();
	$("#pc_part_2").show();
	$("#booklist li a").removeClass("checked");
	$(this).find("a").addClass("checked");
	$("#note_list").empty();
	var bookid = $(this).data("bookid");
	/*var bookid = $(this).data("bookid");
	alert(bookid);*/
	
	$.ajax({
		url:"http://localhost:8088/Cloud/note/noteBooks.do",
		type:"post",
		data:{"id":bookid},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var books = result.data;
				for(var i=0;i<books.length;i++){
				var noteId  = books[i].cn_note_id;
					var title = books[i].cn_note_title;
					var status = books[i].cn_note_status_id;
					if(status!=2){
						
					
					var booklist='<li class="online" id="l1">';
					 booklist+='<a>';
					 booklist+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+title+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
					 booklist+='</a>';
					 booklist+='<div class="note_menu" tabindex="-1">';
					 booklist+='<dl>';
					 booklist+='<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
					 booklist+='<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
					 booklist+='<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
					 booklist+='</dl>';
					 booklist+='</div>';
					 booklist+='</li>';
					
				var $booklists=$(booklist);
				$booklists.data("noteId",noteId);
				$booklists.data("title",title);
				$("#note_list").append($booklists);
					}
				}
			
			}
		
		}
	});
	
	};
//点击"+"按钮
function addNote(){
	$("#can").load("alert/alert_notebook.html");
	$(".opacity_bg").show();
	close();
	$("#can").on("click",".cancle",bookwindow);
	
};
//关闭窗口
function bookwindow(){
	$("#can").empty();
	$(".opacity_bg").hide();
}

//取消和关闭;
function close(){
	$("#can").on("click",".close",function(){
		$("#can").empty();
		$(".opacity_bg").hide();
		});
	$("#can").on("click",".cancle",function(){
		$("#can").empty();
		$(".opacity_bg").hide();
		});
	
};
//添加笔记本
function addNoteBook(){
	var name = $("#input_notebook").val().trim();
	$.ajax({
		url:"http://localhost:8088/Cloud/notebook/addNoteBook.do",
		type:"post",
		data:{"name":name,"id":id},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert(result.msg);
				var bookid = result.data;
				var book = '<li class="online">';
				book+= '<a>';
				book+= '<i class="fa fa-book" title="online" rel="tooltip-bottom">';
				book += '</i>'+name+'</a></li>';
				
				var $book = $(book);
				$book.data("bookid",bookid);
				$("#booklist").append($book);
				bookwindow();
				/*window.location.href="edit.html";*/
			}
			
		},
		error:function(){
		
		alert("系统错误");
		}
	});
};

//退出登录
function exit(){
	delCookie("uid");
};

/*function rename(){
	var bookid = $(this).data("bookid");
	$("#can").load("alert/alert_rename.html");
	$(".opacity_bg").show();
	$("#can").on("click",".close",function(){
	$("#can").empty();
	$(".opacity_bg").hide();
	});
	$("#can").on("click",".cancle",function(){
	$("#can").empty();
	$(".opacity_bg").hide();
	$("#can").on("click",".rename",function(){
		alert("001");
		
		
	});
});
	};*/
//添加笔记
function addnote(){
	var $a = $("#booklist a.checked").parent();
	var name = $("#input_note").val().trim();
	var bookid = $a.data("bookid");
	if(bookid==undefined){
	alert("你还没有选择笔记本");
	return;
	}
	$.ajax({
	url:"http://localhost:8088/Cloud/note/addNote.do",
	type:"post",
	data:{"bookId":bookid,"userId":id,"bookTitle":name},
	dataType:"json",
	success:function(result){
		if(result.status==0){
			/*var msg = result.msg;*/
			var noteId = result.data;
			var booklist='<li class="online" id="l1">';
			 booklist+='<a>';
			 booklist+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+name+'<button  type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
			 booklist+='</a>';
			 booklist+='<div class="note_menu" tabindex="-1">';
			 booklist+='<dl>';
			 booklist+='<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
			 booklist+='<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
			 booklist+='<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
			 booklist+='</dl>';
			 booklist+='</div>';
			 booklist+='</li>';
		var $booklists=$(booklist);
		$booklists.data("noteId",noteId);
	/*	$booklists.data("msg",msg);*/
		$("#note_list").prepend($booklists);
	
		alert(result.msg);
		
		bookwindow();
		/*window.location.href="edit.html";*/
		}
	
	},
	error:function(){
	
	alert("添加笔记本失败");
	}
		
	});

	};
	
	//显示笔记内容
	function showNote(){
		$(this).siblings().find(".note_menu").hide();
		$("#note_list li a").removeClass("checked");
		$(this).find("a").addClass("checked"); 
		var noteId = $(this).data("noteId");
		
		$.ajax({
			url:"http://localhost:8088/Cloud/note/showBody.do",
			type:"post",
			data:{"noteId":noteId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					$("#input_note_title").val(result.data.cn_note_title);
					
					um.setContent(result.data.cn_note_body);
				
				}
			
			},
			error:function(){
			alert("出现错误");
			
			}
		});
			
		};
	
		
	function updateNote(){
			var title = $("#input_note_title").val().trim();
			var body = um.getContent();
			var $a = $("#note_list a.checked").parent();
			var noteId = $a.data("noteId");
			$.ajax({
			url:"http://localhost:8088/Cloud/note/updateNote.do",
			type:"post",
			data:{"noteId":noteId,"title":title,"body":body},
			dataType:"json",
			success:function(result){
				if(result.status==0){
				$("#note_list a.checked").html('<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+title+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>');
				alert(result.msg);
				
				}
			
			},error:function(){
			
				alert("修改失败");
			
			}

			});
			
			};
			/*function hieddiv(){
				
				$("#note_list div").hide();
			}*/
			//点击删除按钮出现删除弹框
			function delNote(){
				$note = $("#note_list a.checked").parent();
				var noteid = $note.data("noteId");
				$("#can").load("alert/alert_delete_note.html");
				$(".opacity_bg").show();
				close();
				/* $("#note_list li a.checked").hide(); */
			}
			//下拉列表
			function showlist(){
				$("#note_list a.checked").next().toggle();
				/*$("#note_list li div").show();*/
				/* $("#note_list a.checked + div").show();*/
				
				
			/*	$("#note_list a.checked+div").hide();
			*/
			
				
				}
			//笔记列表--删除(隐藏-放至回收站 status改为2)
			function hideNote(){
				$("#note_list a.checked").parent().hide();
				$par = $("#note_list a.checked").parent();
				var noteId = $par.data("noteId");
				$.ajax({
					url:"http://localhost:8088/Cloud/note/updateStatus.do",
					type:"post",
					data:{"noteId":noteId},
					dataType:"json",
					success:function(result){
					if(result.status==0){
					alert(result.msg);
				
					
					}
					},error:function(){
					alert("删除失败");
					
					}
				});
				/* $title = $("#note_list a.ckecked");
				var t = $title.data;
				alert(t); */
				
				bookwindow();
				}

			//收藏笔记
			function collectNote(){
				var title = $("#share_list a.checked").parent().data("title");
				var body = $("#share_list a.checked").parent().data("body");
				var noteId = $("#share_list a.checked").parent().data("noteId");

				$.ajax({
				url:"http://localhost:8088/Cloud/note/collect.do",
				type:"post",
				data:{"noteId":noteId},
				dataType:"json",
				success:function(result){
				if(result.status==0){
				alert(result.msg);
				
				}
				
				},error:function(){
				
				alert("收藏失败");
				}
				
				
				
				});
			bookwindow();
			}