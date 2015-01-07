package edu.fudan.cs.schapp.dao

import javax.annotation.Resource

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

import edu.fudan.cs.schapp.util.SqlBuilder


/**
 * 
 * @author Huang Zhenkun (14210240077@fudan.edu.cn)
 * @since 2014-12-11
 */
class BaseDAO {
	@Resource
	JdbcTemplate jdbcTemplate;
	@Resource
	NamedParameterJdbcTemplate npJdbcTemplate;
	
	private static Log log = LogFactory.getLog(BaseDAO.class);
	
	/**
	 * fetch data that can be read by DataTables via wrapping plain SQL.<br>
	 * dynamic paging,counting is supported,but dynamic ordering is not. 
	 * @param sql
	 * @param qConfig
	 * @return
	 */
	public Map simpleQuerysForDT(String sql,Map qConfig){
		if (qConfig && qConfig['pg']) log.debug("Pagination Enabled.")
		String sql_pg=SqlBuilder.wrapForPagination(sql, qConfig)
		String sql_cnt=SqlBuilder.wrapForCount(sql)
		def data=jdbcTemplate.queryForList(sql_pg)
		def total=jdbcTemplate.queryForMap(sql_cnt).get("CNT0")
		Map res=["recordsTotal":total,"recordsFiltered":total,"data":data]
		if (qConfig && qConfig['pgDraw']) res.put("draw",qConfig['pgDraw'])
		res
	}
	
}
