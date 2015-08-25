package org.test.results;
import java.io.IOException;
import java.io.PrintWriter;
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
	public static void main(String[]args){
		String[]queries={"select count(keyword) as val from TestExecution where keyword=\"Alpha\" group by keyword;",
				"select count(keyword) as val from TestExecution where keyword=\"Alpha\" and status=\"PASSED\" group by keyword;",
				"select count(keyword) as val from TestExecution where keyword=\"Alpha\" and status=\"FAILED\" group by keyword;",
				"select count(keyword) as val from TestExecution where keyword=\"Alpha\" and status=\"NOT RUN\" group by keyword;",
				"select count(keyword) as val from TestExecution where keyword=\"Alpha\" and status=\"BLOCKED\" group by keyword;"};
		createRowDataFromQueries(queries);
		//String[]rowNames
	}
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse response)
		throws ServletException, IOException {
	String st="";
	String param = req.getParameter("program");
	PrintWriter pw = response.getWriter();
	if(param.equals("program")){
		response.getWriter().println("<html>");
		//response.getWriter().println("<body  background=\"http://www.wallpaperup.com/uploads/wallpapers/2014/01/10/223345/b30a0f4c2cc96778690cdf0da61ee078.jpg\">");
		//response.getWriter().println("<h1>"+st+"<h1>");
		pw.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"/Users/i311383/Documents/Results/css/style.css\">");
		pw.println("<style type=\"text/css\">");
		pw.println("table, th, td {");
		pw.println("width:100%;");
		pw.println("border: 3px solid black;");
		pw.println("background:lightgray;");
		pw.println("h2 {");
		pw.println("font-family:\"Palatino Linotype\"");
		pw.println("}");
		pw.println("}");
		pw.println("</style>");
		
		pw.println("<h2>Test Metrics by KEYWORD</h2>");
		pw.println("<table>");
		
		//Building 1st table
		String[]queries={"select count(keyword) as val from TestExecution where keyword=\"Alpha\" group by keyword;","select count(keyword) as val from TestExecution where keyword=\"Alpha\" and status=\"PASSED\" group by keyword;","select count(keyword) as val from TestExecution where keyword=\"Alpha\" and status=\"FAILED\" group by keyword;","select count(keyword) as val from TestExecution where keyword=\"Alpha\" and status=\"NOT RUN\" group by keyword;","select count(keyword) as val from TestExecution where keyword=\"Alpha\" and status=\"BLOCKED\" group by keyword;"};
		String[]queries1={"select count(keyword) as val from TestExecution where keyword=\"Acceptance\" group by keyword;",
				"select count(keyword) as val from TestExecution where keyword=\"Acceptance\" and status=\"PASSED\" group by keyword;",
				"select count(keyword) as val from TestExecution where keyword=\"Acceptance\" and status=\"FAILED\" group by keyword;",
				"select count(keyword) as val from TestExecution where keyword=\"Acceptance\" and status=\"NOT RUN\" group by keyword;",
				"select count(keyword) as val from TestExecution where keyword=\"Acceptance\" and status=\"BLOCKED\" group by keyword;"};
		String[]rowNames = {"Total","Passed","Passed %","Failed","Failed %","Not Run","Not Run %","Blocked","Blocked%"};
		String[]colNames = {"Keyword","Alpha","Acceptance"};
		int[][]values = {createRowDataFromQueries(queries),createRowDataFromQueries(queries1)};
		buildTable(rowNames, colNames, values, pw);
		pw.println("</table>");
		
		//Building 2nd table
		pw.println("<h2>Test Metrics by Tester</h2>");
		String[]queries_byTesterName1={"select count(TesterName) as val from TestExecution where TesterName=\"Surya i311383\" group by TesterName;","select count(TesterName) as val from TestExecution where TesterName=\"Surya i311383\" and status=\"PASSED\" group by TesterName;","select count(TesterName) as val from TestExecution where TesterName=\"Surya i311383\" and status=\"FAILED\" group by TesterName;","select count(TesterName) as val from TestExecution where TesterName=\"Surya i311383\" and status=\"NOT RUN\" group by TesterName;","select count(TesterName) as val from TestExecution where TesterName=\"Surya i311383\" and status=\"BLOCKED\" group by TesterName;"};
		String[]queries_byTesterName2={"select count(TesterName) as val from TestExecution where TesterName=\"Prakash ixxxxx\" group by TesterName;","select count(TesterName) as val from TestExecution where TesterName=\"Prakash ixxxxx\" and status=\"PASSED\" group by TesterName;","select count(TesterName) as val from TestExecution where TesterName=\"Prakash ixxxxx\" and status=\"FAILED\" group by TesterName;","select count(TesterName) as val from TestExecution where TesterName=\"Prakash ixxxxx\" and status=\"NOT RUN\" group by TesterName;","select count(TesterName) as val from TestExecution where TesterName=\"Prakash ixxxxx\" and status=\"BLOCKED\" group by TesterName;"};
		String[]queries_byTesterName3={"select count(TesterName) as val from TestExecution where TesterName=\"Rohini iyyyyyy\" group by TesterName;","select count(TesterName) as val from TestExecution where TesterName=\"Rohini iyyyyyy\" and status=\"PASSED\" group by TesterName;","select count(TesterName) as val from TestExecution where TesterName=\"Rohini iyyyyyy\" and status=\"FAILED\" group by TesterName;","select count(TesterName) as val from TestExecution where TesterName=\"Rohini iyyyyyy\" and status=\"NOT RUN\" group by TesterName;","select count(TesterName) as val from TestExecution where TesterName=\"Rohini iyyyyyy\" and status=\"BLOCKED\" group by TesterName;"};
		String []colNames_2 = {"Tester", "Surya","Prakash","Rohini"};
		int [][]values_table2 ={createRowDataFromQueries(queries_byTesterName1),createRowDataFromQueries(queries_byTesterName2),createRowDataFromQueries(queries_byTesterName3)};
		pw.println("<table>");
		buildTableThreeRows(rowNames, colNames_2, values_table2, pw);
		pw.println("</table>");
		
		//Building 3rd table
				pw.println("<h2>Test Metrics by Test Suite</h2>");
				String[]queries_byTestSuite1={"select count(TestSuite) as val from TestExecution where TestSuite=\"Client\" group by TestSuite;","select count(TestSuite) as val from TestExecution where TestSuite=\"Client\" and status=\"PASSED\" group by TestSuite;","select count(TestSuite) as val from TestExecution where TestSuite=\"Client\" and status=\"FAILED\" group by TestSuite;","select count(TestSuite) as val from TestExecution where TestSuite=\"Client\" and status=\"NOT RUN\" group by TestSuite;","select count(TestSuite) as val from TestExecution where TestSuite=\"Client\" and status=\"BLOCKED\" group by TestSuite;"};
				String[]queries_byTestSuite2={"select count(TestSuite) as val from TestExecution where TestSuite=\"Server\" group by TestSuite;","select count(TestSuite) as val from TestExecution where TestSuite=\"Server\" and status=\"PASSED\" group by TestSuite;","select count(TestSuite) as val from TestExecution where TestSuite=\"Server\" and status=\"FAILED\" group by TestSuite;","select count(TestSuite) as val from TestExecution where TestSuite=\"Server\" and status=\"NOT RUN\" group by TestSuite;","select count(TestSuite) as val from TestExecution where TestSuite=\"Server\" and status=\"BLOCKED\" group by TestSuite;"};
				String[]queries_byTestSuite3={"select count(TestSuite) as val from TestExecution where TestSuite=\"Buyer\" group by TestSuite;","select count(TestSuite) as val from TestExecution where TestSuite=\"Buyer\" and status=\"PASSED\" group by TestSuite;","select count(TestSuite) as val from TestExecution where TestSuite=\"Buyer\" and status=\"FAILED\" group by TestSuite;","select count(TestSuite) as val from TestExecution where TestSuite=\"Buyer\" and status=\"NOT RUN\" group by TestSuite;","select count(TestSuite) as val from TestExecution where TestSuite=\"Buyer\" and status=\"BLOCKED\" group by TestSuite;"};

				String []colNames_3 = {"Test Suite", "Client","Server","Buyer"};
				int [][]values_table3 ={createRowDataFromQueries(queries_byTestSuite1),createRowDataFromQueries(queries_byTestSuite2),createRowDataFromQueries(queries_byTestSuite3)};
				pw.println("<table>");
				buildTableThreeRows(rowNames, colNames_3, values_table3, pw);
				pw.println("</table>");
				//Building 4th table	
				String[]queries_byBuild1={"select count(build) as val from TestExecution where build=\"2.0.11\" group by build;","select count(build) as val from TestExecution where build=\"2.0.11\" and status=\"PASSED\" group by build;","select count(build) as val from TestExecution where build=\"2.0.11\" and status=\"FAILED\" group by build;","select count(build) as val from TestExecution where build=\"2.0.11\" and status=\"NOT RUN\" group by build;","select count(build) as val from TestExecution where build=\"2.0.11\" and status=\"BLOCKED\" group by build;"};
				String[]queries_byBuild2={"select count(build) as val from TestExecution where build=\"2.0.24\" group by build;","select count(build) as val from TestExecution where build=\"2.0.24\" and status=\"PASSED\" group by build;","select count(build) as val from TestExecution where build=\"2.0.24\" and status=\"FAILED\" group by build;","select count(build) as val from TestExecution where build=\"2.0.24\" and status=\"NOT RUN\" group by build;","select count(build) as val from TestExecution where build=\"2.0.24\" and status=\"BLOCKED\" group by build;"};
				String[]queries_byBuild3={"select count(build) as val from TestExecution where build=\"2.0.27\" group by build;","select count(build) as val from TestExecution where build=\"2.0.27\" and status=\"PASSED\" group by build;","select count(build) as val from TestExecution where build=\"2.0.27\" and status=\"FAILED\" group by build;","select count(build) as val from TestExecution where build=\"2.0.27\" and status=\"NOT RUN\" group by build;","select count(build) as val from TestExecution where build=\"2.0.27\" and status=\"BLOCKED\" group by build;"};
				pw.println("<h2>Test Metrics by Build Number</h2>");
				String []colNames_4 = {"Build", "2.0.11","2.0.24","2.0.27"};
				int [][]values_table4 ={createRowDataFromQueries(queries_byBuild1),createRowDataFromQueries(queries_byBuild2),createRowDataFromQueries(queries_byBuild3)};
				pw.println("<table>");
				buildTableThreeRows(rowNames, colNames_4, values_table4, pw);
				pw.println("</table>");
				
		pw.println("</body>");
		pw.println("</html>");
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
public static int[] createRowDataFromQueries(String[]queries){
	int[] values=new int[9];
	
	int totValue = getData(queries[0]);
	values[0] = totValue;
	if(totValue==0){
		for(int i=1;i<values.length;i++){
			values[i]=0;
		}
	}else{
	values[1] =  getData(queries[1]);
	
	values[2] = (values[1]*100)/totValue;
	values[3] =  getData(queries[2]);
	values[4] = (values[3]*100)/totValue;
	values[5] =  getData(queries[3]);
	values[6] = (values[5]*100)/totValue;
	values[7] =  getData(queries[4]);
	values[8] = (values[7]*100)/totValue;
	}
	for(int j:values){
		System.out.print(j+ " ");
	}
	return values;
	
}
public static void buildTable(String[]rowNames,String []columnNames, int[][]values,PrintWriter pw ){
	pw.println("<tr>");
	
	pw.println("<th>"+columnNames[0]+"</th>");
	for(String rowName:rowNames){
		pw.println("<th>"+rowName+"</th>");
	}
	pw.println("</tr>");
	pw.println("<tr>");
	pw.println("<th>"+columnNames[1]+"</th>");
	for(int value:values[0]){
		pw.println("<td>"+value+"</td>");
	}
	
	pw.println("</tr>");
	pw.println("<tr>");
	pw.println("<th>"+columnNames[2]+"</th>");
	for(int value:values[1]){
		pw.println("<td>"+value+"</td>");
	}
	
	pw.println("</tr>");
}
public static void buildTableThreeRows(String[]rowNames,String []columnNames, int[][]values,PrintWriter pw ){
	pw.println("<tr>");
	
	pw.println("<th>"+columnNames[0]+"</th>");
	for(String rowName:rowNames){
		pw.println("<th>"+rowName+"</th>");
	}
	pw.println("</tr>");
	pw.println("<tr>");
	pw.println("<th>"+columnNames[1]+"</th>");
	for(int value:values[0]){
		pw.println("<td>"+value+"</td>");
	}
	
	pw.println("</tr>");
	pw.println("<tr>");
	pw.println("<th>"+columnNames[2]+"</th>");
	for(int value:values[1]){
		pw.println("<td>"+value+"</td>");
	}
	
	pw.println("</tr>");
	pw.println("<tr>");
	pw.println("<th>"+columnNames[3]+"</th>");
	for(int value:values[2]){
		pw.println("<td>"+value+"</td>");
	}
	
	pw.println("</tr>");
}

}

