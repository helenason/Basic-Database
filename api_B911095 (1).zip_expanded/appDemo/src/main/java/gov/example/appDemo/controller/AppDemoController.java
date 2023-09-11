package gov.example.appDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppDemoController {
	@RequestMapping("/hi")
	@ResponseBody

	// Method
	public String helloWorld() {

		// Print statement
		return "Hi World!";
	}
}
