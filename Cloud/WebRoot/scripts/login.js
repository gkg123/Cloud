
//登录验证
$(function(){
		$("#login").click(function(){
		var name = $("#count").val().trim();
			var password = $("#password").val().trim();
			$(".s1").html("");
			$(".s2").html("");
			var ok=true;
		
			
			if(name==""){
			$(".s1").html("用户名不能为空");
				ok=false;
			}else if(password==""){
			$(".s2").html("密码不能为空");
				ok=false;
			}
			
			if(ok){
			
		$.ajax({
			
			url:"http://localhost:8088/Cloud/user/login.do",
			type:"post",
			data:{"name":name,"password":password},
			dataType:"json",
			success:function(result){
				if(result.status==0){
				window.location.href="edit.html";
					addCookie("uid",result.data);
				}else if(result.status==1){
					$(".s1").html(result.msg);
					
				}else{
					$(".s2").html(result.msg);
				
				}
			},
			error:function(){
				alert("系统错误");
				
			}
		});
		}
		});
	});