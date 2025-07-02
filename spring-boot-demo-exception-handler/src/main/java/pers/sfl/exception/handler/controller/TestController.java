package pers.sfl.exception.handler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pers.sfl.exception.handler.constant.Status;
import pers.sfl.exception.handler.exception.JsonException;
import pers.sfl.exception.handler.exception.PageException;
import pers.sfl.exception.handler.model.ApiResponse;

/**
 * @description: 测试Controller
 */
@Controller
public class TestController {

	@GetMapping("/json")
	@ResponseBody
	public ApiResponse jsonException() {
		throw new JsonException(Status.UNKNOWN_ERROR);
	}

	@GetMapping("/page")
	public ModelAndView pageException() {
		throw new PageException(Status.UNKNOWN_ERROR);
	}
}
