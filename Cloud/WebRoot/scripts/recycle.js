//点击显示回收站列表
var count=0;
function showRecycle(){
	$("#input_note_title").val("");
	um.setContent("");
	$("#pc_part_5").hide();
	$("#pc_part_3").show();
	count++;
			$("#recycle").empty();
			/*$("#pc_part_2").hide();*/
			/*$("#pc_part_2").toggle();
			$("#pc_part_4").toggle();*/
			$("#pc_part_6").hide();
		
			if(count%2==1){
				$("#pc_part_2").hide();
				$("#pc_part_4").show();
			}else{
				$("#pc_part_2").show();
				$("#pc_part_4").hide();
				
			}
			
		$.ajax({
		url:"http://localhost:8088/Cloud/note/recycle.do",
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.status==0){
			var list = result.data;
			for(var i=0;i<list.length;i++){
			var title = list[i].cn_note_title;
			var body = list[i].cn_note_body;
			var noteId = list[i].cn_note_id;
			var note='<li class="disable"><a ><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+title+'<button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"></i></button><button type="button" class="btn btn-default btn-xs btn_position_2 btn_replay" id="id"><i class="fa fa-reply"></i></button></a></li>';
			var $notes = $(note);
			$notes.data("noteId",noteId);
			$notes.data("title",title);
			$notes.data("body",body);
			$("#recycle").prepend($notes);
			}
			
			}
			
		},error:function(){
		
		alert("回收站显示错误");
		}
		
		
		});
		
		}
//回收站添加选中状态
function addChecked(){
	
	  
	  $("#recycle li a").removeClass("checked");
	$(this).find("a").addClass("checked");
	  var noteId = $(this).data("noteId");
	
	}
//回收站--点击删除出现弹框
function clickDel(){
	  
	
	 $(".opacity_bg").show();
	$("#can").load("alert/alert_delete_rollback.html");
	close();
}

//删除笔记(不可恢复)
function deleteNote(){
	 var noteId = $("#recycle a.checked").parent().data("noteId");
	 $.ajax({
	 url:"http://localhost:8088/Cloud/note/deleteNote.do",
	 type:"post",
	 data:{"id":noteId},
	 dataType:"json",
	 success:function(result){
	 if(result.status==0){
	 alert(result.msg);
	 }
	 },error:function(){
	 alert("删除失败");
	 }
	 });
	 $("#recycle a.checked").parent().hide();
	bookwindow();
}

//添加下拉列表
function addOption(){
	
	 $(".opacity_bg").show();
		$("#can").load("alert/alert_replay.html");
		close();
		$.ajax({
		url:"http://localhost:8088/Cloud/notebook/show.do",
		type:"post",
		data:{"id":id},
		dataType:"json",
		success:function(result){
		if(result.status==0){
		
			var books = result.data;
			for(var i=0;i<books.length;i++){
			var name = books[i].cn_notebook_name;
			
			var notebookid = books[i].cn_notebook_id;
			var option = '<option>'+name+'</option>';
			var $option = $(option);
			$option.data("notebookid",notebookid);
			$("#replaySelect").append($option);
			}
		}
		},error:function(){
		
		alert("获取笔记列表失败");
		}
		});
		
	}

//回收笔记
function recycleNote(){
	var notebookid = $("#replaySelect :selected").data("notebookid");
	/* var title = $("#recycle a.checked").parent().data("title");
	var body = $("#recycle a.checked").parent().data("body"); */
	var noteId = $("#recycle a.checked").parent().data("noteId");
	$.ajax({
		url:"http://localhost:8088/Cloud/note/recoverNote.do",
		type:"post",
		data:{"notebookid":notebookid,"noteId":noteId},
		dataType:"json",
		success:function(result){
		if(result.status==0){
		alert(result.msg);
		}
		},error:function(){
		
		alert("笔记恢复失败");
		}
		
	});
	$("#recycle a.checked").parent().hide();
	bookwindow();
}
