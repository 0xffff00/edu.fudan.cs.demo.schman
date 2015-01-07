package edu.fudan.cs.schapp.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * utilities for building complex SQL
 * 
 * @author Huang Zhenkun (14210240077@fudan.edu.cn)
 * @version Create Date: 2014-12-12
 * @since 1.8
 */
public class SqlBuilder {

	public static String wrapForCount(String sql) {
		return "\nSELECT COUNT(*) CNT0 FROM ( \n" + sql + "\n)\n";
	}

	public static String wrapForPagination(String sql, Map<String, Object> qConfig) {
		if (qConfig == null) {
			return sql;
		}
		Object pgObj = qConfig.get("pg");
		if (pgObj == null || !(pgObj instanceof Boolean) || !((Boolean) pgObj)) {
			return sql;
		}
		Integer pgStart = (Integer) qConfig.get("pgStart");
		Integer pgLen = (Integer) qConfig.get("pgLen");
		Integer pgEnd = pgStart + pgLen;
		String res = "\nSELECT TABLE1.* FROM (SELECT TABLE0.*, ROWNUM ROWNUM1 FROM ((\n" + sql + "\n)) TABLE0 WHERE ROWNUM  <= " + pgEnd
				+ " ) TABLE1 WHERE TABLE1.ROWNUM1 > " + pgStart + " \n";

		return res;

	}

	public static String build_ORDER_BY(String sql, Map<String, Object> qConfig) {
		return "";
	}

	/**
	 * build a string including named parameters,which is used for SQL like
	 * "UPDATE table SET ..." <br>
	 * Example:<br>
	 * 
	 * <pre>
	 * buildParamsContentInUpdateSql({"age":1,"name":"Tom"}) = "age=:age,name=:name"
	 * </pre>
	 * 
	 * @param params
	 * @return a string can be put behind 'SET' in a 'UPDATE' SQL string
	 */
	public static String buildNamedParamsContentInUpdateSql(Map<String, Object> params) {
		return buildNamedParamsContentInUpdateSql(params.keySet());
	}

	/**
	 * build a string including named parameters,which is used for SQL like
	 * "UPDATE table SET ..." ,however,"id" parameter will be skipped. <br>
	 * Example:<br>
	 * 
	 * <pre>
	 * buildParamsContentInUpdateSql(["id","age","name"]) = "age=:age,name=:name"
	 * </pre>
	 * 
	 * @param paramNames
	 * @return a string can be put behind 'SET' in a 'UPDATE' SQL string
	 */
	public static String buildNamedParamsContentInUpdateSql(Collection<String> paramNames) {
		StringBuffer sb = new StringBuffer();
		for (String key : paramNames) {
			if ("id".equalsIgnoreCase(key))
				continue;
			sb.append(key).append("=:").append(key).append(',');
		}
		if (sb.length() > 0)
			sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
}
