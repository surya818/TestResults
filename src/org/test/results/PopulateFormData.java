package org.test.results;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class PopulateFormData {

	public static void main(String[] args) throws SQLException, IOException {
		populateData();
	}
	public static void populateData() throws SQLException, IOException{
		Connection connection = null;
		try
		{
		String connectionURL = "jdbc:mysql://localhost:3306/test";
		
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(connectionURL, "user", "");
		
		} catch (Exception e)
		{
		e.printStackTrace();
		}
		String insertQuery = "insert into TestExecution values (?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pStmt = connection.prepareStatement(insertQuery);
		ArrayList<String[]> list = new ArrayList<String[]>();
		BufferedReader br = new BufferedReader(new FileReader("/Users/i311383/TestResult.csv"));
		String tmp=""; int i=0;
		while((tmp=br.readLine())!=null){
			
			String [] dataLine=tmp.split(",");
			
			list.add(dataLine);
			
		}
		System.out.println(list.size());
		for(int j=0;j<list.size();j++){
			System.out.println(list.get(j)[0]);
			System.out.println(list.get(j)[1]);
			System.out.println(list.get(j)[2]);
			pStmt.setString(1, list.get(j)[0]);
			pStmt.setString(2, list.get(j)[1]);
			pStmt.setString(3, list.get(j)[2]);
			pStmt.setString(4, list.get(j)[3]);
			pStmt.setString(5, list.get(j)[4]);
			pStmt.setString(6, list.get(j)[5]);
			pStmt.setString(7, list.get(j)[6]);
			pStmt.setString(8, list.get(j)[7]);
			pStmt.setString(9, list.get(j)[8]);
			pStmt.setString(10, null);
			pStmt.execute();
		}
	}

}
