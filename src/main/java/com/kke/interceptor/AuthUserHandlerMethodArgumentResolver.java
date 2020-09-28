package com.kke.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.kke.vo.MemberVO;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver{

	@Override
	public Object resolveArgument(MethodParameter param, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		// 1. �Ķ���Ϳ� @AuthUser�� �پ� �ִ��� , Ÿ���� UserVO���� Ȯ�� 
				if( supportsParameter(param) == false ) {
					// ���� �ؼ��� �� �ִ� �Ķ���Ͱ� �ƴϴ�.
					return WebArgumentResolver.UNRESOLVED;
				}
				
				// 5. ������� ������ �Ǿ��ٸ�, @AuthUser�� �پ��ְ� Ÿ���� UserVO�� ����̴�.
				HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
				HttpSession session = request.getSession();
				if( session == null) {
					return null;
				}
				
				return session.getAttribute("authUser");
	}
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// 2. @AuthUser�� �پ� �ִ��� Ȯ��
				AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
				
				// 3. @AuthUser�� �Ⱥپ� �ִ� ���
				if( authUser == null ) {
					return false;
				}
				
				// 4. UserVO Ÿ���� �ƴ� ���
				if( parameter.getParameterType().equals(MemberVO.class) == false) {
					return false;
				}
				
				return true;
	}

	

}
