//ע����֤
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
				$(".s4").html("�û�������Ϊ��");
				ok=false;
			}else	if(nick==""){
				$(".s5").html("�ǳƲ���Ϊ��");
				ok=false;
			}else if(password==""){
				$(".s6").html("���벻��Ϊ��");
				ok=false;
			}else if(password1==""){
				$(".s3").html("������ȷ������");
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
