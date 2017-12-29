package DivideForTeacher;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;



import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class SaveGroupInfo
 */
@WebServlet("/SaveGroupInfo")
public class SaveGroupInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveGroupInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		
		if(action.equals("saveTeacherGroup")){
			saveTeacherGroup(request,response);
			return;
		}
		/*
		if(action.equals("SaveTGroupInfo"))
		{
			try
			{
				String sql = "";
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/School","root","Javaweb");
				Statement stat = conn.createStatement();
				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter writer = response.getWriter();
				//String json = request.getParameter("data");
				//JSONArray GroupInfoArray;
				//GroupInfoArray = new JSONArray();
				String json ="{\"rows:\":[{\"id\":\"1\",\"GroupId\":\"1\"},{\"id\":\"2\",\"GroupId\":\"1\"},{\"id\":\"3\",\"GroupId\":\"NaN\"},{\"id\":\"4\",\"GroupId\":\"3\"},{\"id\":\"5\",\"GroupId\":\"NaN\"}]}";
				try{
					JSONObject obj = JSONObject.fromObject(json);
					writer.print(obj);
				}catch(Exception ex){
					ex.printStackTrace();
				}
				
				
				JSONArray GroupInfoArray;
				GroupInfoArray = new JSONArray();
				GroupInfoArray = JSONArray.fromObject(json);
				//JSONArray GroupInfoArray = JSONArray.fromObject(json);
				 //	[{"id":"1","GroupId":"1"},{"id":"2","GroupId":"1"},{"id":"3","GroupId":"NaN"}]
				if(GroupInfoArray.size()>0)
				{
					for(int i=0;i<GroupInfoArray.size();i++)
					{
						JSONObject GroupInfo = GroupInfoArray.getJSONObject(i);
						sql ="update School.tbl_Teacher SET GroupId=\""+GroupInfo.getString("GroupId")
		                		+"\" where id=\""+GroupInfo.getString("id")+"\";";
						stat.executeUpdate(sql);
					}
				}
				//Iterator<Object> it = GroupInfoArray.iterator();
				// while (it.hasNext()) {
	            //   JSONObject GroupInfo = (JSONObject) it.next();
	            // update School.tbl_Student SET GroupId ="2" where id="1";
	            //   sql +="update School.tbl_Student SET GroupId=\""+GroupInfo.getString("GroupId")
	            //   		+"\" where id=\""+GroupInfo.getString("id")+"\"";
	                
	            // }
	            
				
				
				//writer.write("lll");
				stat.close();
				conn.close();
				
			}
			 catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	public void saveTeacherGroup(HttpServletRequest request, HttpServletResponse response){
		
		try{
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter writer = response.getWriter();
			
			String teacherIds = request.getParameter("teacherIds");
			String groupIds = request.getParameter("groupIds");
			String leaders = request.getParameter("leaders");
			
			String[] teacherArr = teacherIds.split(",");
			String[] groupArr = groupIds.split(",");
			String[] leaderArr = leaders.split(",");
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/School","root","Javaweb");
			Statement stat = conn.createStatement();
			
			for(int i = 0; i < teacherArr.length; i++ ){
				String teacherId = teacherArr[i];
				String groupId = groupArr[i];
				if(groupId.isEmpty()){
					groupId = "";
				}
				String sql = "update School.tbl_Teacher set GroupId='"+groupId+"' where id='"+teacherId+"' ";
				stat.executeUpdate(sql);
			}
			
			String sql = "update School.tbl_Teacher set IsLeader = \"0\" where id > 0";
			stat.executeUpdate(sql);
			for(int i = 0;i < leaderArr.length;i++){
				String leader = leaderArr[i];
				String sql2 = "update School.tbl_Teacher set IsLeader = \"1\" where id =\""+leader+"\"";
				stat.executeUpdate(sql2);
			}
			
			writer.write("{\"success\":true}");
			
		}catch(Exception ex){
			
			
		}
	}

}
