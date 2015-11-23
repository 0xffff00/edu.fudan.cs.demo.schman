package edu.fudan.cs.auth;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.fudan.cs.schapp.model.UserAdaptee;
/**
 * use example in jsp file:
 * <pre>
 * &lt;require permissions="sys_perm1"&gt;
 * ...
 * &lt;/require&gt;
 * </pre>
 * <pre>
 * &lt;require roles="admin"&gt;
 * ...
 * &lt;/require&gt;
 * </pre>
 * @author Huang Zhenkun (14210240077@fudan.edu.cn)
 * @version Create Date: 2014-12-23
 * @since 1.8
 */
public class RequireTag extends BodyTagSupport {

	private static final Log log = LogFactory.getLog(RequireTag.class);
	private static final long serialVersionUID = 9182854473782482397L;
	private String roles, permissions;

	public int doStartTag() throws JspException {
		boolean pass = true;
		try {
			pass = checkPassort(pageContext.getSession());
		} catch (Exception e) {
			log.error(e);
		}
		return pass ? EVAL_BODY_INCLUDE : SKIP_BODY;
	}

	/**
	 * 检查是否当前用户的权限是否可以放行
	 * 
	 * @param session
	 *            存放当前user的session
	 * @return
	 */
	private boolean checkPassort(HttpSession session) {
		Object userObj = session.getAttribute("user");
		if (userObj == null) {
			return false;
		}
		UserAdaptee user = (UserAdaptee) userObj;
		String currUserMainRole = user.getMainRoleName();		

		if (roles != null) {
			if (currUserMainRole == null)
				return false;
			if (!roles.contains(currUserMainRole))
				return false;
		}
		if (permissions != null) {			
			// TODO implement multi-permissions attribute parsing
		}

		return true;
	}

	public int doAfterBody() throws JspException {
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

}
