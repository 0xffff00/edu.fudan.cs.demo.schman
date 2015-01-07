package edu.fudan.cs.schapp.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author Huang Zhenkun (14210240077@fudan.edu.cn)
 * @version Create Date: 2014-12-11
 * @since 1.8
 */
public class QueryConfigBuilder {

	private static Log log = LogFactory.getLog(QueryConfigBuilder.class);

	/**
	 * parse request parameters POSTed by DataTables in which option
	 * 'serverside' is open.<br>
	 * usage example: <br>
	 * <code>Map qConfig=QueryConfigBuilder.parseFromDT(httpServletRequest.getParameterMap());</code>
	 * 
	 * @see http://datatables.net/examples/server_side/simple.html
	 * @see edu.fudan.nisl.jaq.dao.BaseDAO#simpleQuerysForDT(String, Map)
	 * @param paramsMap
	 * @return qConfig, a Map contains such keys:
	 * 	<pre>  'pg' - whether pagination is open
	 *  'pgDraw'
	 *  'pgStart'
	 *  'pgLen'
	 *  'orderFld' - the column(s) ordered by,eg.: 'age','age,grade,id'
	 *  'orderDir' - 'ASC' or 'DESC'
	 *  </pre>
	 */
	public static Map<String, Object> parseFromDT(Map<String, String[]> paramsMap) {
		Map<String, Object> res = new HashMap<>();

		String[] arr = paramsMap.get("draw");
		if (arr == null || arr.length == 0) { // fast fail
			return res;
		}
		try {
			Integer pgDraw = Integer.valueOf(arr[0]);
			Integer pgStart = Integer.valueOf(paramsMap.get("start")[0]);
			Integer pgLen = Integer.valueOf(paramsMap.get("length")[0]);
			res.put("pg", true);
			res.put("pgDraw", pgDraw);
			res.put("pgStart", pgStart);
			res.put("pgLen", pgLen);

			String[] arr2 = paramsMap.get("order[0][column]");
			if (arr2 != null && arr2.length > 0) {
				Integer orderColIdx = Integer.valueOf(arr2[0]);
				String orderColKey = "columns[" + orderColIdx + "][data]";
				String orderFld = paramsMap.get(orderColKey)[0];
				String orderDir = paramsMap.get("order[0][dir]")[0];
				orderDir = ("desc".equals(orderDir) ? "DESC" : "ASC");
				res.put("order", 1);
				res.put("orderFld", orderFld);
				res.put("orderDir", orderDir);
			}
		} catch (Exception e) {
			log.debug(e);
		}
		return res;
	}
}
