package pers.sfl.adminclient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 首页
 * @version: V1.0
 */
@RestController
public class IndexController {
	@GetMapping(value = {"", "/"})
	public String index() {
		return "This is a Spring Boot Admin Client.";
	}
}
