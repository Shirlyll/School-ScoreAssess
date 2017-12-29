<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" import="java.sql.*"%>
<%	
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>AdminPage</title>
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
			if($("#cclass").val()=="01")
			{
				DivideTGroup();
			}
			if($("#cclass").val()=="02")
			{
				DivideSGroup();
			}
			

		}
   		//教师表
        function DivideTGroup()
        {

        	$.get("/ScoreAssess/DivideForTeacher/ShowTeacherInfo",{"action":"ShowTeacherInfo"},function(resultString){
        			var tablestr = "<table class=\"table table-striped\">";
					tablestr+="<tr>"+
		        			  "<td width='20%'>id</td>"+
		        			  "<td width='20%'>工号</td>"+
		        			  "<td width='20%'>教师姓名</td>"+
		        			  "<td width='20%'>所在组号</td>"+
		        			  "<td width='20%'>组长</td>"+
		        			  
		        			  "</tr>";
		        	
		        	var data = eval('('+resultString+')');
		        	//alert(resultString)
		           	$(data).each(function (each, teacher) {
			            
			            tablestr += "<tr>" + "<td>" + "<div class=\"checkbox\">" + "<label><input type=\"checkbox\" name=\"ckb\" value=\"" + teacher.id + "\">" + teacher.id + " </label>" + "</div></td>";
			            tablestr += "<td>" +teacher.TeacherId + "</td>";
			            tablestr += "<td>" + teacher.TeacherName + "</td>";
			            tablestr += "<td>" + "<a id=\"" + teacher.id + "\" name=\"GroupId\">" + teacher.GroupId + "</a>" + "</td>";
			            tablestr +=	"<td>" + "<div class=\"checkbox\">"+ "<label><input type=\"checkbox\" name=\"leader\" value=\""+teacher.id+"\"></label>"+"</div>"+"</td>"+ "</tr>";;
			           
			            //tablestr += "<td>" + "<label>" + teacher.GroupNumber + "</label>" + "</td>" + "</tr>";
			        });
			               	
		         	//写入html
		         	tablestr+="</table>";
		         	btnstr="<a class=\"btn btn-success\" onclick=\"SaveTGroupInfo();\">提交分组信息</a>";
		         	$("#table").html(tablestr); 
		         	$("#btn").html(btnstr);
	         	});
        }
        //学生表
        function DivideSGroup()
        {
        	
        	$.get("/ScoreAssess/DivideForStudent/ShowStudentInfo",{"action":"ShowStudentInfo"},function(resultString){
        	
        			var tablestr = "<table class=\"table table-striped\">";
					tablestr+="<tr>"+
		        			  "<td width='20%'>id</td>"+
		        			  "<td width='30%'>学号</td>"+
		        			  "<td width='30%'>学生姓名</td>"+
		        			  "<td width='20%'>所在组号</td>"+
		        			  "</tr>";
		        	alert(resultString)
		        	
		        	var data = eval('('+resultString+')');
		        	alert(resultString)
		           	$(data).each(function (each, student) {
			            
			            tablestr += "<tr>" + "<td>" + "<div class=\"checkbox\">" + "<label><input type=\"checkbox\" name=\"ckb\" value=\"" + student.id + "\">" + student.id + " </label>" + "</div></td>";
			            tablestr += "<td>" +student.StudentId + "</td>";
			            tablestr += "<td>" + student.StudentName + "</td>";
			            tablestr += "<td>" + "<a id=\"" + student.id + "\" name=\"GroupId\">" + student.GroupId + "</a>" + "</td>";
			            tablestr += "</tr>";
			            //tablestr += "<td>" + "<label>" + teacher.GroupNumber + "</label>" + "</td>" + "</tr>";
			        });
			               	
		         	//写入html
		         	tablestr+="</table>";
		         	btnstr="<a class=\"btn btn-success\" onclick=\"SaveSGroupInfo();\">提交分组信息</a>";
		         	$("#table").html(tablestr); 
		         	$("#btn").html(btnstr);
		         	
	         	});
        }
        function SeleSubmit()
	    {
	        //给第一组安排教师
	        if ($("#DivideGroup").val() == "G1")
	        {
	            $("input[name='ckb']").each(function () {
	                if ($(this).is(":checked")) {
	                     $("#" + $(this).val()).html("1");
	                     
	                }
	            });  
	        }
	        if ($("#DivideGroup").val() == "G2")
	        {
	            $("input[name='ckb']").each(function () {
	                if ($(this).is(":checked")) {
	                     $("#" + $(this).val()).html("2");        
	                }
	            });  
	        }
	        if ($("#DivideGroup").val() == "G3")
	        {
	            $("input[name='ckb']").each(function () {
	                if ($(this).is(":checked")) {
	                     $("#" + $(this).val()).html("3");
	                }
	            });  
	        }
	        

	    }
	    /*
	    function SaveTGroupInfo()
	    {
	    	//alert("yes")
	    	var GroupId = document.getElementsByName("GroupId");
	        var data = "[";     //  [{"id":"1","groupId":"1"},{"id":"2"}]
	        $.each(GroupId, function (i, groupId) {
	            data += "{\"id\":\"" + groupId.id + "\"," + "\"GroupId\":\""+ +groupId.outerText + "\"},"
	        });
	        data = data.substring(0, data.length - 1);
	        data += "]";
	        //alert(data);
	       //	[{"id":"1","GroupId":"1"},{"id":"2","GroupId":"1"},{"id":"3","GroupId":"NaN"}]
	        $.get("/ScoreAssess/DivideForTeacher/SaveGroupInfo",{"action":"SaveTGroupInfo","data":data},function(resultString){
	        	
	        	alert("succ")
	        });
	        //alert(GroupId);
	 
	        
	    }
	    */
	    
	   	function SaveTGroupInfo(){
	   		var teacherList = document.getElementsByName("GroupId")
	   		var teacherIds = ""
	   		var groupIds = ""
	   		var first = true
	   		$.each(teacherList,function(i,teacher){
	   			if(!first){
	   				teacherIds += ","
	   				groupIds += ","
	   			}else{
	   				first = false
	   			}
	   			teacherIds += teacher.id
	   			groupIds += teacher.outerText
	   			
	   		})
	   		//存 组长信息
	   		var leaders = "";
	        var first = true;
	        $("input[name='leader']").each(function(){
	        	if($(this).is(":checked")){
	        	
	        		if(!first)
	        		{
	        			leaders+=","
	        		}
	        		else{first = false}
	        		
	        		leaders += $(this).val();
	        		////
	        	}
	        })
	   		
	   		$.get("/ScoreAssess/DivideForTeacher/SaveGroupInfo",{"action":"saveTeacherGroup","teacherIds":teacherIds,"groupIds":groupIds,"leaders":leaders},function(resultString){
	   			var result = JSON.parse(resultString)
	   			if(result.success){
	   				alert('success')
	   			}
	   		})
	   	}
	   	function SaveSGroupInfo()
	   	{
	   		var studentList = document.getElementsByName("GroupId")
	   		var studentIds = ""
	   		var groupIds = ""
	   		var first = true
	   		$.each(studentList,function(i,student){
	   			if(!first){
	   				studentIds += ","
	   				groupIds += ","
	   			}else{
	   				first = false
	   			}
	   			studentIds += student.id
	   			groupIds += student.outerText
	   			
	   		})
	   		
	   		$.get("/ScoreAssess/DivideForStudent/SaveSGroupInfo",{"action":"saveStudentGroup","studentIds":studentIds,"groupIds":groupIds},function(resultString){
	   			var result = JSON.parse(resultString)
	   			if(result.success){
	   				alert('success')
	   			}
	   		})
	   	}
	    
</script>
</head>
  <body id="fbody" style="padding:3px;background-color:#F6F4F0;height:100%">	
	<ol class="breadcrumb">
	    <li><a href="LogIn.jsp">LogIn</a></li>
	    <li>教师分组页</li>
	    <li>学生分组页</li>
	</ol>	 
	
	 <div class="form-group col-lg-3" >
                    <div class="col-sm-8">
                        <select class="form-control" id="cclass" name="cclass">
                            <option value="01">分配教师组</option>
                            <option value="02">分配学生组</option>
                        </select>
                    </div>
     
     <a class="btn btn-success" onclick="submit();">确定</a> 
	</div>
	
    <div>
    <label class="col-sm-2"></label>
             <div  class=" col-lg-2">
                 <select class="form-control" id="DivideGroup" class="col-sm-2">
                    <option value="G1">1</option>
             		<option value="G2">2</option>
             		<option value="G3">3</option>
                 </select>
             </div>

       <a class="btn btn-success" onclick="SeleSubmit();" >确定</a>
    
     </div>
      
   	  <div id="table"></div>
	  <div id="btn"></div>
  </body>
</html>
