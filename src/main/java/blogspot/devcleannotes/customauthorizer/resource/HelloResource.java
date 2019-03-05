package blogspot.devcleannotes.customauthorizer.resource;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloResource {

	@Secured("hello_update")
	@GetMapping("/")
	public String getHelloResource() {
		return "HelloResource";
	}
}
