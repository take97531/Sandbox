package com.kke.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.kke.vo.MemberVO;

public class MyInterceptor implements HandlerInterceptor{
	// controller로 보내기 전에 처리하는 인터셉터
		// 반환이 false라면 controller로 요청을 안함
		// 매개변수 Object는 핸들러 정보를 의미한다. ( RequestMapping , DefaultServletHandler )
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MyInterCeptor - preHandle");
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
				System.out.println(adminRole);
				
				// 4. method에 @Auth가 없는 경우, 즉 인증이 필요 없는 요청
				if( auth == null ) {
					System.out.println("admin일경우 1단계");
					return true;
				}
				
				// 5. @Auth가 있는 경우이므로, 세션이 있는지 체크
				HttpSession session = request.getSession();
				if( session == null ) {
					// 로그인 화면으로 이동
					System.out.println("admin일경우 1단계");
					response.sendRedirect(request.getContextPath() + "/");
					return false;
				}
				
				// 6. 세션이 존재하면 유효한 유저인지 확인
				MemberVO member = (MemberVO)session.getAttribute("member");
				if ( member == null ) {
					System.out.println("admin일경우 1단계");
					//response.sendRedirect(request.getContextPath() + "/admin/adminPage");
					return false;
				}

				// 7. admin일 경우
				if( adminRole != null ) {
					System.out.println("admin일경우 1단계");
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
	
	
	// controller의 handler가 끝나면 처리됨
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	// view까지 처리가 끝난 후에 처리됨
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
