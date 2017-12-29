package DivideForTeacher;

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
 * Servlet implementation class LeaderCheck
 */
@WebServlet("/LeaderCheck")
public class LeaderCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeaderCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		
		if(action.equals("CheckLeader")){
			CheckLeader(request,response);
			return;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	public void CheckLeader(HttpServletRequest request, HttpServletResponse response){
		
		try
		{
			response.setContentType("text/html;charset=utf-8");
			PrintWriter writer = response.getWriter();
			String TeacherId = request.getParameter("TeacherId");
			//writer.write(TeacherId);
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/School","root","Javaweb");
			Statement stat = conn.createStatement();
			String sql = "select IsLeader from School.tbl_Teacher where TeacherId =\""+TeacherId+"\"";
			ResultSet rs = stat.executeQuery(sql);
			String IsLeader = "";
			while(rs.next())
			{
				IsLeader = rs.getString("IsLeader");
			}
			//String IsLeader = rs.getString("IsLeader");
			writer.write(IsLeader);
			stat.close();
			conn.close();
			
			
			
				
		}
		catch(Exception ex){
			
		}
	
	
	}

}
