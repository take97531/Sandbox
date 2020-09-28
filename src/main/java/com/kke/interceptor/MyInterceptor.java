package com.kke.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.kke.vo.MemberVO;

public class MyInterceptor implements HandlerInterceptor{
	// controller�� ������ ���� ó���ϴ� ���ͼ���
		// ��ȯ�� false��� controller�� ��û�� ����
		// �Ű����� Object�� �ڵ鷯 ������ �ǹ��Ѵ�. ( RequestMapping , DefaultServletHandler )
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MyInterCeptor - preHandle");
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
				System.out.println(adminRole);
				
				// 4. method�� @Auth�� ���� ���, �� ������ �ʿ� ���� ��û
				if( auth == null ) {
					System.out.println("admin�ϰ�� 1�ܰ�");
					return true;
				}
				
				// 5. @Auth�� �ִ� ����̹Ƿ�, ������ �ִ��� üũ
				HttpSession session = request.getSession();
				if( session == null ) {
					// �α��� ȭ������ �̵�
					System.out.println("admin�ϰ�� 1�ܰ�");
					response.sendRedirect(request.getContextPath() + "/");
					return false;
				}
				
				// 6. ������ �����ϸ� ��ȿ�� �������� Ȯ��
				MemberVO member = (MemberVO)session.getAttribute("member");
				if ( member == null ) {
					System.out.println("admin�ϰ�� 1�ܰ�");
					//response.sendRedirect(request.getContextPath() + "/admin/adminPage");
					return false;
				}

				// 7. admin�� ���
				if( adminRole != null ) {
					System.out.println("admin�ϰ�� 1�ܰ�");
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
	
	
	// controller�� handler�� ������ ó����
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	// view���� ó���� ���� �Ŀ� ó����
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
