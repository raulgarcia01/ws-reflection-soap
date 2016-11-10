package sv.wobits.br;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import sv.wobits.util.MemoryDB;

/**
 * The Business Rules Class using JDBC.
 */
public class BusinessRules {

	/** The Constant for LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(BusinessRules.class.getName());

	/** The Constant for ERROR_MSG. */
	private static final String ERROR_MSG = "Error data not found";

	/**
	 * Find a user by ID.
	 *
	 * @param idUser
	 *            the id user
	 * @return user's name
	 */
	public String findByID(int idUser) {
		String value = null;
		MemoryDB memorydb = new MemoryDB();

		try {
			Connection conn = memorydb.getConn();
			PreparedStatement prestmt = conn.prepareStatement("SELECT name FROM users WHERE id = ? ");
			prestmt.setInt(1, idUser);
			ResultSet rs = prestmt.executeQuery();
			while (rs.next()) {
				value = rs.getString(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "SQLException occur :", e);
		}

		return (!value.equals("")) ? value : ERROR_MSG;
	}

	/**
	 * Find user by email.
	 *
	 * @param emailUser
	 *            the email user
	 * @return the user's name
	 */
	public String findByEmail(String emailUser) {
		String value = null;
		MemoryDB memorydb = new MemoryDB();

		try {
			Connection conn = memorydb.getConn();
			PreparedStatement prestmt = conn.prepareStatement("SELECT name FROM users WHERE email = ? ");
			prestmt.setString(1, emailUser);
			ResultSet rs = prestmt.executeQuery();
			while (rs.next()) {
				value = rs.getString(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "SQLException occur :", e);
		}

		return (!value.equals("")) ? value : ERROR_MSG;
	}
}
