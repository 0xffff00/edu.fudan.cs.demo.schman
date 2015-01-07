package edu.fudan.nisl.auth;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 
 * @author Huang Zhenkun (14210240077@fudan.edu.cn)
 * @version Create Date: 2014年12月21日
 * @since 1.8
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequirePermission {
	String value() default "";
}
