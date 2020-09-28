package com.kke.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kke.vo.MemberVO;

public class AuthInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
								Object handler) throws Exception {
		// 1. handler ���� Ȯ��
		// �츮�� ���� �ִ� ���� Controller�� �ִ� �޼����̹Ƿ� HandlerMethod Ÿ������ üũ
		if( handler instanceof HandlerMethod == false ) {
			// return true�̸�  Controller�� �ִ� �޼��尡 �ƴϹǷ�, �״�� ��Ʈ�ѷ��� ����
			return true;
		}

		// 2.�� ��ȯ
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		// 3. @Auth �޾ƿ���
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		Auth adminRole = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		System.out.println(auth);
		System.out.println("adminRole :"+adminRole);
		
		// 4. method�� @Auth�� ���� ���, �� ������ �ʿ� ���� ��û
		if( auth == null ) {
			return true;
		}
		
		// 5. @Auth�� �ִ� ����̹Ƿ�, ������ �ִ��� üũ
		HttpSession session = request.getSession();
		if( session == null ) {
			// �α��� ȭ������ �̵�
			response.sendRedirect(request.getContextPath() + "/");
			return false;
		}
		
		// 6. ������ �����ϸ� ��ȿ�� �������� Ȯ��
		MemberVO member = (MemberVO)session.getAttribute("member");
		System.out.println("member: "+ member);
		if ( member == null ) {
			//response.sendRedirect(request.getContextPath() + "/admin/adminPage");
			return false;
		}
		
		// 7-0. admin user_job���� üũ���ֱ�
		if("ROLE_ADMIN".equals(member.getuser_job()) == false){
			System.out.println("admin�ƴ�!");
			response.sendRedirect(request.getContextPath() +"/");
			return false;
		}

		// 7. admin�� ���(adminRole�� ��� null ���� �۵��ȵ� �߽������� �� �� �����ʿ�..)
		if( adminRole != null ) {
			String role = adminRole.role().toString();
			if( "ADMIN".equals(role) ) {
				if( "qwe".equals(member.getuser_id()) == false ){
					response.sendRedirect(request.getContextPath());
					return false;
				}
			}
		}
		
		
		// 8. �����㰡, �� �޼��带 �����ϵ��� ��
		return true;
	}
}