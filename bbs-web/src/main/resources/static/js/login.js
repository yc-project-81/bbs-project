//当页面一加载，则发出ajax请求，请求服务器中的判断用户是否已经登录的serlvet
//如返回 code:1，则表示已经登录，
//如返回code:0, 则表示没有登录
$(function(){
		//serialize()作用将表单中所有的参数拼接起来
			$.ajax({
				type:"POST",
				url:"/bbs/login",
				dataType:"json",
				success:function(data){
					if(data.code=='1'){
						$("#logindiv").html("欢迎您,"+ data.obj.uname+", <a href='javascript:logout()'>退出</a>"  );
					}else{
						$("#logindiv").html("您尚未　<a href=\"login.html\">登录</a>		&nbsp;| &nbsp; <A href=\"reg.html\">注册</A> |");
						
					}
				}
			});
	});


function logout(){
	$.ajax({
		type:"POST",
		url:"user.action",
		data:"op=logout",
		dataType:"json",
		success:function(data){
			if(data.code=='1'){
				$("#logindiv").html("您尚未　<a href=\"login.html\">登录</a>		&nbsp;| &nbsp; <A href=\"reg.html\">注册</A> |");
			}else{
				$("#logindiv").html("欢迎您,"+ data.obj.uname+", <a href='javascript:logout()'>退出</a>"  );
			}
		}
	});
}