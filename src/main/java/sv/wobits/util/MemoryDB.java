package sv.wobits.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemoryDB {

	Connection conn = null;
	Statement stmt = null;
	
	public void connectionToDerby() throws SQLException {
		String dbUrl = "jdbc:derby:memory:sampledb;create=true";
		conn = DriverManager.getConnection(dbUrl);
	}
	
	public void importToDerby() throws SQLException {
		Statement stmt = conn.createStatement();
		 
		 // create table
		stmt.executeUpdate("Create table users (id int primary key, name varchar(30))");
		
		// insert 2 rows
		stmt.executeUpdate("insert into users values (1,'tom')");
		stmt.executeUpdate("insert into users values (2,'peter')");
	}
}