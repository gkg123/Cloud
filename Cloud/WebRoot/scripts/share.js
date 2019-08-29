//模糊查询分享的笔记
function findNote() {
		var noteName = $("#search_note").val().trim();
		$("#share_list").empty();
		if(event.keyCode == 13){
		
		$("#pc_part_2").hide();
		$("#pc_part_4").hide();
		$("#pc_part_6").show();
		/* $("#pc_part_2").toggle();
		$("#pc_part_4").toggle();
		$("#pc_part_6").toggle(); */
		$.ajax({
		url:"http://localhost:8088/Cloud/share/findTitle.do",
		type:"post",
		data:{"noteName":noteName},
		dataType:"json",
		success:function(result){
		if(result.status==0){
		var share = result.data;
		for(var i=0;i<share.length;i++){
			var body = share[i].cn_share_body;
			var title = share[i].cn_share_title;
			var noteId = share[i].cn_note_id;
			var shareNote = '<li class="disable"><a ><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+title+'  <button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"></i></button><button type="button" class="btn btn-default btn-xs btn_position_2 btn_replay" id="collect"><i class="fa fa-star" style="font-size:10px;line-height:10px;"></i></button></a></li>';
			var $shareNote = $(shareNote);
			//<button type="button" class="btn btn-default btn-xs btn_position_2 btn_sc" id="id"><i class="fa fa-star" style="font-size:20px;line-height:31px;"></i></button>
			//<button type="button" class="btn btn-default btn-xs btn_position_2 btn_replay" id="collect"><i class="fa fa-star" style="font-size:10px;line-height:10px;"></i></button>
			$shareNote.data("title",title);
			$shareNote.data("body",body);
			$shareNote.data("noteId",noteId);
			$("#share_list").append($shareNote);
		}
			alert(result.msg);
		}
		},error:function(){
			alert("查询错误");
		}
		
		
		});
		

	}

	}
//分享笔记
function shareNote(){
	var title = $("#note_list a.checked").parent().data("title");
	var body = um.getContent($("#note_list a.checked").parent());
	var noteId = $("#note_list a.checked").parent().data("noteId");
	alert("01");
	$.ajax({
	
		url:"http://localhost:8088/Cloud/share/addShare.do",
		type:"post",
		data:{"noteId":noteId,"title":title,"body":body},
		dataType:"json",
		success:function(result){
		if(result.status==0){
			alert(result.msg);
		
		}
		},error:function(){
		
		alert("分享笔记失败");
		
		}
	
	
	});
	
}