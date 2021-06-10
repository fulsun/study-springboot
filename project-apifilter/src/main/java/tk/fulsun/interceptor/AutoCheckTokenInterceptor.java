package tk.fulsun.interceptor;

import com.alibaba.fastjson.JSON;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import tk.fulsun.annotation.AutoICheckToken;
import tk.fulsun.common.CodeMsg;
import tk.fulsun.service.TokenUtilService;

/**
 * @author fsun7
 * @description: 检查token的拦截器
 * @date 6/10/2021 1:54 PM
 */
@Component
public class AutoCheckTokenInterceptor implements HandlerInterceptor {
  @Autowired private TokenUtilService tokenService;
  /**
   * 预处理
   *
   * @param request
   * @param response
   * @param handler
   * @return
   * @throws Exception
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    if (!(handler instanceof HandlerMethod)) {
      return true;
    }
    HandlerMethod handlerMethod = (HandlerMethod) handler;
    Method method = handlerMethod.getMethod();
    // 被AutoICheckToken标记的扫描
    AutoICheckToken methodAnnotation = method.getAnnotation(AutoICheckToken.class);
    if (methodAnnotation != null) {
      try {
        // 幂等性校验, 校验通过则放行, 校验失败则抛出异常, 并通过统一异常处理返回友好提示
        return tokenService.checkToken(request);
      } catch (Exception ex) {
        render(response, CodeMsg.ILLEGAL_REQUEST);
      }
    }
    // 必须返回true,否则会被拦截一切请求
    return true;
  }

  @Override
  public void postHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      ModelAndView modelAndView)
      throws Exception {}

  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {}

  private void render(HttpServletResponse response, CodeMsg cm) throws Exception {
    response.setContentType("application/json;charset=UTF-8");
    response.setStatus(cm.getCode());
    OutputStream out = response.getOutputStream();
    Map result = new HashMap<>(3);
    result.put("status", "1");
    result.put("message", "");
    result.put("error", cm.getMessage());
    String str = JSON.toJSONString(result);
    out.write(str.getBytes("UTF-8"));
    out.flush();
    out.close();
  }
}
