<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>LogIn Page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="Content/bootstrap/bootstrap.min.css">
	<script src="Scripts/Jquery/jquery-3.2.1.min.js"></script>
	<script src="Scripts/Web/clist.js"></script>
	<script src="Scripts/Web/sel_student.js"></script>

	<script type="text/javascript">
		var obj=document.getElementById("fbody")
        obj.style.height=(window.innerHeight).toString()+"px"
        
        function submit()
        {
        	if($("#Account").val()=="admin"&&$("#Password").val()=="123")
        	{
        		alert("登录成功~欢迎来到管理员页面！");
        		window.location.href="index.jsp?"; 
				
        	}
        	else if($("#Account").val()=="t003"&$("#Password").val()=="123")
        	{
        		alert("登录成功~欢迎来到教师界面！");
        		window.location.href="ScoreAssess.jsp?TeacherId=t003";
				
        	}
        	else if($("#Account").val()=="t004"&$("#Password").val()=="123")
        	{
        		alert("登录成功~欢迎来到教师界面！");
        		window.location.href="ScoreAssess.jsp?TeacherId=t003";
        	}
        	else
        	{
        		alert("账号与密码不匹配呢~")
        	}
        }        
</script>
</head>
<body id="fbody" style="padding:3px;background-color:#F6F4F0;height:100%">
<div class="panel panel-default" style="padding: 0px;margin-bottom:1px">
        <div class="panel-heading" style="padding: 3px;padding-left:5px;padding-right:5px">
            <h5><strong>请登录</strong></h5>
        </div>
    </div>	
    <div id="large-header" class="large-header">
		<form class="form-horizontal" role="form" method="get" >
                <div class="form-group" style="margin-bottom:5px">
                    <label class="col-sm-2 control-label">Account</label>
                    <div class="col-sm-8">
                        <input id="Account" type="text" class="form-control" name="stdname" placeholder="登录名"  />
                     </div>
                 </div>
                 <div class="form-group" style="margin-bottom:5px">
                     <label class="col-sm-2 control-label">Password</label>
                     <div class = "col-sm-8">
                        <input id="Password" type="password" class="form-control" name="stdname" placeholder="密码"  />
                    </div>
                </div>
                
                <div style="text-align: center;margin:10px">
                    <span id="mes"></span>
                    <a class="btn btn-success" onclick="submit();">登录</a> 
                </div>
    	 </form>
     </div>
  </body>
</html>
