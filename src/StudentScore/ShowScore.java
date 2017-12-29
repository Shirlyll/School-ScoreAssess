package StudentScore;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowScore
 */
@WebServlet("/ShowScore")
public class ShowScore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowScore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		
		if(action.equals("ShowScore")){
			ShowStdScore(request,response);
			return;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	public void ShowStdScore(HttpServletRequest request, HttpServletResponse response){
		
		try{
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter writer = response.getWriter();

			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/School","root","Javaweb");
			Statement stat = conn.createStatement();
			String sql = "select StudentId,StudentName,Score1,Score2,Score3 from School.tbl_Student";
			ResultSet rs = stat.executeQuery(sql);
			StringBuilder sb = new StringBuilder();
			Double Score1,Score2,Score3;
			sb.append("[");
			while(rs.next()){
				sb.append("{");
				sb.append("\"StudentId\":\""+rs.getString("StudentId")+"\",");
				sb.append("\"StudentName\":\""+rs.getString("StudentName")+"\",");
				if(rs.getString("Score1")==null)
				{
					Score1 = (double) 0;
				}
				else
				{
					Score1 = Double.valueOf(rs.getString("Score1")).doubleValue();
				}
				if(rs.getString("Score2")==null)
				{
					Score2 = (double) 0;
				}
				else
				{
					Score2 = Double.valueOf(rs.getString("Score2")).doubleValue();
				}
				if(rs.getString("Score3")==null)
				{
					Score3 = (double) 0;
				}
				else
				{
					Score3 = Double.valueOf(rs.getString("Score3")).doubleValue();
				}
				BigDecimal bd = new BigDecimal((Score1+Score2+Score3)/3.0);
				bd = bd.setScale(2, RoundingMode.HALF_UP);
				
				sb.append("\"Score\":\""+bd+"\"");
				
				sb.append("},");
			}
			sb.replace(sb.length()-1, sb.length(), "]");
			
			
			writer.write(sb.toString());
			
			stat.close();
			conn.close();
		
		}
		catch(Exception ex)
		{}
	}

}
