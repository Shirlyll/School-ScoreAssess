package StudentScore;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Assess
 */
@WebServlet("/Assess")
public class Assess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Assess() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		/*
		if(action.equals("ShowStudentInfo")){
			ShowStudentInfo(request,response);
			return;
		}
		*/
		if(action.equals("ShowStudentInfo")){
			
			try{
				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter writer = response.getWriter();
				
				
				String TeacherId = request.getParameter("TeacherId");
				
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/School","root","Javaweb");
				Statement stat = conn.createStatement();
				//select GroupId From School.tbl_Teacher where TeacherId="t003"
				//writer.write(TeacherId);
				
				String sql_gid = "select GroupId From School.tbl_Teacher where TeacherId=\""+TeacherId+"\"";
				//stat.executeUpdate(sql_gid);
				
				ResultSet rs_gid = stat.executeQuery(sql_gid);
				String GroupId="";
				while(rs_gid.next())
				{
					GroupId += rs_gid.getString("GroupId");
				}
				//writer.write(GroupId);
				//select StudentId,StudentName From School.tbl_Student where GroupId="3";
				String sql_Std = "select id,StudentId,StudentName From School.tbl_Student where GroupId=\""+GroupId+"\"";
				ResultSet rs_std = stat.executeQuery(sql_Std);		
				StringBuilder sb = new StringBuilder();
				sb.append("[");
				while(rs_std.next()){
					sb.append("{");
					sb.append("\"id\":\""+rs_std.getString("id")+"\",");
					sb.append("\"StudentId\":\""+rs_std.getString("StudentId")+"\",");
					sb.append("\"StudentName\":\""+rs_std.getString("StudentName")+"\"");
					sb.append("},");
				}
				sb.replace(sb.length()-1, sb.length(), "]");
				
				writer.write(sb.toString());
				
				stat.close();
				conn.close();
				
				
				
			}catch(Exception ex)
			{
				
			}
		
		}
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
