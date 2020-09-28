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
		// 1. handler 종류 확인
		// 우리가 관심 있는 것은 Controller에 있는 메서드이므로 HandlerMethod 타입인지 체크
		if( handler instanceof HandlerMethod == false ) {
			// return true이면  Controller에 있는 메서드가 아니므로, 그대로 컨트롤러로 진행
			return true;
		}

		// 2.형 변환
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		// 3. @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		Auth adminRole = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		System.out.println(auth);
		System.out.println("adminRole :"+adminRole);
		
		// 4. method에 @Auth가 없는 경우, 즉 인증이 필요 없는 요청
		if( auth == null ) {
			return true;
		}
		
		// 5. @Auth가 있는 경우이므로, 세션이 있는지 체크
		HttpSession session = request.getSession();
		if( session == null ) {
			// 로그인 화면으로 이동
			response.sendRedirect(request.getContextPath() + "/");
			return false;
		}
		
		// 6. 세션이 존재하면 유효한 유저인지 확인
		MemberVO member = (MemberVO)session.getAttribute("member");
		System.out.println("member: "+ member);
		if ( member == null ) {
			//response.sendRedirect(request.getContextPath() + "/admin/adminPage");
			return false;
		}
		
		// 7-0. admin user_job으로 체크해주기
		if("ROLE_ADMIN".equals(member.getuser_job()) == false){
			System.out.println("admin아님!");
			response.sendRedirect(request.getContextPath() +"/");
			return false;
		}

		// 7. admin일 경우(adminRole에 계속 null 들어가서 작동안됨 잘써먹을라면 좀 더 이해필요..)
		if( adminRole != null ) {
			String role = adminRole.role().toString();
			if( "ADMIN".equals(role) ) {
				if( "qwe".equals(member.getuser_id()) == false ){
					response.sendRedirect(request.getContextPath());
					return false;
				}
			}
		}
		
		
		// 8. 접근허가, 즉 메서드를 실행하도록 함
		return true;
	}
}