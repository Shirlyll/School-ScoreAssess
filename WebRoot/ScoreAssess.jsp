<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" import="java.sql.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>TeacherPage</title>
    
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
	$(function () {
	
		var str = getQueryString("TeacherId");
		//alert(str)
   		str="t003";
   		LeaderCheck(str);
   		ShowStudentInfo(str);

	});
	
	function getQueryString(name) {
	    var result = window.location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
	    if (result == null || result.length < 1) {
	        return "";
	    }
	    return result[1];
	}
	
	function ShowStudentInfo(TeacherId)
	{   	
			//alert(TeacherId)
			$.get("/ScoreAssess/StudentScore/Assess",{"action":"ShowStudentInfo","TeacherId":TeacherId},function(resultString){
		
					//alert(resultString)
					var data = eval('('+resultString+')');
					var tablestr = "<table class=\"table table-striped\">";
					tablestr+="<tr>"+
		        			  "<td width='15%'>学号</td>"+
		        			  "<td width='15%'>学生姓名</td>"+
		        			  "<td width='20%'>专业问答得分</td>"+	
		        			  "<td width='20%'>外语问答得分</td>"+	
 			  		          "<td width='20%'>小组讨论得分</td>"+
 			  		          "<td width='10%'>总评成绩</td>"+
		        			  "</tr>";
		        	
					$(data).each(function (each, student) {
			            
			            tablestr += "<tr>";
			            tablestr += "<td>" +student.StudentId + "</td>";
			            tablestr += "<td>" +student.StudentName + "</td>";
			            tablestr += "<td><input type=\"text\" name=\"score"+student.id+"\" class=\"form-control\"></td>";
			            tablestr += "<td><input type=\"text\" name=\"score"+student.id+"\" class=\"form-control\"></td>";
			            tablestr += "<td><input type=\"text\" name=\"score"+student.id+"\" class=\"form-control\"></td>";
			            tablestr += "<td><a name=\"FinalScore\" id=\""+student.id+"\"></a></td>";
			            tablestr += "</tr>";
			            
			        });
			        
			        $("#table").html(tablestr);
			        $("#accountId").val(TeacherId);
			        
			        btnstr="<a class=\"btn btn-success\" onclick=\"Calculate(" + JSON.stringify(data).replace(/"/g, "&quot;") +");\">计算总评</a>";
			     	//ps:上述代码我感到瑟瑟发抖/doge
			        $("#CalBtn").html(btnstr);
			        
			         
			});
	
	}
	//
	function Calculate(data){
		$(data).each(function(each,student){
			var score=0;
			$("input[name='score"+student.id+"']").each(function () {
	        	score += parseFloat($(this).val())
	        	
	        });
	       	score = (score/3.0).toFixed(2); 
	        $("#" + student.id).html(score);
		});
		
		 btnstr="<a class=\"btn btn-success\" onclick=\"SaveScore();\">提交</a>";
		 $("#Submit").html(btnstr);	
	}
	
	
	function SaveScore()
	{
	
			var ScoreList = document.getElementsByName("FinalScore")
	   		var StudentIds = ""
	   		var scores = ""
	   		var first = true
	   		$.each(ScoreList,function(i,student){
	   			if(!first){
	   				StudentIds += ","
	   				scores += ","
	   			}else{
	   				first = false
	   			}
	   			StudentIds += student.id;
	   			scores += student.outerText;
	   		})
			
	   		$.get("/ScoreAssess/StudentScore/SaveScore",{"action":"SaveScore","StudentIds":StudentIds,"scores":scores,"TeacherId":$("#accountId").val()},function(resultString){
	   			
	   			//alert(resultString)
	   			var result = JSON.parse(resultString)
	   			if(result.success){
	   				alert('success')
	   				
	   			}
	   		})
	   		
	}
	function LeaderCheck(TeacherId)
	{
		
		$.get("/ScoreAssess/DivideForTeacher/LeaderCheck",{"action":"CheckLeader","TeacherId":TeacherId},function(resultString){
			if(resultString=="0")
			{
				str = "<ol class=\"breadcrumb\" id=\"title\"><li><a href=\"LogIn.jsp\">LogIn</a></li><li>教师评分页</li></ol>";
				$("#title").html(str);
			}
			else if(resultString=="1")
			{
				str = "<ol class=\"breadcrumb\" id=\"title\"><li><a href=\"LogIn.jsp\">LogIn</a></li><li>教师评分页</li><li><a href=\"ShowScore.jsp\">查看学生评分</a></li></ol>";
				$("#title").html(str);
			}

		});
		
	}
	
	</script>
</head>
  
  <body id="fbody" style="padding:3px;background-color:#F6F4F0;height:100%">	
    <div id="title"></div>
    
	<p><span class="glyphicon glyphicon-pencil"></span>请为学生评分。(0-5)分</p>
	<div id="table"></div>
	<div id="CalBtn" style="display:inline-block"></div>
	<div id="Submit" style="display:inline-block"></div>
	<div id="accountId" style="display:none"></div>

  </body>
</html>
