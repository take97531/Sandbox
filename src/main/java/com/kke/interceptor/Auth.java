package com.kke.interceptor;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface Auth {
	public enum Role {ADMIN, USER}
	// �̿� ���� �ۼ��ϸ� �޼��� ���� @Auth(role=Role.ADMIN)�� ���� �ۼ� ����
	public Role role() default Role.USER;
}
