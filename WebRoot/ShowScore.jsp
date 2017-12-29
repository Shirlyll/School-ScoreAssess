<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" import="java.sql.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>LeaderPage</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="Content/bootstrap/bootstrap.min.css">
	<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="Scripts/Jquery/jquery-3.2.1.min.js"></script>
	<script src="Scripts/Web/clist.js"></script>
	<script src="Scripts/Web/sel_student.js"></script>
	<script type="text/javascript">
	
	$(function(){
		ShowStudentScore();
	})	
	function ShowStudentScore()
	{
	 	$.get("/ScoreAssess/StudentScore/ShowScore",{"action":"ShowScore"},function(resultString){
	 				alert(resultString)
	 				var tablestr = "<table class=\"table table-striped\">";
					tablestr+="<tr>"+
		        			  "<td width='30%'>学号</td>"+
		        			  "<td width='30%'>学生姓名</td>"+
		        			  "<td width='20%'>评分</td>"+
		        			  "</tr>";
		        	var data = eval('('+resultString+')');
		           	$(data).each(function (each, student) {
			            tablestr += "<tr>";
			            tablestr += "<td>" +student.StudentId + "</td>";
			            tablestr += "<td>" + student.StudentName + "</td>";
			            tablestr += "<td>" + student.Score + "</td>";
			            tablestr += "</tr>";
			        });
			               	
		         	//写入html
		         	tablestr+="</table>";
		         	$("#table").html(tablestr); 
		         	
	         	});
	}
	</script>
  </head>
  
  <body>
	<ol class="breadcrumb">
	    <li><a href="LogIn.jsp">LogIn</a></li>
	    <li><a href="ScoreAssess.jsp">教师评分页</a></li>
	    <li>查看学生评分</li>
	</ol>
	
	<div id="table"></div>
  </body>
</html>
