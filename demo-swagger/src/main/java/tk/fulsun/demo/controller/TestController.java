package tk.fulsun.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fulsun
 * @description: 测试swagger的接口生成
 * @date 6/8/2021 2:54 PM
 */
@Api(tags = "测试接口")
@RestController
@RequestMapping("/api")
public class TestController {

  @ApiOperation(value = "测试接口标题", notes = "测试接口描述")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "param1", value = "参数1", required = true),
    @ApiImplicitParam(name = "param2", value = "参数2", required = false)
  })
  @ApiResponses({
    @ApiResponse(code = 400, message = "请求参数没填好"),
    @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
  })
  @GetMapping("/test")
  public String test() {
    return "ok";
  }
}
