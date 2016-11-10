package sv.wobits.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import junit.framework.TestCase;
import sv.wobits.br.BusinessRules;
import sv.wobits.util.MemoryDB;

/**
 * The Class Reflection Test Case.
 */
public class ReflectionTest extends TestCase {

	/** The Constant for LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(BusinessRules.class.getName());

	/**
	 * Test find user by id.
	 */
	@Test
	public void testFindById() {
		String value = null;
		Connection conn = null;
		PreparedStatement prestmt;
		try {
			MemoryDB memorydb = new MemoryDB();
			conn = memorydb.getConn();
			prestmt = conn.prepareStatement("SELECT name FROM users WHERE id = ? ");
			prestmt.setInt(1, 1);
			ResultSet rs = prestmt.executeQuery();
			while (rs.next()) {
				value = rs.getString(1);
			}
			assertEquals("raul", value);
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "SQLException occur :", e);
		}

	}

}
