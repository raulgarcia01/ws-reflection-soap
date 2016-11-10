package sv.wobits.ws;

import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface MasterQuery {

	/**
	 * Gets the master query information using reflection.
	 *
	 * @param queryName Query name method
	 * @param params the params method to execute
	 * @return the master query response
	 */
	@WebMethod
	String getMasterQueryInfo(String queryName, Map<String, String> params);
}
