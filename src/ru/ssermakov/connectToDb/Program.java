package ru.ssermakov.connectToDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Program {
	
	public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	public static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/zumanaha?user=root&password=";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER_NAME);
		Connection conn = DriverManager.getConnection(CONNECTION_STRING);
		
//		Scanner sc = new Scanner (System.in);
//		String line = sc.nextLine();
		
		String sql = "SELECT wo FROM return_objects";
		PreparedStatement cmd = (PreparedStatement) conn.prepareStatement(sql);
//		cmd.setString(1, "%"+line+"%");
		ResultSet res = cmd.executeQuery();
		while (res.next()) {
			String wo = res.getString("wo");
			
			System.out.printf("%s \n", wo);
		}
		
		res.close();
		conn.close();
	}

}
