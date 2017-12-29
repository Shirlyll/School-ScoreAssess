package DivideForStudent;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SaveSGroupInfo
 */
@WebServlet("/SaveSGroupInfo")
public class SaveSGroupInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveSGroupInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		
		if(action.equals("saveStudentGroup")){
			saveStudentGroup(request,response);
			return;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	public void saveStudentGroup(HttpServletRequest request, HttpServletResponse response){
		try{
			response.setContentType("text/html;charset=utf-8");
			PrintWriter writer = response.getWriter();
			
			String studentIds = request.getParameter("studentIds");
			String groupIds = request.getParameter("groupIds");
			
			String[] studentArr = studentIds.split(",");
			String[] groupArr = groupIds.split(",");
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/School","root","Javaweb");
			Statement stat = conn.createStatement();
			
			for(int i = 0; i < studentArr.length; i++ ){
				String studentId = studentArr[i];
				String groupId = groupArr[i];
				if(groupId.isEmpty()){
					groupId = "";
				}
				String sql = "update School.tbl_Student set GroupId='"+groupId+"' where id='"+studentId+"' ";
				stat.executeUpdate(sql);
			}
			
			writer.write("{\"success\":true}");
			
		}catch(Exception ex){
			
			
		}
	}

}
