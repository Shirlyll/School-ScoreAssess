package StudentScore;

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
 * Servlet implementation class SaveScore
 */
@WebServlet("/SaveScore")
public class SaveScore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveScore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if(action.equals("SaveScore")){
			SaveFinalScore(request,response);
			return;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	public void SaveFinalScore(HttpServletRequest request, HttpServletResponse response){
		
	try{
		
			response.setContentType("text/html;charset=utf-8");
			PrintWriter writer = response.getWriter();
			
			String studentIds = request.getParameter("StudentIds");
			String scores = request.getParameter("scores");
			String TeacherId= request.getParameter("TeacherId");
			String ScoreRow ="";
			if(TeacherId.equals("t001"))
			{
				ScoreRow = "Score1";
			}
			else if(TeacherId.equals("t002"))
			{
				ScoreRow = "Score2";
			}
			else if(TeacherId.equals("t003"))
			{
				ScoreRow = "Score3";
			}
			//writer.write(TeacherId);
			String[] studentArr = studentIds.split(",");
			String[] scoresArr = scores.split(",");
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/School","root","Javaweb");
			Statement stat = conn.createStatement();
			
		
			for(int i = 0; i < studentArr.length; i++){
				String studentId = studentArr[i];
				String score = scoresArr[i];
				if(score.isEmpty()){
					score = "";
				}
				//	 update School.tbl_Student set Score1 = "4.8" where id = "4";
				String sql = "update School.tbl_Student set "+ScoreRow+"=\""+ score+"\" where id = \""+studentId+"\"";
				//String sql = "update School.tbl_Student set Score1 = \"4.6\" where id = \"4\"";
				stat.executeUpdate(sql);
			}
			
			
			writer.write("{\"success\":true}");
			stat.close();
			conn.close();
			
			
		}catch(Exception ex){
			
			
		}
	}		
}
