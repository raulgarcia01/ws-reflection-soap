package sv.wobits.ws;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jws.WebService;

/**
 * The Class MasterQueryImpl.
 */
@WebService(endpointInterface = "sv.wobits.ws.MasterQuery")
public class MasterQueryImpl implements MasterQuery {

	/** The Constant for LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(MasterQueryImpl.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see sv.wobits.ws.MasterQuery#getMasterQueryInfo(java.lang.String,
	 * java.util.Map)
	 */
	@Override
	public String getMasterQueryInfo(String queryName, Map<String, String> params) {
		Object value = null;
		if (queryName.equals("findByID")) {
			if ((params.size() > 0) && (params.get("userID") != null)) {

				// User ID parameter
				Class[] paramID = new Class[1];
				paramID[0] = Integer.TYPE;

				try {
					// --Reflection of a class related with the method name
					Class<?> clsBusinessRules = Class.forName("sv.wobits.br.BusinessRules");
					// --Invoking the object class
					Object objBusinessRules = clsBusinessRules.newInstance();
					// --Reflection of a method of the invoked class
					Method method = clsBusinessRules.getDeclaredMethod(queryName, paramID);
					// --Send parameters info
					value = method.invoke(objBusinessRules, Integer.parseInt(params.get("userID")));

				} catch (Exception e) {
					LOGGER.log(Level.SEVERE, "Error occur :", e);
				}

				return value.toString();
			} else {
				return "QueryName parameter not found";
			}
		} else if (queryName.equals("findByEmail")) {
			if ((params.size() > 0) && (params.get("emailUser") != null)) {

				// User ID parameter
				Class[] paramEmail = new Class[1];
				paramEmail[0] = String.class;

				try {
					// --Reflection of a class related with the method name
					Class<?> clsBusinessRules = Class.forName("sv.wobits.br.BusinessRules");
					// --Invoking the object class
					Object objBusinessRules = clsBusinessRules.newInstance();
					// --Reflection of a method of the invoked class
					Method method = clsBusinessRules.getDeclaredMethod(queryName, paramEmail);
					// --Send parameters info
					value = method.invoke(objBusinessRules, params.get("emailUser").toString());

				} catch (Exception e) {
					LOGGER.log(Level.SEVERE, "Error occur :", e);
				}
				return (String) value;
			} else {
				return "QueryName parameter not found";
			}
		} else {
			return "QueryName doesn't exist";
		}
	}

}
