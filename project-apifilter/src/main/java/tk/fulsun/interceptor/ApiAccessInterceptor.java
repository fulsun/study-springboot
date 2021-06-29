package tk.fulsun.interceptor;

import com.alibaba.fastjson.JSON;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import tk.fulsun.annotation.AccessLimit;
import tk.fulsun.common.CodeMsg;
import tk.fulsun.utils.RedisUtil;

/**
 * @author fulsun
 * @description: 访问的拦截器
 * @date 6/9/2021 2:28 PM
 */
@Component
public class ApiAccessInterceptor extends HandlerInterceptorAdapter {
  @Autowired private RedisTemplate redisTemplate;
  @Autowired private RedisUtil redisUtil;
  private final Semaphore permit = new Semaphore(10, true);

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    // redisUtil.setRedisTemplate(redisTemplate);
    // 判断请求是否属于方法的请求
    if (handler instanceof HandlerMethod) {
      HandlerMethod hm = (HandlerMethod) handler;
      // 获取方法中的注解,看是否有该注解
      AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
      if (accessLimit == null) {
        return true;
      }
      int seconds = accessLimit.seconds();
      int maxCount = accessLimit.maxCount();
      boolean login = accessLimit.needLogin();
      String key = request.getRequestURI();
      // 如果需要登录
      if (login) {
        // 获取登录的session进行判断
        // 这里假设用户id是1,项目中是动态获取的userId
        key += "" + "1";
      }

      // 从redis中获取用户访问的次数
      try {
        // 控制并发数量为10
        permit.acquire();
        Integer count = (Integer) redisUtil.get(key);
        System.out.println("当前次数 " + count);
        if (count == null) {
          // 第一次访问
          redisUtil.set(key, 1, seconds);
        } else if (count < maxCount) {
          // +1
          redisUtil.incr(key, 1);
        } else {
          // 超出访问次数, 这里的CodeMsg是一个返回参数
          render(response, CodeMsg.ACCESS_LIMIT_REACHED);
          return false;
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        permit.release();
      }
    }
    return true;
  }

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
