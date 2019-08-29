//注册验证
$(function(){
	
		$("#regist_button").click(function(){
			var name = $("#regist_username").val().trim();
			var nick = $("#nickname").val().trim();
			var password = $("#regist_password").val().trim();
			var password1 = $("#final_password").val().trim();
			$(".s3").html("");
			$(".s4").html("");
			$(".s5").html("");
			$(".s6").html("");
			var ok=true;
			if(name==""){
				$(".s4").html("用户名不能为空");
				ok=false;
			}else	if(nick==""){
				$(".s5").html("昵称不能为空");
				ok=false;
			}else if(password==""){
				$(".s6").html("密码不能为空");
				ok=false;
			}else if(password1==""){
				$(".s3").html("请输入确认密码");
				ok=false;
			}
			if(ok){
			$.ajax({
				url:"http://localhost:8088/Cloud/user/register.do",
				type:"post",
				data:{"name":name,"nick":nick,"password":password,"password1":password1},
				dataType:"json",
				success:function(result){
					if(result.status==2){
						$(".s4").html(result.msg);
					}
					if(result.status==0){
						alert(result.msg);
						$("#back").click();
							
					}
					if(result.status==1){
						$(".s3").html(result.msg);
					}
				}
			});
			}
		});
	
	});
