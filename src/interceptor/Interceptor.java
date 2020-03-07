package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class Interceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

		// 登录不拦截
		if (!request.getRequestURI().contains("/login")) {
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			if (username == null) {
				response.sendRedirect(request.getContextPath() + "/login.action");

				// web-info里面的文件只有转发才能进得去，重定向进不去！
				// request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,
				// response);
				return false;
			}
		}

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
