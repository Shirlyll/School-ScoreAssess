package DivideForStudent;

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
 * Servlet implementation class ShowStudentInfo
 */
@WebServlet("/ShowStudentInfo")
public class ShowStudentInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowStudentInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if(action.equals("ShowStudentInfo"))
		{
			try
			{
				response.setContentType("text/html;charset=utf-8");
				PrintWriter writer = response.getWriter();
				
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/School","root","Javaweb");
				Statement stat = conn.createStatement();
				String sql = "SELECT id,StudentId,StudentName,GroupId from School.tbl_Student";
				//stat.executeQuery(sql);
				
				ResultSet rs = stat.executeQuery(sql);
				StringBuilder sb = new StringBuilder();
				sb.append("[");
				while(rs.next()){
					sb.append("{");
					sb.append("\"id\":\""+rs.getString("id")+"\",");
					sb.append("\"StudentId\":\""+rs.getString("StudentId")+"\",");
					sb.append("\"StudentName\":\""+rs.getString("StudentName")+"\",");
					sb.append("\"GroupId\":\""+rs.getString("GroupId")+"\"");
					sb.append("},");
				}
				sb.replace(sb.length()-1, sb.length(), "]");
				
				
				writer.write(sb.toString());
				
				stat.close();
				conn.close();
				
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
