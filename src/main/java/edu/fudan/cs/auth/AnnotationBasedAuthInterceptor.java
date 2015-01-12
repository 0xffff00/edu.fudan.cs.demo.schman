package edu.fudan.cs.auth;

import java.lang.annotation.Annotation;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 基于注解方式的用户登录认证模块拦截器 本拦截器用于拦截被@RequireUserLogin,@RequirePermission,@RequireRole等注解的method
 * 
 * @author Huang Zhenkun (14210240077@fudan.edu.cn)
 * @version Create Date: 2014年12月4日
 * @since 1.8
 */
public class AnnotationBasedAuthInterceptor implements HandlerInterceptor {

	private static final Log log = LogFactory.getLog(AnnotationBasedAuthInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		RequireUserLogin annoRequireUserLogin = null;
		RequirePermission annoRequirePermission = null;
		HttpSession session = request.getSession();
		try {
			annoRequireUserLogin = this.findAnno(handler, RequireUserLogin.class);
		} catch (Exception e) {
			log.error(e);
		}
		if (annoRequireUserLogin != null) {
			if (session.getAttribute("user") == null) {
				response.sendRedirect(request.getContextPath() + "/home?goto=" + request.getRequestURI());
				return false;
			}
		}

		try {
			annoRequirePermission = this.findAnno(handler, RequirePermission.class);
		} catch (Exception e) {
			log.error(e);
		}
		if (annoRequirePermission != null) {
			if (session.getAttribute("user") == null) {
				response.sendRedirect(request.getContextPath() + "/home?goto=" + request.getRequestURI());
				return false;
			}
			Map<?, ?> user = (Map<?, ?>) session.getAttribute("user");
			String permissions = MapUtils.getString(user, "permissions");
			if (permissions == null) {
				return false;
			} else {
				String permissionNeed = annoRequirePermission.value();
				if (!permissions.contains(permissionNeed))
					return false;
			}
			
		}
		// TODO parse @RequireRole
		return true;

	}

	/**
	 * 通过java反射查找handler所被附上的注解<br>
	 * 先尝试读取该Method被标注的注解，如果没找到，再尝试读取该Method所在的Class被标注的注解。
	 * 
	 * @param handler
	 *            被拦截的Method句柄
	 * @param annotationType
	 *            注解类型
	 * @return 被附上的注解实例
	 * @author Huang Zhenkun (14210240077@fudan.edu.cn)
	 */
	private <A extends Annotation> A findAnno(Object handler, Class<A> annotationType) {
		if (!(handler instanceof HandlerMethod)) {
			log.warn("this is not HandlerMethod type : " + handler);
			return null;
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		// 先尝试读取该Method被标注的注解
		A anno = handlerMethod.getMethodAnnotation(annotationType);
		// 如果没找到，再尝试读取该Method所在的Class被标注的注解
		if (anno == null) {
			Class<?> handlerClass = handlerMethod.getBeanType();
			anno = handlerClass.getAnnotation(annotationType);
		}

		return anno;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {

	}

}