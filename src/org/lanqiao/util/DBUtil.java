package org.lanqiao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	static  Connection conn;
	static final String URL="jdbc:oracle:thin:@localhost:1521:orcl";
	static final String USER="student";
	static final String PASSWORD="123";
	static final String DRIVER="oracle.jdbc.driver.OracleDriver";
	public static Connection getConnection(){
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
		
	}
}
