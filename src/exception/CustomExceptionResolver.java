package exception;
/*
 * 自定义异常处理器的实现类
 * 项目中任何一个过程抛出异常由这里解决
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class CustomExceptionResolver implements HandlerExceptionResolver {

	/*
	 * obj是发生异常的地方 包加类加方法加形参
	 * 
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj,
			Exception e) {
		ModelAndView mv = new ModelAndView();
		if (e instanceof CustomException) {
			// 预期异常
			mv.addObject("error", ((CustomException) e).getMsg());
		} else {
			// 未知异常
			mv.addObject("error", "未知异常");
		}
		mv.setViewName("error");
		return mv;
	}

}
