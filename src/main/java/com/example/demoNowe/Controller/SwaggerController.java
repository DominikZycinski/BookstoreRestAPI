package com.example.demoNowe.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 *
 * @author dozyc1
 */
@ApiIgnore
@Controller
public class SwaggerController {
	private static final String SWAGGER_URL = "redirect:/swagger-ui.html#";
	
	@GetMapping("${bookstore.swagger.path}")
	public String swaggerMapping() {
		return SWAGGER_URL;
	}
}