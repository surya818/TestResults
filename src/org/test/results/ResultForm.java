package org.test.results;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;


public class ResultForm extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse response)
		throws ServletException, IOException {
	String st="";
	String param = req.getParameter("program");
	
	if(param.equals("program")){
		response.getWriter().println("<html>");
		//response.getWriter().println("<body  background=\"http://www.wallpaperup.com/uploads/wallpapers/2014/01/10/223345/b30a0f4c2cc96778690cdf0da61ee078.jpg\">");
		//response.getWriter().println("<h1>"+st+"<h1>");
		response.getWriter().println("<link rel=\"stylesheet\" type=\"text/css\" href=\"/Users/i311383/Documents/Results/css/style.css\">");
		response.getWriter().println("<style type=\"text/css\">");
		response.getWriter().println("table, th, td {");
		response.getWriter().println("width:100%;");
		response.getWriter().println("border: 3px solid black;");
		response.getWriter().println("background:lightgray;");
		response.getWriter().println("}");
		response.getWriter().println("</style>");
		
		response.getWriter().println("<h2>Test Metrics by KEYWORD</h2>");
		response.getWriter().println("<table>");
		response.getWriter().println("<tr>");
		response.getWriter().println("<td>"+"Keyword"+"</td>");
		response.getWriter().println("<td>"+"Total"+"</td>");
		response.getWriter().println("<td>"+"Passed"+"</td>");
		response.getWriter().println("<td>"+"Passed %"+"</td>");

		response.getWriter().println("<td>"+"Failed"+"</td>");
		response.getWriter().println("<td>"+"Failed %"+"</td>");
		response.getWriter().println("<td>"+"Not Run"+"</td>");
		response.getWriter().println("<td>"+"Not Run %"+"</td>");
		response.getWriter().println("<td>"+"Blocked"+"</td>");
		response.getWriter().println("<td>"+"Blocked%"+"</td>");
		response.getWriter().println("</tr>");
		response.getWriter().println("<tr>");
		response.getWriter().println("<td>"+"Alpha"+"</td>");
		String total = "select count(keyword) as val from TestExecution where keyword=\"Alpha\" group by keyword;";
		int totalVal=getData(total);
		response.getWriter().println("<td>"+totalVal+"</td>");

		String s = "select count(keyword) as val from TestExecution where keyword=\"Alpha\" and status=\"PASSED\" group by keyword;";
		response.getWriter().println("<td>"+getData(s)+"</td>"); 
		response.getWriter().println("<td>"+((getData(s)*100/totalVal))+"</td>");

		 s = "select count(keyword) as val from TestExecution where keyword=\"Alpha\" and status=\"FAILED\" group by keyword;";
		 response.getWriter().println("<td>"+getData(s)+"</td>"); 
		 response.getWriter().println("<td>"+((getData(s)*100/totalVal))+"</td>");

		 s = "select count(keyword) as val from TestExecution where keyword=\"Alpha\" and status=\"NOT RUN\" group by keyword;";
		 response.getWriter().println("<td>"+getData(s)+"</td>"); 
		 response.getWriter().println("<td>"+((getData(s)*100/totalVal))+"</td>");
			s = "select count(keyword) as val from TestExecution where keyword=\"Alpha\" and status=\"BLOCKED\" group by keyword;";
			 response.getWriter().println("<td>"+getData(s)+"</td>"); 
			 response.getWriter().println("<td>"+((getData(s)*100/totalVal))+"</td>");
/*
		response.getWriter().println("<td>"+""+"</td>");
		*/
		response.getWriter().println("</tr>");
		//Another ROW
		response.getWriter().println("<tr>");
		response.getWriter().println("<td>"+"Acceptance"+"</td>");
		 total = "select count(keyword) as val from TestExecution where keyword=\"Acceptance\" group by keyword;";
		 totalVal=getData(total);
		response.getWriter().println("<td>"+totalVal+"</td>");

		 s = "select count(keyword) as val from TestExecution where keyword=\"Acceptance\" and status=\"PASSED\" group by keyword;";
		response.getWriter().println("<td>"+getData(s)+"</td>"); 
		response.getWriter().println("<td>"+((getData(s)*100/totalVal))+"</td>");

		 s = "select count(keyword) as val from TestExecution where keyword=\"Acceptance\" and status=\"FAILED\" group by keyword;";
		 response.getWriter().println("<td>"+getData(s)+"</td>"); 
		 response.getWriter().println("<td>"+((getData(s)*100/totalVal))+"</td>");

		 s = "select count(keyword) as val from TestExecution where keyword=\"Acceptance\" and status=\"NOT RUN\" group by keyword;";
		 response.getWriter().println("<td>"+getData(s)+"</td>"); 
		 response.getWriter().println("<td>"+((getData(s)*100/totalVal))+"</td>");
			s = "select count(keyword) as val from TestExecution where keyword=\"Acceptance\" and status=\"BLOCKED\" group by keyword;";
			 response.getWriter().println("<td>"+getData(s)+"</td>"); 
			 response.getWriter().println("<td>"+((getData(s)*100/totalVal))+"</td>");
/*
		response.getWriter().println("<td>"+""+"</td>");
		*/
		response.getWriter().println("</tr>");
		/*
		response.getWriter().println("<tr>");
		response.getWriter().println("<td>"+"</td>");
		response.getWriter().println("</tr>");
		*/
		response.getWriter().println("</table>");
		response.getWriter().println("</body>");
		response.getWriter().println("</html>");
	}
}
public static int getData(String query){
	Connection connection = null;
	java.sql.Statement stmt = null;
	try
	{
	String connectionURL = "jdbc:mysql://localhost:3306/test";
	
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	connection = DriverManager.getConnection(connectionURL, "user", "");
	stmt = connection.createStatement();
	} catch (Exception e)
	{
	e.printStackTrace();
	}
	ResultSet rs=null;
	String s="";
	try{
	rs= stmt.executeQuery(query);
	
	while(rs.next()){
		
		s=rs.getString("val");
	}
	System.out.println(s);
	if(s.trim().equals("")||s==null){
		return 0;
	}
	}
	catch(SQLException|NumberFormatException e){
		e.printStackTrace();
		return 0;
	}
	return Integer.parseInt(s);
}
}
