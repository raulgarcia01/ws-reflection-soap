package sv.wobits.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class to provide In MemoryDB Derby.
 */
public class MemoryDB {

	/** Connection property. */
	private Connection conn = null;

	/** The Statement property. */
	private Statement stmt = null;

	/**
	 * Creates every time a sample DB in memory.
	 *
	 * @throws SQLException
	 *             the SQL exception
	 */
	public void connectionToDerby() throws SQLException {
		String dbUrl = "jdbc:derby:memory:myInMemDB;create=true";
		// --Open DB connection
		conn = DriverManager.getConnection(dbUrl);
	}

	/**
	 * Creates a Table User an fill with a few rows.
	 *
	 * @throws SQLException
	 *             the SQL exception
	 */
	public void importToDerby() throws SQLException {
		stmt = conn.createStatement();
		DatabaseMetaData dbmd = conn.getMetaData();
		ResultSet rs = dbmd.getTables(null, "APP", "USERS", null);
		if (!rs.next()) {
			// -- Create a User Table
			stmt.executeUpdate("create table users (id int primary key, name varchar(30), email varchar(50))");
		}else{
			//-- Clean database
			stmt.executeUpdate("delete from users");
		}
		// -- Fill a User Table
		stmt.executeUpdate("insert into users values (1,'raul','raul@wobits.com')");
		stmt.executeUpdate("insert into users values (2,'peter','peter@gmail.com')");
		stmt.executeUpdate("insert into users values (3,'joe','joe@goverment.com')");
		stmt.executeUpdate("insert into users values (4,'john','john@yahoo.com')");
		stmt.executeUpdate("insert into users values (5,'mike','mike@hotmail.com')");

		stmt.close();
	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection DB
	 * @throws SQLException
	 *             the SQL exception
	 */
	public Connection getConn() throws SQLException {
		if (this.conn == null) {
			this.connectionToDerby();
			this.importToDerby();
		}
		return conn;
	}

}